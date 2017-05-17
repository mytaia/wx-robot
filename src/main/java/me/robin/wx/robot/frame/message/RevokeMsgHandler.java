
package me.robin.wx.robot.frame.message;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import me.robin.wx.robot.frame.listener.MessageSendListener;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.frame.service.MessageService;
import me.robin.wx.robot.frame.util.WxUtil;

/**
 * Created by xuanlubin on 2017/4/20.
 */
public class RevokeMsgHandler implements MsgHandler {
    
    private MessageSendListener messageSendListener;
    
    private MessageService messageService;
    
    private ContactService contactService;
    
    private Set<String> enableUserSet = new HashSet<>();
    
    public RevokeMsgHandler(MessageSendListener messageSendListener, MessageService messageService, ContactService contactService) {
        this.messageSendListener = messageSendListener;
        this.messageService = messageService;
        this.contactService = contactService;
    }
    
    /**
     * 添加启用防撤销的用户
     *
     * @param user
     */
    public void enable(String user) {
        enableUserSet.add(user);
    }
    
    @Override
    public void handle(WxMsg message) {
        WxUser wxUser = contactService.queryUserByUserName(message.getFromUserName());
        if (null != wxUser) {
            if (!enableUserSet.contains(wxUser.getNickName()) && !enableUserSet.contains(wxUser.getAlias())
                && !enableUserSet.contains(wxUser.getRemarkName())) {
                return;
            }
        }
        String sendToUserName = message.getFromUserName();
        String messageId = WxUtil.getValueFromXml(WxUtil.revertXml(message.getContent()), "msgid");
        
        WxMsg wxMsg = this.messageService.findMessageByUserAndMid(message.getFromUserName(), messageId);
        String messageContent;
        if (null != wxMsg) {
            String appendContent;
            if (wxMsg instanceof WxGroupMsg) {
                WxUser sendUser = contactService.queryUserByUserName(((WxGroupMsg) wxMsg).getSender());
                if (null != sendUser) {
                    messageContent = sendUser.getNickName() + " 撤销了这条消息:";
                    appendContent = wxMsg.getContent();
                } else {
                    messageContent = "楼上撤销了这条消息:";
                    appendContent = wxMsg.getContent();
                }
            } else {
                messageContent = "撤销无效:";
                appendContent = wxMsg.getContent();
            }
            if (StringUtils.startsWith(appendContent, "<br/>")) {
                appendContent = StringUtils.substring(appendContent, 5);
            }
            messageContent += StringUtils.replace(appendContent, "<br/>", "\r\n");
        } else {
            messageContent = "真幸运没找到楼上撤销的消息";
        }
        // api.sendTextMessage(sendToUserName, messageContent);
    }
}
