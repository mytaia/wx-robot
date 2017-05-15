/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.model.TermResult;

/**
 * 匹配到所有的球都正确，但
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
public class BallNumMatchPlayed extends WinPlayed {
    
    /**
     * 构造函数
     * 
     * @param gamePlayed x
     */
    public BallNumMatchPlayed(GamePlayed gamePlayed) {
        super(gamePlayed);
        
    }
    
    /**
     * @return the attr
     */
    public String getNum() {
        return gamePlayed.getBallNum();
    }
    
    @Override
    protected GameResultEnum calcResult(Bet bet, TermResult termResult) {
        return GameResultEnum.Lose;
    }
    
}
