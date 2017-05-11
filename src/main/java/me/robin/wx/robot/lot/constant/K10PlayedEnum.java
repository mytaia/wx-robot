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
 * @version 2017年5月11日 作者
 */
public enum K10PlayedEnum {
    
    /* =============================================== */
    /** FIXME */
    ZhongHeDa(TermAttrEnum.ZhongHeDa),
    /** FIXME */
    ZhongHeXiao(TermAttrEnum.ZhongHeXiao),
    /** FIXME */
    ZhongHeDan(TermAttrEnum.ZhongHeDan),
    /** FIXME */
    ZhongHeSuang(TermAttrEnum.ZhongHeSuang),
    /** FIXME */
    ZhongHeWeiDa(TermAttrEnum.ZhongHeWeiDa),
    /** FIXME */
    ZhongHeWeiXiao(TermAttrEnum.ZhongHeWeiXiao),
    /** FIXME */
    Long(TermAttrEnum.Long),
    /** FIXME */
    Hu(TermAttrEnum.Hu),
    
    /* =============================================== */
    
    /** FIXME */
    B1Da(1, BallAttrEnum.Da),
    /** FIXME */
    B1WeiDa(1, BallAttrEnum.WeiDa),;
    
    /** FIXME */
    private String text;
    
    /**
     * 构造函数
     * 
     * @param text x
     */
    K10PlayedEnum(String text) {
        this.text = text;
    }
    
    /**
     * 构造函数
     * 
     * @param tae x
     */
    K10PlayedEnum(TermAttrEnum tae) {
        this.text = tae.id();
    }
    
    /**
     * 构造函数
     * 
     * @param index x
     * @param bia x
     */
    K10PlayedEnum(int index, BallAttrEnum bia) {
        this.text = bia.id();
    }
    
    /**
     * @return the text
     */
    public String text() {
        return text;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param index 球编号从1开始
     * @param bia x
     * @return x
     */
    public static K10PlayedEnum valueOf(int index, BallAttrEnum bia) {
        String name = String.format("B%s%s", index, bia.name());
        return K10PlayedEnum.valueOf(name);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param tae x
     * @return x
     */
    public static K10PlayedEnum valueOf(TermAttrEnum tae) {
        return K10PlayedEnum.valueOf(tae.name());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param index 球编号从1开始
     * @param num 球号码(2位，1位前面补0)
     * @return x
     */
    public static K10PlayedEnum valueOf(int index, String num) {
        String name = String.format("B%s%s", index, num.length() == 1 ? "0" + num : num);
        return K10PlayedEnum.valueOf(name);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param bce 球编号从1开始
     * @return x
     */
    public static K10PlayedEnum valueOf(BallComboEnum bce) {
        return K10PlayedEnum.valueOf(bce.name());
    }
}
