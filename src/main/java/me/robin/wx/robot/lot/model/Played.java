/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.model;

/**
 * 玩法
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
public abstract class Played {
    
    /** 游戏 */
    private String game;
    
    /** 标识，全局唯一 */
    private String playedId;
    
    /** 游戏内部KEY，在游戏内唯一 */
    private String key;
    
    /** 中文名 */
    private String name;
    
    /**
     * 构造函数
     * 
     * @param key x
     * @param name x
     * @param game x
     */
    public Played(String game, String key, String name) {
        super();
        this.key = key;
        this.name = name;
        this.game = game;
        this.playedId = String.format("%s:%s", game, key);
    }
    
    /**
     * 处理结果
     * 
     * @param bet x
     * @param termResult x
     * @return x
     */
    public abstract BetResult process(BetRequest bet, TermResult termResult);
    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }
    
    /**
     * @return the playedId
     */
    public String getPlayedId() {
        return playedId;
    }
    
    @Override
    public String toString() {
        return "Played [game=" + game + ", playedId=" + playedId + ", key=" + key + ", name=" + name + "]";
    }
    
}
