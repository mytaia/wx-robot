
package me.robin.wx.robot.frame.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.util.WxUtil;
import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.Commander;
import me.robin.wx.robot.lot.core.RequestContext;

/** 
 */
@Component
public class TextMessageHandler extends AbstractMessageHandler {
    
    /** FIXME */
    @Autowired
    private Commander commander;
    
    @Override
    public void handle(WxMsg message) {
        String content = StringUtils.trim(message.getContent());
        if (StringUtils.isEmpty(content)) {
            return;
        }
        // 只接受群组消息,并忽略群主的消息
        if (!WxUtil.isGroupMessage(message)) {
            return;
        }
        
        String groupName = message.getFromUserName();
        if ("?".equals(content) || "？".equals(content)) {
            sendMessage(groupName, "这里是指令的说明:1/2/3表示xxx");
            return;
        }
        
        RequestContext contex = createContext(message);
        
        // 识别投注请求sbs
        Command cmd = commander.resolveCommand(contex);
        if (cmd == null) {
            logger.debug("无法解析的消息[{}]", content);
            return;
        }
        
        cmd.execute(contex);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param wxMsg x
     * @return x
     */
    private RequestContext createContext(WxMsg wxMsg) {
        RequestContext context = new RequestContext();
        context.setMessage(wxMsg);
        return context;
    }
    
}
