
package me.robin.wx.robot.frame.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.util.WxUtil;

/** 
 */
@Component
public class TextMessageHandler extends AbstractMessageHandler {
    
    @Override
    public void handle(WxMsg message) {
        String content = StringUtils.trim(message.getContent());
        if (StringUtils.isEmpty(content)) {
            return;
        }
        // 只接受群组消息,并忽略群主的消息
        if (!WxUtil.isGroupMessage(message) && StringUtils.equals(server.loginUser().getUserName(), message.getFromUserName())) {
            return;
        }
        
        String groupName = message.getToUserName();
        if ("?".equals(content) || "？".equals(content)) {
            sendMessage(groupName, "这里是问号的说明");
            return;
        }
        
        String[] arr = StringUtils.split(content, "/");
        if (arr.length == 3) {
            String str = String.format("第%s名买%s每个%s块", (Object[]) arr);
            sendMessage(groupName, str);
            return;
        }
    }
    
}
