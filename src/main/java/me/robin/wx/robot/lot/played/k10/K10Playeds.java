/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.played.k10;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.played.Played;
import me.robin.wx.robot.lot.played.Playeds;
import me.robin.wx.robot.lot.played.factory.PlayedFactory;
import me.robin.wx.robot.lot.service.GamePlayedService;

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
public class K10Playeds extends Playeds implements InitializingBean {
    
    /** FIXME */
    @Autowired
    private GamePlayedService playedService;
    
    /** FIXME */
    @Autowired
    private ApplicationContext applicationContext;
    
    /**
     * 构造函数
     * 
     * @param game x
     */
    public K10Playeds() {
        super(GameEnum.K10.code());
    }
    
    /**
     * 所有的玩法
     */
    private void loadPlayeds() {
        List<GamePlayed> playeds = playedService.findByGame(getGame());
        try {
            for (GamePlayed gp : playeds) {
                Class<?> clazz = Class.forName(gp.getPlayedClass());
                if (PlayedFactory.class.isAssignableFrom(clazz)) {
                    PlayedFactory factory = (PlayedFactory) applicationContext.getBean(clazz);
                    Played played = factory.load(gp);
                    addPlayed(played);
                } else {
                    throw new RuntimeException("玩法加载失败[" + gp.toString() + "]");
                }
            }
            logger.info("游戏{}共加载到{}种玩法", this.getGame(), playeds.size());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("玩法加载失败", e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param tremAttr x
     * @return x
     */
    public Played getTermAttrPlayed(TermAttrEnum tremAttr) {
        return getPlayed(tremAttr.code());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param index x
     * @param ballNum x
     * @return x
     */
    public Played getBallNumPlayed(int index, String ballNum) {
        return getPlayed(ballNumPlayedKey(index, ballNum));
    }
    
    /**
     * 返回几中中
     * 
     * @param count x
     * @param in x
     * @return x
     */
    public Played getBallComboPlayed(int count, int in) {
        return getPlayed(ballComboPlayedKey(count, in));
    }
    
    /**
     * 返回 第几球单双
     *
     * @param index x
     * @param ballAttr x
     * @return x
     */
    public Played getBallAttrPlayed(int index, BallAttrEnum ballAttr) {
        
        return getPlayed(ballAttrPlayedKey(index, ballAttr));
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param index x
     * @param ballNum x
     * @return x
     */
    private String ballNumPlayedKey(int index, String ballNum) {
        int num = Integer.parseInt(ballNum);
        
        return String.format("b%s_%s", index, num);
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param ballIndex x
     * @param ballAttr x
     * @return x
     */
    private String ballAttrPlayedKey(int ballIndex, BallAttrEnum ballAttr) {
        return String.format("b%s_%s", ballIndex, ballAttr.code());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param count x
     * @param in x
     * @return x
     */
    private String ballComboPlayedKey(int count, int in) {
        return String.format("%sz%s", count, in);
    }
    
    /**
     * @throws Exception x
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadPlayeds();
    }
}
