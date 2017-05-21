/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/

package me.robin.wx.robot.web.velocity;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.velocity.tools.config.DefaultKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@DefaultKey("shiro")
public class ShiroTool {
    
    /** FIXME */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /** FIXME */
    // public static ThreadLocal<String> ThreadLocal = new ThreadLocal<String>();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @return String
     */
    public String userName() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getSession().getAttribute("USER_OBJECT");
        if (userName == null || userName.length() == 0) {
            userName = principal("userName");
            subject.getSession().setAttribute("USER_OBJECT", userName);
        }
        return userName;
    }
    
    /**
     * 获取登录用户的一些重要信息
     * 
     * @param property 属性名称
     * @return 用户的属性值
     */
    public String principal(String property) {
        String strValue = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            // Get the principal to print out
            Object principal;
            
            principal = subject.getPrincipal();
            
            // Get the string value of the principal
            if (principal != null) {
                if (property == null) {
                    strValue = principal.toString();
                } else {
                    strValue = getPrincipalProperty(principal, property);
                }
            }
            
        }
        
        return strValue;
    }
    
    /**
     * 判断是否有权限
     * 
     * @param name 权限名称
     * @return 是否有权限
     */
    public boolean hasPermission(String name) {
        if (StringUtils.isEmpty(name)) {
            return true;
        }
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(name);
    }
    
    /**
     * 判断是否有角色
     * 
     * @param roleName 角色名称
     * @return 是否有权限
     */
    public boolean hasRole(String roleName) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.hasRole(roleName);
    }
    
    /**
     * 判断是否是登录用户
     * 
     * @return 是否是登录用户
     */
    public boolean user() {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.getPrincipal() != null;
    }
    
    /**
     * 判断是否是游客
     * 
     * @return 是否是游客
     */
    public boolean guest() {
        Subject subject = SecurityUtils.getSubject();
        return subject == null || subject.getPrincipal() == null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param principal principal
     * @param property property
     * @return value
     */
    private String getPrincipalProperty(Object principal, String property) {
        String strValue = null;
        
        try {
            BeanInfo bi = Introspector.getBeanInfo(principal.getClass());
            
            // Loop through the properties to get the string value of the specified property
            boolean foundProperty = false;
            for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
                if (pd.getName().equals(property)) {
                    Object value = pd.getReadMethod().invoke(principal, (Object[]) null);
                    strValue = String.valueOf(value);
                    foundProperty = true;
                    break;
                }
            }
            
            if (!foundProperty) {
                final String message = "Property [" + property + "] not found in principal of type [" + principal.getClass().getName() + "]";
                logger.error(message);
            }
            
        } catch (Exception e) {
            final String message = "Error reading property [" + property + "] from principal of type [" + principal.getClass().getName() + "]";
            logger.error(message);
        }
        
        return strValue;
    }
    
}
