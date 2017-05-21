/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/

package me.robin.wx.robot.event;

import org.springframework.context.ApplicationEvent;

import me.robin.wx.robot.event.bean.LoginStatusEnum;

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
 * @version 2017年5月20日 作者
 */
public class LoginStatusChangeEvent extends ApplicationEvent {
    
    /** FIXME */
    private static final long serialVersionUID = 1L;
    
    /** FIXME */
    private LoginStatusEnum loginStatus;
    
    /** FIXME */
    private Object data;
    
    /**
     * 构造函数
     * 
     * @param loginStatus x
     */
    public LoginStatusChangeEvent(LoginStatusEnum loginStatus) {
        this(loginStatus, null);
    }
    
    /**
     * 构造函数
     * 
     * @param loginStatus x
     * @param data x
     */
    public LoginStatusChangeEvent(LoginStatusEnum loginStatus, Object data) {
        super(loginStatus);
        this.loginStatus = loginStatus;
        this.data = data;
    }
    
    /**
     * @return the loginStatus
     */
    public LoginStatusEnum getLoginStatus() {
        return loginStatus;
    }
    
    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }
}
