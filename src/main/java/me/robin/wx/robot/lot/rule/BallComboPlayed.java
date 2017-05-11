/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.rule;

import me.robin.wx.robot.lot.constant.BallComboEnum;
import me.robin.wx.robot.lot.constant.GameResultEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.model.TermResult;

/**
 * 组合玩法
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
public class BallComboPlayed extends WinPlayed {
    
    /** FIXME */
    private BallComboEnum ballComboEnum;
    
    /**
     * 构造函数
     * 
     * @param key x
     * @param name x
     * @param game x
     * @param ballComboEnum x
     */
    public BallComboPlayed(String game, String key, String name, BallComboEnum ballComboEnum) {
        super(key, name, game);
        this.ballComboEnum = ballComboEnum;
    }
    
    /**
     * @return the ballComboEnum
     */
    public BallComboEnum getBallComboEnum() {
        return ballComboEnum;
    }
    
    @Override
    protected GameResultEnum calcResult(BetRequest bet, TermResult termResult) {
        
        return null;
    }
    
}
