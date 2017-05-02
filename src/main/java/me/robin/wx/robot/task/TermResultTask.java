package me.robin.wx.robot.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.frame.Server;
import me.robin.wx.robot.frame.listener.MessageSendListener;

@Component
public class TermResultTask {
	
	@Autowired
	private Server server; 
	
	@Autowired
	private MessageSendListener messageSendListener;

	@Scheduled(cron="0 10 * * * ?")
	public void sendTermResult(){

	    server.sendTextMessage("AgFighter", "消息发送：" + new Date(), messageSendListener);
	}
}
