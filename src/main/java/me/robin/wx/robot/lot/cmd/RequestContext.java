/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd;

import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.lot.model.BetRequest;

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
 * @version 2017年5月14日 作者
 */
public class RequestContext {
    
    /** FIXME */
    private WxMsg message;
    
    /** FIXME */
    private BetRequest betRequest;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public String getInput() {
        return message.getContent();
    }
    
    /**
     * @return the message
     */
    public WxMsg getMessage() {
        return message;
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(WxMsg message) {
        this.message = message;
    }
    
    /**
     * @return the betRequest
     */
    public BetRequest getBetRequest() {
        return betRequest;
    }
    
    /**
     * @param betRequest the betRequest to set
     */
    public void setBetRequest(BetRequest betRequest) {
        this.betRequest = betRequest;
    }
    
}
