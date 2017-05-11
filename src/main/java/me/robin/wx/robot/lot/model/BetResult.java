/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.model;

import me.robin.wx.robot.lot.constant.GameResultEnum;

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
    private BetRequest bet;
    
    /** FIXME */
    private GameResultEnum result;
    
    /**
     * @return the bet
     */
    public BetRequest getBet() {
        return bet;
    }
    
    /**
     * @param bet the bet to set
     */
    public void setBet(BetRequest bet) {
        this.bet = bet;
    }
    
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
    
}
