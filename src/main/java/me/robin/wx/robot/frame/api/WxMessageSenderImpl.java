/******************************************************************************
 * create by 2017年5月15日
 ******************************************************************************/

package me.robin.wx.robot.frame.api;

import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Queues;

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
 * @version 2017年5月15日 作者
 */
@Component
public class WxMessageSenderImpl implements WxMessageSender {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(WxMessageSenderImpl.class);
    
    /** 消息至少间隔的时间 */
    private static final int INTERVAL = 1;
    
    /** FIXME */
    @Autowired
    private Server server;
    
    /** FIXME */
    private BlockingQueue<Runnable> messages = Queues.newArrayBlockingQueue(10000);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @PostConstruct
    private void init() {
        thread.start();
    }
    
    /** FIXME */
    private Thread thread = new Thread(new Runnable() {
        
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable runable = messages.take();
                    runable.run();
                } catch (Exception e) {
                    logger.error("发送微信消息失败", e);
                }
                try {
                    Thread.sleep(INTERVAL * 1000);
                } catch (InterruptedException e) {
                    
                }
            }
        }
    }, "wxSendMessage");
    
    @Override
    public void sendMessage(String user, String message, int type) {
        try {
            messages.put(new Runnable() {
                
                @Override
                public void run() {
                    server.sendTextMessage(user, message);
                }
            });
        } catch (InterruptedException e) {
            logger.error("发送微信消息队列时异常", e);
        }
    }
    
}
