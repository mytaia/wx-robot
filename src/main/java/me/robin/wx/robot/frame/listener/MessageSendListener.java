
package me.robin.wx.robot.frame.listener;

/**
 * Created by xuanlubin on 2017/4/19.
 */
public interface MessageSendListener {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     */
    void userNotFound(String user, String message);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     */
    void serverNotReady(String user, String message);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     * @param messageId x
     * @param localId x
     */
    void success(String user, String message, String messageId, String localId);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @param message x
     */
    void failure(String user, String message);
}
