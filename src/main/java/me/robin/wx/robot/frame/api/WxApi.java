
package me.robin.wx.robot.frame.api;

import me.robin.wx.robot.frame.model.LoginUser;

/**
 * Created by xuanlubin on 2017/4/24.
 */
public interface WxApi extends WxMessageSender {
    
    LoginUser loginUser();
    
    default void createGroup() {
    }
    
    default void modifyGroupName() {
    }
    
    default void deleteGroupUser() {
    }
}
