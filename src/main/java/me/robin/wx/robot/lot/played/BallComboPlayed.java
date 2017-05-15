/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import java.util.Arrays;
import java.util.List;

import me.robin.wx.robot.lot.constant.BallComboEnum;
import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.model.TermResult;

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
        
        Bet bet = new Bet();
        bet.setPlayed(bet.getPlayed());
        bet.setBetMoney(bet.getBetMoney());
        bet.setPlayedId(bet.getPlayedId());
        return Arrays.asList(bet);
    }
    
}
