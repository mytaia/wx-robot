/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd.resolver.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.WangPanCommandSupport;
import me.robin.wx.robot.lot.core.RequestContext;
import me.robin.wx.robot.lot.core.ServiceRequest;

/**
 * 下注的指令处理器
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
 * @version 2017年5月14日 作者
 */
@Component
public class ServiceCommand extends WangPanCommandSupport implements Command {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(ServiceCommand.class);
    
    /** FIXME */
    @Value("${wanpan.service.url}")
    private String wanpanServiceUrl;
    
    /** FIXME */
    @Autowired
    private WxMessageSender messageSender;
    
    @Override
    public void execute(RequestContext context) {
        ServiceRequest req = (ServiceRequest) context.getMessageRequest();
        logger.info("服务指令的处理，{}", req);
        
        WxGroupMsg msg = (WxGroupMsg) context.getMessage();
        String group = msg.getGroup();
        
        WxUser sender = context.getSender();
        String nickName = sender.getNickName();
        String exUserId = sender.getExUserId();
        
        if (exUserId == null) {
            logger.warn("用户{}没有设置对应的账号，服务指令[{}]被忽略", nickName, context.getInput());
            return;
        }
        
        Map<String, Object> map = ImmutableMap.<String, Object> of( //
            "serviceName", req.getServicCommandeEnum().code(), //
            "operId", exUserId //
        );
        
        try {
            WangPanRespon res = this.doRequest(wanpanServiceUrl, map);
            String strMsg = null;
            if (res.code == 1) {
                // 成功
                strMsg = String.format("@%s指令成功处理,%s", exUserId, res.msg);
            } else {
                logger.error("向网盘提交服务指令时失败 :{}", res.msg);
                strMsg = String.format("@%s指令处理失败,原因是 ： %s", exUserId, res.msg);
            }
            messageSender.sendTextMessage(group, strMsg);
        } catch (IOException e) {
            logger.error("提交服务指令时异常", e);
        }
    }
    
}
