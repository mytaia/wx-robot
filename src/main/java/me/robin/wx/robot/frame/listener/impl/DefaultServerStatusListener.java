
package me.robin.wx.robot.frame.listener.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.api.WxApi;
import me.robin.wx.robot.frame.exetor.ExecutorServiceFactory;
import me.robin.wx.robot.frame.listener.ServerStatusListener;
import me.robin.wx.robot.frame.message.MsgChainHandler;
import me.robin.wx.robot.frame.message.MsgHandler;
import me.robin.wx.robot.frame.message.TextMessageHandler;
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
    private Map<Integer, MsgChainHandler> handlerMap = new ConcurrentHashMap<>();
    
    /** FIXME */
    @Autowired
    private TextMessageHandler textMessageHandler;
    
    /** FIXME */
    private ExecutorService messageExecutorService = ExecutorServiceFactory.createExecutorService(50, "messageProcessThead");
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @PostConstruct
    private void init() {
        this.registerMessageHandler(WxConst.MessageType.TEXT, textMessageHandler);
    }
    
    @Override
    public void registerMessageHandler(int msgType, MsgHandler msgHandler) {
        handlerMap.computeIfAbsent(msgType, s -> new MsgChainHandler()).addHandler(msgHandler);
    }
    
    @Override
    public void onUUIDSuccess(String url) {
        logger.info("登录二维码:{}", url);
    }
    
    @Override
    public void onAddMsgList(JSONArray addMsgList, WxApi api) {
        for (int i = 0; i < addMsgList.size(); i++) {
            logger.info("收到新消息:{} ", addMsgList.getJSONObject(i));
            WxMsg message = addMsgList.getObject(i, WxMsg.class);
            String MsgId = message.getMsgID();
            String FromUserName = message.getFromUserName();
            String ToUserName = message.getToUserName();
            message.setContent(WxUtil.revertXml(message.getContent()));
            String Content = message.getContent();
            int msgType = message.getMsgType();
            logger.info("收到新消息:{} {} {} {} {}", MsgId, FromUserName, ToUserName, Content, msgType);
            MsgHandler msgHandler = this.handlerMap.get(msgType);
            if (null == msgHandler) {
                logger.info("没有定义消息处理器 msgType:{}", msgType);
                continue;
            }
            
            messageExecutorService.submit(new MessageHandle(msgHandler, message));
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
        
    }
    
    @Override
    public void onDelContactList(JSONArray delContactList, WxApi api) {
        
    }
    
    @Override
    public void onModChatRoomMemberList(JSONArray modChatRoomMemberList, WxApi api) {
        
    }
}
