
package me.robin.wx.robot.frame.model.req;

import me.robin.wx.robot.frame.model.LoginUser;
import me.robin.wx.robot.frame.util.WxUtil;

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
 * @version 2017年5月4日 作者
 */
public class WxBaseRequest {
    
    /** FIXME */
    public String DeviceID;
    
    /** FIXME */
    public String Sid;
    
    /** FIXME */
    public String Skey;
    
    /** FIXME */
    public long Uin;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param user x
     * @return x
     */
    public static WxBaseRequest from(LoginUser user) {
        WxBaseRequest br = new WxBaseRequest();
        br.Uin = Long.parseLong(user.getUin());
        br.Sid = user.getSid();
        br.Skey = user.getSkey();
        br.DeviceID = WxUtil.randomDeviceId();
        return br;
    }
}
