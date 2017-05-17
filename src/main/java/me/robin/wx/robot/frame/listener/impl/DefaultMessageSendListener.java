/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.frame.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.Beans;
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
 * @version 2017年5月17日 作者
 */
@Component 
public class DefaultMessageSendListener implements MessageSendListener {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(Beans.class);
    
    /** FIXME */
    @Autowired
    private Server server;
    
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
    
}
