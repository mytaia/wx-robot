/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.constant;

/**
 * 组合
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
public enum BallComboEnum implements EnumAware {
    /** FIXME */
    In11("一中一"),
    /** FIXME */
    In22("二中二"),
    /** FIXME */
    In33("三中三"),
    /** FIXME */
    In43("四中三"),
    /** FIXME */
    In53("五中三");
    
    /** FIXME */
    private String description;
    
    /**
     * 构造函数
     * 
     * @param description x
     */
    BallComboEnum(String description) {
        this.description = description;
    }
    
    /**
     * @return the text
     */
    @Override
    public String id() {
        return name();
    }
    
    /**
     * @return the text
     */
    @Override
    public String description() {
        return description;
    }
    
}
