
package me.robin.wx.robot.frame.api;

import me.robin.wx.robot.frame.WxConst;

/**
 * Created by xuanlubin on 2017/4/24.
 */
public interface WxMessageSender {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     */
    default void sendTextMessage(String user, String message) {
        sendMessage(user, message, WxConst.MessageType.TEXT);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     * @param type x
     */
    void sendMessage(String user, String message, int type);
    
}
