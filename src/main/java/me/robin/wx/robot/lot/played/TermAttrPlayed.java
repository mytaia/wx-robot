/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.model.TermResult;

/**
 * 全盘玩法
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
public class TermAttrPlayed extends WinPlayed {
    
    /** FIXME */
    private TermAttrEnum attr;
    
    /**
     * 构造函数
     * 
     * @param gamePlayed x
     */
    public TermAttrPlayed(GamePlayed gamePlayed) {
        super(gamePlayed);
        this.attr = TermAttrEnum.fromCode(gamePlayed.getCode());
    }
    
    /**
     * @return the attr
     */
    public TermAttrEnum getAttr() {
        return attr;
    }
    
    @Override
    protected GameResultEnum calcResult(Bet bet, TermResult termResult) {
        
        switch (attr) {
            case ZhongHeDa:
                
                break;
            default:
                break;
        }
        
        return null;
    }
    
}
