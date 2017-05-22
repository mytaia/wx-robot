
package me.robin.wx.robot.frame.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.util.WxUtil;

/** 
 */
public abstract class AbstractMessageHandler implements MsgHandler {
    
    /** FIXME */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /** FIXME */
    @Autowired
    protected WxMessageSender messageSender;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param content x
     */
    protected void sendMessage(String user, String content) {
        messageSender.sendTextMessage(user, content);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param content x
     */
    protected void sendAdminMessage(String user, String content) {
        messageSender.sendTextMessage(user, WxUtil.builddminMessage(content));
    }
    
    /**
     * 是否可以处理此消息
     * 
     * @param msg x
     * @param user x
     * @param content x
     * @return x
     */
    protected boolean canHand(WxMsg msg) {
        return false;
    }
    
}
