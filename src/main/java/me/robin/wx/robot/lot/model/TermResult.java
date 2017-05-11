/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.model;

/**
 * 每期的开奖结果
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
public class TermResult {
    
    /** FIXME */
    private String game;
    
    /** FIXME */
    private String term;
    
    /** FIXME */
    private String[] balls;
    
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
     * @return the balls
     */
    public String[] getBalls() {
        return balls;
    }
    
    /**
     * @param balls the balls to set
     */
    public void setBalls(String[] balls) {
        this.balls = balls;
    }
    
    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }
    
    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }
    
}
