
package me.robin.wx.robot.frame.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.Commander;
import me.robin.wx.robot.lot.core.RequestContext;
import me.robin.wx.robot.lot.entity.UserMapper;
import me.robin.wx.robot.lot.service.GroupService;
import me.robin.wx.robot.lot.service.UserMapperService;

/** 
 */
@Component
public class GroupMessageHandler extends AbstractMessageHandler {
    
    /** FIXME */
    @Autowired
    private Commander commander;
    
    /** FIXME */
    @Autowired
    private GroupService groupService;
    
    /** FIXME */
    @Autowired
    private ContactService contactService;
    
    /** FIXME */
    @Autowired
    private UserMapperService userMapperService;
    
    @Override
    public void handle(WxMsg msg) {
        WxGroupMsg message = (WxGroupMsg) msg;
        String content = StringUtils.trim(message.getContent());
        if (StringUtils.isEmpty(content)) {
            return;
        }
        
        String groupName = message.getGroup();
        // 只处理受管的群组消息
        WxGroup group = contactService.getWxGroup(groupName);
        if (group == null || groupService.getByNickName(group.getNickName()) == null) {
            logger.warn("未找到[{}]群的信息", group);
            return;
        }
        
        // 这里如果找不到对应的用户账户，应直接返回，待添加
        if ("?".equals(content) || "？".equals(content)) {
            sendMessage(groupName, "这里是指令的说明:1/2/3表示xxx");
            return;
        }
        
        // 查找用户对应的网盘用户
        String sender = message.getSender();
        WxUser user = contactService.queryUserByUserName(sender);
        UserMapper userMapper = userMapperService.findByNickName(sender, groupName);
        if (userMapper == null) {
            sendMessage(groupName, "@" + user.getNickName() + "没有对应用用户信息，请与管理员联系");
            return;
        }
        
        RequestContext contex = createContext(message);
        contex.setSender(user);
        contex.setUserMapper(userMapper);
        
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
    
    @Override
    protected boolean canHand(WxMsg msg) {
        return (WxConst.MessageType.TEXT == msg.getMsgType()) && (msg instanceof WxGroupMsg);
    }
    
}
