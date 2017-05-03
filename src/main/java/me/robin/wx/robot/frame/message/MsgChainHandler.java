
package me.robin.wx.robot.frame.message;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.robin.wx.robot.frame.model.WxMsg;

/**
 * Created by xuanlubin on 2017/4/20.
 */
public class MsgChainHandler implements MsgHandler {
    
    private static Logger logger = LoggerFactory.getLogger(MsgChainHandler.class);
    
    private List<MsgHandler> msgHandlers = new ArrayList<>();
    
    @Override
    public void handle(WxMsg message) {
        List<MsgHandler> handlers = new ArrayList<>(msgHandlers);
        for (MsgHandler msgHandler : handlers) {
            try {
                msgHandler.handle(message);
            } catch (Exception e) {
                logger.error("消息处理器[{}]异常", msgHandler.getClass(), e);
            }
        }
    }
    
    public void addHandler(MsgHandler msgHandler) {
        if (msgHandler.equals(this)) {
            return;
        }
        if (msgHandlers.contains(msgHandler)) {
            return;
        }
        msgHandlers.add(msgHandler);
    }
    
    public void removeHandler(MsgHandler msgHandler) {
        if (msgHandler.equals(this)) {
            return;
        }
        msgHandlers.remove(msgHandler);
    }
}
