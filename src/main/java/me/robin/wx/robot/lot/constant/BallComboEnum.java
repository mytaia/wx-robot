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
    In1_1("1z1", "一中一", 1, 1),
    /** FIXME */
    In2_2("2z2", "二中二", 2, 2),
    /** FIXME */
    In3_3("3z3", "三中三", 3, 3),
    /** FIXME */
    In4_3("4z3", "四中三", 4, 3),
    /** FIXME */
    In5_3("5z3", "五中三", 5, 3);
    
    /** FIXME */
    private String code;
    
    /** FIXME */
    private String description;
    
    /** FIXME */
    private int count;
    
    /** FIXME */
    private int in;
    
    /**
     * 构造函数
     * 
     * @param code x
     * @param description x
     * @param count x
     * @param in x
     */
    BallComboEnum(String code, String description, int count, int in) {
        this.code = code;
        this.description = description;
        this.count = count;
        this.in = in;
    }
    
    /**
     * @return the text
     */
    @Override
    public String code() {
        return code;
    }
    
    /**
     * @return the text
     */
    @Override
    public String description() {
        return description;
    }
    
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
    
    /**
     * @return the in
     */
    public int getIn() {
        return in;
    }
    
    /**
     * x
     * 
     * @param code x
     * @return the text
     */
    public static BallComboEnum fromCode(String code) {
        for (BallComboEnum item : values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }
    
}
