/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd.resolver.bet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxGroupMsg;
import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.WangPanCommandSupport;
import me.robin.wx.robot.lot.core.BetRequest;
import me.robin.wx.robot.lot.core.RequestContext;
import me.robin.wx.robot.lot.entity.Bet;

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
public class BetCommand extends WangPanCommandSupport implements Command {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(BetCommand.class);
    
    /** FIXME */
    @Autowired
    private WxMessageSender messageSender;
    
    /** FIXME */
    @Value("${wanpan.bet.url}")
    private String betUrl;
    
    @Override
    public void execute(RequestContext context) {
        BetRequest betRequest = (BetRequest) context.getMessageRequest();
        logger.info("投注指令的处理" + betRequest);
        // 向网盘提交,要做一些处理
        List<Bet> bets = betRequest.getPlayed().extractBet(betRequest);
        String dataList = toJson(bets);
        WxGroupMsg msg = (WxGroupMsg) context.getMessage();
        String group = msg.getGroup();
        
        String nickName = context.getSender().getNickName();
        String exUserId = context.getUserMapper().getExUserId();
        
        if (exUserId == null) {
            logger.warn("用户{}没有设置对应的账号，投注[{}]被忽略", nickName, betRequest.getInput());
            return;
        }
        
        Map<String, Object> map = ImmutableMap.of( //
            "dataList", dataList, //
            "operId", exUserId //
        );
        
        try {
            WangPanRespon res = this.doRequest(betUrl, map);
            String strMsg = null;
            if (res.code == 1) {
                logger.info("投注成功");
                strMsg = String.format("@%s您的%s投注成功,账户余额为%s", exUserId, betRequest.getPlayed().getName(), res.yuer);
            } else {
                logger.error("向网盘提交投注时失败 :{}", res.msg);
                strMsg = String.format("@%s您的%s投注失败,原因是 ： %s", exUserId, betRequest.getPlayed().getName(), res.msg);
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
    
}
