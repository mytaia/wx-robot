
package me.robin.wx.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import me.robin.wx.robot.frame.api.Server;

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
 * @version 2017年5月3日 作者
 */
@SpringBootApplication
@ImportResource(value="classpath:*applicationContext.xml")
public class Application {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param args x
     */
    public static void main(String[] args) {
        System.setProperty("jsse.enableSNIExtension", "false");
        ApplicationContext context = SpringApplication.run(Application.class, args);
        Server server = context.getBean(Server.class);
        server.run();
    }
    
}
