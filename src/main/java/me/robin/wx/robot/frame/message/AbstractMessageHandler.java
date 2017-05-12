
package me.robin.wx.robot.frame.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.robin.wx.robot.frame.api.Server;
import me.robin.wx.robot.frame.util.WxUtil;

/** 
 */
public abstract class AbstractMessageHandler implements MsgHandler {
    
    /** FIXME */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /** FIXME */
    @Autowired
    protected Server server;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param content x
     */
    protected void sendMessage(String user, String content) {
        server.sendTextMessage(user, content);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param content x
     */
    protected void sendAdminMessage(String user, String content) {
        server.sendTextMessage(user, WxUtil.builddminMessage(content));
    }
}
