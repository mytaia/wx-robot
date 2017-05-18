/**create 2017-05-15**/

package me.robin.wx.robot.frame.service;

import me.robin.wx.robot.frame.model.WxMsg;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2017年5月17日 作者
 */
public interface MessageService {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param fromUserName x
     * @param messageId x
     * @return x
     */
    WxMsg findMessageByUserAndMid(String fromUserName, String messageId);
    
}
