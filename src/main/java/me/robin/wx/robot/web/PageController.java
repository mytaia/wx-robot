/**create 2017-05-15**/

package me.robin.wx.robot.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.google.common.collect.Lists;

import me.robin.wx.robot.event.LoginStatusChangeEvent;
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
 * @version 2017年5月17日 作者
 */
@Controller
@RequestMapping("/")
public class PageController {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);
    
    /** FIXME */
    @Autowired
    private Server server;
    
    /** FIXME */
    private final List<SseEmitter> emitters = Lists.newCopyOnWriteArrayList();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param model x
     * @return x
     */
    @GetMapping("home")
    public String index(Model model) {
        model.addAttribute("user", server.loginUser());
        model.addAttribute("isLogin", server.isLogin());
        
        return "home";
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    @GetMapping(path = "/stream")
    public SseEmitter stream() {
        
        SseEmitter emitter = new SseEmitter(600000L);
        
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        
        return emitter;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param event x
     */
    @EventListener
    public void onEvent(LoginStatusChangeEvent event) {
        logger.info("收到事件消息{}", event.getSource());
        emitters.forEach((SseEmitter emitter) -> {
            try {
                emitter.send(event);
            } catch (IOException e) {
                emitter.complete();
                e.printStackTrace();
            }
        });
    }
    
}
