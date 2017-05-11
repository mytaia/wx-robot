/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.domain.k10;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.BallUtils;
import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.model.Played;
import me.robin.wx.robot.lot.model.Playeds;
import me.robin.wx.robot.lot.rule.BallAttrPlayed;
import me.robin.wx.robot.lot.rule.BallNumPlayed;
import me.robin.wx.robot.lot.rule.TermAttrPlayed;

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
@Component
public class K10Playeds extends Playeds {
    
    /** FIXME */
    private static final int BALL_COUNT = 5;
    
    /** FIXME */
    private static final int BALL_MAX_NUM = 21;
    
    /**
     * 构造函数
     * 
     * @param game x
     */
    public K10Playeds() {
        super(GameEnum.K10.id());
    }
    
    /**
     * 所有的玩法
     */
    @PostConstruct
    private void loadPlayeds() {
        
        // 获取所有的玩法;
        //
        createTermAttrPlayed(TermAttrEnum.values());
        //
        createBallAttrPlayed(BallAttrEnum.values());
        //
        createBallNumPlayed();
        //
        createBallComboPlayed();
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    private void createBallComboPlayed() {
        // TODO 自动生成方法存根注释，方法实现时请删除此注释
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    private void createTermAttrPlayed(TermAttrEnum[] items) {
        for (TermAttrEnum item : items) {
            Played played = new TermAttrPlayed(this.getGame(), item.id(), item.description(), item);
            this.addPlayed(played);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param item x
     * @return x
     */
    public Played getTermAttrPlayed(TermAttrEnum item) {
        return this.getPlayed(item.id());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    private void createBallAttrPlayed(BallAttrEnum[] items) {
        for (int i = 0; i < BALL_COUNT; i++) {
            for (BallAttrEnum item : items) {
                Played played = new BallAttrPlayed(this.getGame(), i, item);
                this.addPlayed(played);
            }
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    private void createBallNumPlayed() {
        for (int i = 0; i < BALL_COUNT; i++) {
            for (int n = 1; n <= BALL_MAX_NUM; n++) {
                String num = BallUtils.ballNum(n, 2);
                Played played = new BallNumPlayed(this.getGame(), "", "", i, num);
                this.addPlayed(played);
            }
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param ballIndex x
     * @param ballNum x
     * @return x
     */
    public Played getBallNumPlayed(String ballIndex, String ballNum) {
        
        // 得到KEY
        return null;
    }
    
}
