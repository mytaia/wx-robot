/**create 2017-05-15**/

package me.robin.wx.robot;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    
    /** FIXME */
    private static ApplicationContext applicationContext = null;
    
    /** FIXME */
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    
    /**
     * 取得存储在静态变量中的ApplicationContext.
     * 
     * @return xx
     */
    public static ApplicationContext getApplicationContext() {
        Objects.requireNonNull(applicationContext);
        return applicationContext;
    }
    
    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 
     * @param name xx
     * @return xx
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        Objects.requireNonNull(applicationContext);
        return (T) applicationContext.getBean(name);
    }
    
    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 
     * @param requiredType xx
     * @return xx
     */
    public static <T> T getBean(Class<T> requiredType) {
        Objects.requireNonNull(applicationContext);
        return applicationContext.getBean(requiredType);
    }
    
    /**
     *  
     */
    public static void clearHolder() {
        applicationContext = null;
    }
    
    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }
    
    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }
    
}
