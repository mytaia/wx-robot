
package me.robin.wx.robot.frame.message;

import me.robin.wx.robot.frame.model.WxMsg;

/**
 * Created by xuanlubin on 2017/4/20.
 */
public interface MsgHandler {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param message x
     * @param api x
     */
    void handle(WxMsg message);
}
