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
 * 用户最原始的请求
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
public class BetRequest {
    
    /** FIXME */
    private String game;
    
    /** FIXME */
    private String tream;
    
    /** FIXME */
    private Played played;
    
    /** 下注原始数据，用户输入 */
    private String input;
    
    /** 下注数据 */
    private String data;
    
    /** 下注对象（从下注数据转化而来的） */
    private Object info;
    
    /** 下注额 */
    private BigDecimal money;
    
    /** 时间 */
    private Date betTime;
    
    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }
    
    /**
     * @param game the game to set
     */
    public void setGame(String game) {
        this.game = game;
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
     * @return the info
     */
    public Object getInfo() {
        return info;
    }
    
    /**
     * @param info the info to set
     */
    public void setInfo(Object info) {
        this.info = info;
    }
    
    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }
    
    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }
    
}
