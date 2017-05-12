/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.played;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

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
public class Playeds {
    
    /** FIXME */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** FIXME */
    private String game;
    
    /** FIXME */
    private Map<String, Played> playeds = Maps.newConcurrentMap();
    
    /**
     * 构造函数
     * 
     * @param game x
     */
    public Playeds(String game) {
        this.game = game;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param played x
     */
    public void addPlayed(Played played) {
        playeds.put(played.getCode(), played);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param played x
     * @return x
     */
    public Collection<Played> getAll() {
        return Collections.unmodifiableCollection(playeds.values());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param key x
     * @return x
     */
    public Played getPlayed(String key) {
        return playeds.get(key);
    }
    
    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }
    
}
