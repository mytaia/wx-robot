
package me.robin.wx.robot.task;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.api.WebClient;
import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;

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
@EnableScheduling()
public class TermResultTask {
    
    /** FIXME */
    @Autowired
    private WxMessageSender sender;
    
    /** FIXME */
    @Autowired
    private WebClient webClient;
    
    /** FIXME */
    @Autowired
    private ContactService contactService;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Scheduled(cron = "0 12/2 9-22 * * ?")
    public void sendTermResult() {
        WxUser user = contactService.queryUser("888");
        if (user != null) {
            String str = webClient.downloadString("http://gxsf13.ttgw08.com/getqi.aspx?type=1");
            String[] arr = StringUtils.split(str, ",");
            if (arr.length == 6) {
                String msg = String.format("第%s期的结果是%s,%s,%s,%s,%s", (Object[]) arr);
                sender.sendTextMessage(user.getUserName(), msg);
            }
        }
    }
}
