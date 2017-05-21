/**create 2017-05-15**/

package me.robin.wx.robot.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.robin.wx.robot.frame.api.Server;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.frame.util.WxUtil;
import me.robin.wx.robot.lot.entity.UserMapper;
import me.robin.wx.robot.lot.service.UserMapperService;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2017年5月17日 作者
 */
@Controller
@RequestMapping("/group")
public class GroupController {
    
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    
    /** FIXME */
    @Autowired
    private ContactService contactService;
    
    /** FIXME */
    @Autowired
    private UserMapperService userMapperService;
    
    /** FIXME */
    @Autowired
    private Server server;
    
    /** FIXME */
    @Value("${user.headImg.path:}")
    private String userHeadImgPath;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param model x
     * @return x
     */
    @GetMapping("")
    public String index(Model model) {
        List<WxGroup> groups = contactService.getAllWxGroup();
        groups.forEach(g -> buildGroup(g));
        model.addAttribute("groups", groups);
        return "group/list";
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @param model x
     * @return x
     */
    @GetMapping("queryGroup")
    @ResponseBody
    public Object queryGroup(String userName, Model model) {
        WxGroup group = null;
        if (StringUtils.isEmpty(userName)) {
            List<WxGroup> groups = contactService.getAllWxGroup();
            if (groups.size() > 0) {
                groups.get(0);
            }
        } else {
            group = contactService.getWxGroup(userName);
        }
        return group == null ? null : buildGroup(group);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @param groupName x
     * @param response x
     */
    @GetMapping("headImg")
    public void headImg(String userName, String groupName, HttpServletResponse response) {
        String path = WxUtil.getUserHeadImgPath(userHeadImgPath, userName);
        File file = new File(path);
        
        try {
            if (!file.exists()) {
                WxGroup group = contactService.getWxGroup(groupName);
                if (group == null) {
                    return;
                }
                file = server.getHeadImg(userName, group);
                if (file == null) {
                    return;
                }
            }
            try (FileInputStream fis = new FileInputStream(file)) {
                IOUtils.copy(fis, response.getOutputStream());
            }
        } catch (IOException e) {
            logger.error("获取用户头像时异常", e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userMapper x
     * @param groupName x
     * @param exUserId x
     * @param names x
     */
    @GetMapping("updateExUserId")
    public void updateExUserId(UserMapper userMapper) {
        if (StringUtils.isBlank(userMapper.getExUserId())) {
            userMapperService.delete(userMapper.getNickName(), userMapper.getGroupNickName());
        } else {
            userMapperService.save(userMapper);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param group x
     * @param userName x
     * @param names x
     * @return x
     */
    public WxGroup buildGroup(WxGroup group) {
        List<UserMapper> list = userMapperService.findByGroupNickName(group.getNickName());
        if (CollectionUtils.isEmpty(list)) {
            return group;
        }
        
        List<WxUser> users = group.getMemberList();
        list.forEach((um) -> {
            users.forEach((user) -> {
                if (StringUtils.equals(um.getNickName(), user.getNickName())) {
                    user.setExUserId(um.getExUserId());
                }
            });
        });
        return group;
    }
}
