
package me.robin.wx.robot.lot.model;

import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.entity.Bet;

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
 * @version 2017年5月11日 作者
 */
public class BetResult {
    
    /** FIXME */
    private Bet bet;
    
    /** FIXME */
    private GameResultEnum result;
    
    /**
     * @return the result
     */
    public GameResultEnum getResult() {
        return result;
    }
    
    /**
     * @param result the result to set
     */
    public void setResult(GameResultEnum result) {
        this.result = result;
    }
    
    /**
     * @return the bet
     */
    public Bet getBet() {
        return bet;
    }
    
    /**
     * @param bet the bet to set
     */
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    
}
