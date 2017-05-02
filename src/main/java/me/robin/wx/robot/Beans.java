package me.robin.wx.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.Server;
import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.listener.MessageSendListener;
import me.robin.wx.robot.frame.listener.ServerStatusListener;
import me.robin.wx.robot.frame.listener.impl.DefaultServerStatusListener;
import me.robin.wx.robot.frame.message.AppMsgHandler;
import me.robin.wx.robot.frame.message.MessageSaveHandler;
import me.robin.wx.robot.frame.message.RevokeMsgHandler;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.frame.service.MessageService;

@Component
@SpringBootConfiguration
public class Beans {

	
	private static final Logger logger = LoggerFactory.getLogger(Beans.class); 
	@Autowired
	private ContactService contactService;
	@Autowired
	private Server server; 

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageSendListener messageSendListener;
 

	@Bean
	public MessageSendListener messageSendListener() {

		MessageSendListener serverStatusListener = new MessageSendListener() {
			@Override
			public void userNotFound(String user, String message) {

			}

			@Override
			public void serverNotReady(String user, String message) {
				server.waitLoginDone();
				server.sendTextMessage(user, message, this);
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

	@Bean
	public ServerStatusListener serverStatusListener() {
		ServerStatusListener serverStatusListener = new DefaultServerStatusListener();
		RevokeMsgHandler revokeMsgHandler = new RevokeMsgHandler(messageSendListener, messageService, contactService);
		revokeMsgHandler.enable("demo");
		serverStatusListener.registerMessageHandler(WxConst.MessageType.REVOKE_MSG, revokeMsgHandler);
		serverStatusListener.registerMessageHandler(WxConst.MessageType.APP_MSG, new AppMsgHandler());

		MessageSaveHandler messageSaveHandler = new MessageSaveHandler(messageService);
		serverStatusListener.registerMessageHandler(WxConst.MessageType.TEXT, messageSaveHandler);
		serverStatusListener.registerMessageHandler(WxConst.MessageType.IMG, messageSaveHandler);
		serverStatusListener.registerMessageHandler(WxConst.MessageType.VIDEO, messageSaveHandler);
		serverStatusListener.registerMessageHandler(WxConst.MessageType.VOICE, messageSaveHandler);

		return serverStatusListener;
	}
}
