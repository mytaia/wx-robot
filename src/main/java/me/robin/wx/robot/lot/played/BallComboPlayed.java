/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.google.common.collect.Lists;

import me.robin.wx.robot.lot.BallUtils;
import me.robin.wx.robot.lot.constant.BallComboEnum;
import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.core.BetRequest;
import me.robin.wx.robot.lot.core.TermResult;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;

/**
 * 组合玩法
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
 * @version 2017年5月11日 作者
 */
public class BallComboPlayed extends WinPlayed {
    
    /** FIXME */
    private BallComboEnum ballComboEnum;
    
    /**
     * 构造函数
     * 
     * @param gamePlayed x
     */
    public BallComboPlayed(GamePlayed gamePlayed) {
        super(gamePlayed);
        ballComboEnum = BallComboEnum.fromCode(this.gamePlayed.getCode());
    }
    
    /**
     * @return the ballComboEnum
     */
    public BallComboEnum getBallComboEnum() {
        return ballComboEnum;
    }
    
    @Override
    protected GameResultEnum calcResult(Bet bet, TermResult termResult) {
        
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @return x
     */
    @Override
    public List<Bet> extractBet(BetRequest request) {
        String[] arr = (String[]) request.getInfo();
        List<Bet> bets = Lists.newArrayList();
        List<String[]> ballsList = BallUtils.comboSelect(arr, ballComboEnum.getCount());
        int count = ballsList.size();
        BigDecimal money = request.getBetMoney().divide(new BigDecimal(count), 2, RoundingMode.DOWN);
        BigDecimal lastMoney = request.getBetMoney().subtract(money.multiply(new BigDecimal(count - 1)));
        for (int i = 0; i < count; i++) {
            Bet bet = new Bet();
            bet.setPlayed(request.getPlayed());
            bet.setBetMoney(i < count - 1 ? money : lastMoney);
            bet.setPlayedId(request.getPlayed().getPlayedId());
            bet.setBall(String.join(",", ballsList.get(i)));
            bets.add(bet);
        }
        return bets;
    }
    
}
