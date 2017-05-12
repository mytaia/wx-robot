
package me.robin.wx.robot.lot.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.robin.wx.robot.lot.played.Played;

/**
 * 投注单
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
 * @version 2017年5月13日 作者
 */
@Entity
@Table(name = "lot_bet")
public class Bet extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8220270052951919500L;
    
    /**
     * ID
     */
    @Id
    @Column(length = 40)
    private String betId;
    
    /** 玩法ID */
    @Column(length = 40)
    private String playedId;
    
    /** 玩法 */
    @Transient
    private Played played;
    
    /** 买的球，假如是 一中一，二中二 的话 */
    @Column(length = 40)
    private String ball;
    
    /** 下注额 */
    @Column(precision = 8, scale = 2)
    private BigDecimal betMoney;
    
    /** 赔率 */
    @Column(precision = 8, scale = 2)
    private BigDecimal rate;
    
    /** FIXME */
    @Column(length = 40)
    private String userId;
    
    /** FIXME */
    @Column(length = 40)
    private String tream;
    
    /**
     * @return the betId
     */
    public String getBetId() {
        return betId;
    }
    
    /**
     * @param betId the betId to set
     */
    public void setBetId(String betId) {
        this.betId = betId;
    }
    
    /**
     * @return the playedId
     */
    public String getPlayedId() {
        return playedId;
    }
    
    /**
     * @param playedId the playedId to set
     */
    public void setPlayedId(String playedId) {
        this.playedId = playedId;
    }
    
    /**
     * @return the ball
     */
    public String getBall() {
        return ball;
    }
    
    /**
     * @param ball the ball to set
     */
    public void setBall(String ball) {
        this.ball = ball;
    }
    
    /**
     * @return the betMoney
     */
    public BigDecimal getBetMoney() {
        return betMoney;
    }
    
    /**
     * @param betMoney the betMoney to set
     */
    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }
    
    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }
    
    /**
     * @param rate the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return the played
     */
    public Played getPlayed() {
        return played;
    }
    
    /**
     * @param played the played to set
     */
    public void setPlayed(Played played) {
        this.played = played;
    }
    
    /**
     * @return the tream
     */
    public String getTream() {
        return tream;
    }
    
    /**
     * @param tream the tream to set
     */
    public void setTream(String tream) {
        this.tream = tream;
    }
    
    @Override
    public String toString() {
        return "Bet [betId=" + betId + ", playedId=" + playedId + ", played=" + played + ", ball=" + ball + ", betMoney=" + betMoney + ", rate="
            + rate + ", userId=" + userId + "]";
    }
    
}
