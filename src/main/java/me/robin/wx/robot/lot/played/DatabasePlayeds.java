/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.played;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.played.factory.PlayedFactory;
import me.robin.wx.robot.lot.service.GamePlayedService;

/**
 * 从数据库中加载的玩法
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
public abstract class DatabasePlayeds extends Playeds {
    
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
    public DatabasePlayeds(String game) {
        super(game);
    }
    
    /**
     * 所有的玩法
     */
    protected void loadPlayeds() {
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
}
