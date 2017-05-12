
package me.robin.wx.robot.lot.played;

import java.util.Arrays;
import java.util.List;

import me.robin.wx.robot.lot.entity.Bet;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.model.BetResult;
import me.robin.wx.robot.lot.model.TermResult;

/**
 * 玩法
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
public abstract class Played {
    
    /** FIXME */
    protected GamePlayed gamePlayed;
    
    /**
     * 构造函数
     * 
     * @param gamePlayed x
     */
    public Played(GamePlayed gamePlayed) {
        super();
        this.gamePlayed = gamePlayed;
    }
    
    /**
     * 处理结果
     * 
     * @param bet x
     * @param termResult x
     * @return x
     */
    public abstract BetResult process(Bet bet, TermResult termResult);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @return x
     */
    public List<Bet> extractBet(BetRequest request) {
        Bet bet = new Bet();
        bet.setPlayed(request.getPlayed());
        bet.setBetMoney(request.getMoney());
        bet.setPlayedId(request.getPlayed().getPlayedId());
        return Arrays.asList(bet);
    }
    
    /**
     * @return the key
     */
    public String getCode() {
        return gamePlayed.getCode();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return gamePlayed.getName();
    }
    
    /**
     * @return the game
     */
    public String getGame() {
        return gamePlayed.getGame();
    }
    
    /**
     * @return the playedId
     */
    public String getPlayedId() {
        return gamePlayed.getPlayedId();
    }
    
    @Override
    public String toString() {
        return "Played [gamePlayed=" + gamePlayed + "]";
    }
    
}
