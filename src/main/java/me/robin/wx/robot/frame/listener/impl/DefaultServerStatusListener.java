
package me.robin.wx.robot.frame.listener.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

import me.robin.wx.robot.frame.api.WxApi;
import me.robin.wx.robot.frame.exetor.ExecutorServiceFactory;
import me.robin.wx.robot.frame.listener.ServerStatusListener;
import me.robin.wx.robot.frame.message.GroupMessageHandler;
import me.robin.wx.robot.frame.message.MsgChainHandler;
import me.robin.wx.robot.frame.message.MsgHandler;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.frame.util.WxUtil;

/**
 * Created by xuanlubin on 2017/4/20.
 */
@Component
public class DefaultServerStatusListener implements ServerStatusListener {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(DefaultServerStatusListener.class);
    
    /** FIXME */
    private MsgChainHandler chainHandler = new MsgChainHandler();
    
    /** FIXME */
    @Autowired
    private GroupMessageHandler groupMessageHandler;
    
    /** FIXME */
    private static final int MESSAGE_PROCESS_THREAD = 5;
    
    /** FIXME */
    private ExecutorService messageExecutorService = ExecutorServiceFactory.createExecutorService(MESSAGE_PROCESS_THREAD, "messageProcessThead");
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @PostConstruct
    private void init() {
        this.registerMessageHandler(groupMessageHandler);
    }
    
    @Override
    public void registerMessageHandler(MsgHandler msgHandler) {
        chainHandler.addHandler(msgHandler);
    }
    
    @Override
    public void onUUIDSuccess(String url) {
        logger.info("登录二维码:{}", url);
    }
    
    @Override
    public void onAddMsgList(List<WxMsg> messages, WxApi api) {
        for (WxMsg message : messages) {
            
            WxMsg msg = message;
            if (WxUtil.isGroupMessage(message)) {
                msg = WxGroupMsg.from(msg);
            }
            
            msg.setContent(WxUtil.revertXml(msg.getContent()));
            
            logger.info("收到新消息:{} ", msg);
            messageExecutorService.submit(new MessageHandle(chainHandler, msg));
        }
    }
    
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
    class MessageHandle implements Runnable {
        
        /** FIXME */
        private WxMsg message;
        
        /** FIXME */
        private MsgHandler msgHandler;
        
        /**
         * 构造函数
         * 
         * @param msgHandler x
         * @param message x
         */
        public MessageHandle(MsgHandler msgHandler, WxMsg message) {
            this.message = message;
            this.msgHandler = msgHandler;
        }
        
        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            msgHandler.handle(message);
        }
        
    }
    
    @Override
    public void onModContactList(JSONArray modContactList, WxApi api) {
        logger.info("{}", modContactList);
    }
    
    @Override
    public void onDelContactList(JSONArray delContactList, WxApi api) {
        
    }
    
    @Override
    public void onModChatRoomMemberList(JSONArray modChatRoomMemberList, WxApi api) {
        
    }
}
