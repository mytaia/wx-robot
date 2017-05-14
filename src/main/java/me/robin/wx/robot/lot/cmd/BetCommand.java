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
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.robin.wx.robot.lot.compant.WebClinet;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.model.BetRequest;
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
    
    @Override
    public void execute(RequestContext context) {
        BetRequest request = context.getBetRequest();
        logger.info("投注指令的处理" + request);
        // 向网盘提交,要做一些处理
        List<Bet> bets = request.getPlayed().extractBet(request);
        String dataList = toJson(bets);
        
        Request req = new Request.Builder() //
            .url("http://localhost/rest/lot!lot.action") //
            .post(new FormBody.Builder() //
                .add("dataList", dataList) //
                .add("operId", context.getMessage().getFromUserName()).build() //
            ).build();
            
        try {
            Response response = WebClinet.instance.execute(req);
            String json = response.body().string();
            WangPanRespon res = JSON.parseObject(json, WangPanRespon.class);
            if (res.code == 1) {
                // 成功
                logger.error("投注成功");
            } else {
                logger.error("向网盘提交投注时失败 :{}", res.msg);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            list.add(map);
        }
        
        return JSON.toJSONString(list);
    }
    
    static class WangPanRespon {
        
        public String msg;
        
        public BigDecimal totalsum;
        
        public String yuer;
        
        public int code;
    }
    
}
