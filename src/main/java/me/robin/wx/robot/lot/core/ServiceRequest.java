
package me.robin.wx.robot.lot.core;

import me.robin.wx.robot.lot.constant.ServicCommandeEnum;

/**
 * 用户最原始的请求
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
 * @version 2017年5月11日 作者
 */
public class ServiceRequest extends MessageRequest {
    
    /** FIXME */
    private ServicCommandeEnum servicCommandeEnum;
    
    /**
     * @return the servicCommandeEnum
     */
    public ServicCommandeEnum getServicCommandeEnum() {
        return servicCommandeEnum;
    }
    
    /**
     * @param servicCommandeEnum the servicCommandeEnum to set
     */
    public void setServicCommandeEnum(ServicCommandeEnum servicCommandeEnum) {
        this.servicCommandeEnum = servicCommandeEnum;
    }
    
    @Override
    public String toString() {
        return "ServiceRequest [servicCommandeEnum=" + servicCommandeEnum + "]";
    }
    
}
