/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.constant;

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
 * @version 2017年5月12日 作者
 */
public enum GameResultEnum implements EnumAware {
    /** FIXME */
    Win("1", "胜"),
    /** FIXME */
    Lose("-1", "负"),
    /** FIXME */
    Tie("0", "平");
    
    /** FIXME */
    private String id;
    
    /** FIXME */
    private String description;
    
    /**
     * 构造函数
     * 
     * @param id x
     * @param description x
     */
    GameResultEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    /**
     * @return the text
     */
    @Override
    public String id() {
        return id;
    }
    
    /**
     * @return the description
     */
    @Override
    public String description() {
        return description;
    }
    
}
