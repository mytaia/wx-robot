/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.robin.wx.robot.compant.WebClient;
import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.lot.core.BetRequest;
import me.robin.wx.robot.lot.core.RequestContext;
import me.robin.wx.robot.lot.entity.Bet;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

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
public class BetCommand implements Command {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(BetCommand.class);
    
    /** FIXME */
    @Autowired
    private WxMessageSender messageSender;
    
    /** FIXME */
    @Autowired
    private ContactService contactService;
    
    @Override
    public void execute(RequestContext context) {
        BetRequest betRequest = (BetRequest) context.getMessageRequest();
        logger.info("投注指令的处理" + betRequest);
        // 向网盘提交,要做一些处理
        List<Bet> bets = betRequest.getPlayed().extractBet(betRequest);
        String dataList = toJson(bets);
        WxGroupMsg msg = (WxGroupMsg) context.getMessage();
        String group = msg.getGroup();
        String sender = msg.getSender();
        WxUser user = contactService.queryUser(sender);
        String userName = null;
        if (user != null) {
            userName = user.getRemarkName();
        }
        
        if (userName == null) {
            logger.warn("用户{}没有设置对应的账号，投注[{}]被忽略", user.getNickName(), betRequest.getInput());
            return;
        }
        
        Request req = new Request.Builder() //
            .url("http://localhost/rest/lot!lot.action") //
            .post(new FormBody.Builder() //
                .add("dataList", dataList) //
                .add("operId", userName)//
                .build() //
            ).build();
            
        try {
            Response response = WebClient.instance.execute(req);
            String json = response.body().string();
            WangPanRespon res = JSON.parseObject(json, WangPanRespon.class);
            String strMsg = null;
            if (res.code == 1) {
                // 成功
                logger.info("投注成功");
                strMsg = String.format("@%s您的%s投注成功,账户余额为%s", userName, betRequest.getPlayed().getName(), res.yuer);
            } else {
                logger.error("向网盘提交投注时失败 :{}", res.msg);
                strMsg = String.format("@%s您的%s投注失败,原因是 ： %s", userName, betRequest.getPlayed().getName(), res.msg);
            }
            messageSender.sendTextMessage(group, strMsg);
        } catch (IOException e) {
            logger.error("提交下注数据时异常", e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param bets x
     * @return x
     */
    private String toJson(List<Bet> bets) {
        // lotTypeId: "k10_1z1_a",
        // lotType: "快乐十分",
        // pan: "A",
        // ballType: "一中一",
        // ball: "11",
        // ratio: "2.2",
        // betMoney: "10"
        
        List<Map<String, String>> list = Lists.newArrayList();
        for (Bet bet : bets) {
            Map<String, String> map = Maps.newHashMap();
            map.put("lotTypeId", bet.getPlayedId().replace(":", "_") + "_a");
            map.put("betMoney", bet.getBetMoney().toPlainString());
            map.put("ball", bet.getBall());
            list.add(map);
        }
        
        return JSON.toJSONString(list);
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
     * @version 2017年5月17日 作者
     */
    static class WangPanRespon {
        
        /** FIXME */
        public String msg;
        
        /** FIXME */
        public BigDecimal totalsum;
        
        /** FIXME */
        public String yuer;
        
        /** FIXME */
        public int code;
    }
    
}
