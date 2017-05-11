/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * @version 2017年5月12日 作者
 */
public class Bet {
    
    /** FIXME */
    private Played played;
    
    /** FIXME */
    private String tream;
    
    /** 下注数据 */
    private String data;
    
    /** 下注额 */
    private BigDecimal money;
    
    /** 时间 */
    private Date betTime;
    
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
    
    /**
     * @return the data
     */
    public String getData() {
        return data;
    }
    
    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }
    
    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    /**
     * @return the betTime
     */
    public Date getBetTime() {
        return betTime;
    }
    
    /**
     * @param betTime the betTime to set
     */
    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }
    
}
