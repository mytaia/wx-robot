
package me.robin.wx.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.api.Server;
import me.robin.wx.robot.frame.listener.MessageSendListener;

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
@Component
@SpringBootConfiguration
public class Beans {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(Beans.class);
    
    /** FIXME */
    @Autowired
    private Server server;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    @Bean
    public MessageSendListener messageSendListener() {
        
        MessageSendListener serverStatusListener = new MessageSendListener() {
            
            @Override
            public void userNotFound(String user, String message) {
                
            }
            
            @Override
            public void serverNotReady(String user, String message) {
                server.waitLoginDone();
                server.sendTextMessage(user, message);
            }
            
            @Override
            public void success(String user, String message, String messageId, String localId) {
                logger.debug("发送完成:{} {}", messageId, localId);
            }
            
            @Override
            public void failure(String user, String message) {
                
            }
        };
        
        return serverStatusListener;
    }
    
}
