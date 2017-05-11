/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.rule;

import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.model.BetResult;
import me.robin.wx.robot.lot.model.Played;
import me.robin.wx.robot.lot.model.TermResult;

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
public abstract class WinPlayed extends Played {
    
    /**
     * 构造函数
     * 
     * @param game x
     * @param key x
     * @param name x
     */
    public WinPlayed(String game, String key, String name) {
        super(game, key, name);
    }
    
    /**
     * 处理结果
     * 
     * @param bet x
     * @param termResult x
     * @return x
     */
    @Override
    public BetResult process(BetRequest bet, TermResult termResult) {
        BetResult betResult = new BetResult();
        betResult.setBet(bet);
        betResult.setResult(calcResult(bet, termResult));
        return betResult;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param bet x
     * @param termResult x
     * @return x
     */
    protected abstract GameResultEnum calcResult(BetRequest bet, TermResult termResult);
}
