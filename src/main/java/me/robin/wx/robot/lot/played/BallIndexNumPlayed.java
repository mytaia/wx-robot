/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import me.robin.wx.robot.lot.BallUtils;
import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.core.TermResult;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;

/**
 * 第几球是几号
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
public class BallIndexNumPlayed extends WinPlayed {
    
    /**
     * 构造函数
     * 
     * @param gamePlayed x
     */
    public BallIndexNumPlayed(GamePlayed gamePlayed) {
        super(gamePlayed);
    }
    
    /**
     * @return the ballIndex
     */
    public int getBallIndex() {
        return gamePlayed.getBallIndex();
    }
    
    /**
     * @return the attr
     */
    public String getNum() {
        return gamePlayed.getBallNum();
    }
    
    @Override
    protected GameResultEnum calcResult(Bet bet, TermResult termResult) {
        
        return BallUtils.ballEquals(termResult.getBalls()[getBallIndex() - 1], getNum()) ? GameResultEnum.Win : GameResultEnum.Lose;
    }
    
}
