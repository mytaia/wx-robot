
package me.robin.wx.robot.frame.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.util.WxUtil;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.resolver.BetResolver;

/** 
 */
@Component
public class TextMessageHandler extends AbstractMessageHandler {
    
    /** FIXME */
    @Autowired
    BetResolver messageBetResolver;
    
    @Override
    public void handle(WxMsg message) {
        String content = StringUtils.trim(message.getContent());
        if (StringUtils.isEmpty(content)) {
            return;
        }
        // 只接受群组消息,并忽略群主的消息
        if (WxUtil.isGroupMessage(message)) {
            return;
        } else if (StringUtils.equals(server.loginUser().getUserName(), message.getFromUserName())) {
            // 如果是管理员指令
            
            return;
        }
        
        String groupName = message.getFromUserName();
        if ("?".equals(content) || "？".equals(content)) {
            sendMessage(groupName, "这里是指令的说明:1/2/3表示xxx");
            return;
        }
        
        BetRequest bet = messageBetResolver.resolver(content);
        if (bet == null) {
            logger.debug("无法解析的消息[{}]", content);
        }
        
    }
    
}
