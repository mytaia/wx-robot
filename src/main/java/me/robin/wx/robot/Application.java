package me.robin.wx.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import me.robin.wx.robot.frame.Server;

@SpringBootApplication
public class Application {
	static {
		System.setProperty("jsse.enableSNIExtension", "false");
	} 
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class); 

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(Application.class, args); 
		Server server = context.getBean(Server.class);
		server.run();
	}

	 
}
