/**create 2017-05-15**/

package me.robin.wx.robot.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.robin.wx.robot.frame.api.Server;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
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
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param model x
     * @return x
     */
    @GetMapping("")
    public String index(Model model) {
        List<WxGroup> groups = contactService.getAllWxGroup();
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
        return group;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @param groupUserName x
     * @param response x
     */
    @GetMapping("headImg")
    public void headImg(String userName, String groupUserName, HttpServletResponse response) {
        
        WxGroup group = contactService.getWxGroup(groupUserName);
        if (group == null) {
            return;
        }
        try (InputStream fis = server.getHeadImg(userName, group)) {
            IOUtils.copy(fis, response.getOutputStream());
        } catch (IOException e) {
            logger.error("获取用户头像时异常", e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @param groupUserName x
     * @param userMapper x
     * @param groupName x
     * @param exUserId x
     * @param names x
     * @return x
     */
    @ResponseBody
    @PostMapping("updateExUserId")
    public Object updateExUserId(String userName, String groupUserName, String exUserId) {
        WxGroup group = contactService.getWxGroup(groupUserName);
        if (group == null) {
            return null;
        }
        WxUser user = group.findMember(userName);
        if (user == null) {
            return null;
        }
        
        if (StringUtils.isBlank(exUserId)) {
            user.setExUserId(null);
            userMapperService.delete(group.getNickName(), groupUserName);
        } else {
            user.setExUserId(exUserId);
            UserMapper userMapper = new UserMapper();
            userMapper.setExUserId(exUserId);
            userMapper.setGroupNickName(group.getNickName());
            userMapper.setUserName(userName);
            userMapper.setNickName(user.getNickName());
            userMapperService.save(userMapper);
        }
        return "";
    }
    
}
