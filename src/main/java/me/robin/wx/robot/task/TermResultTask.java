
package me.robin.wx.robot.task;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import me.robin.wx.robot.compant.WebClient;
import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.lot.entity.Group;
import me.robin.wx.robot.lot.service.GroupService;

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
 * @version 2017年5月3日 作者
 */
@Component
@EnableScheduling
public class TermResultTask {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(TermResultTask.class);
    
    /** FIXME */
    @Autowired
    private WxMessageSender sender;
    
    /** FIXME */
    @Autowired
    private ContactService contactService;
    
    /** FIXME */
    @Autowired
    private GroupService groupService;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Scheduled(cron = "0 12/15 9-22 * * ?")
    public void sendTermResult() {
        List<Group> groups = groupService.findAll();
        if (CollectionUtils.isEmpty(groups)) {
            return;
        }
        
        String str = WebClient.instance.downloadString("http://gxsf13.ttgw08.com/getqi.aspx?type=1");
        String[] arr = StringUtils.split(str, ",");
        if (arr.length != 6) {
            logger.warn("获取快十开奖结果的格式不正确:{}", str);
            return;
        }
        String msg = String.format("第%s期的结果是%s,%s,%s,%s,%s", (Object[]) arr);
        
        for (Group group : groups) {
            WxGroup wxGroup = contactService.getAllWxGroup().stream().filter(g -> group.getNickName().equals(g.getNickName())).findFirst().get();
            if (wxGroup == null) {
                continue;
            }
            sender.sendTextMessage(wxGroup.getUserName(), msg);
        }
    }
}
