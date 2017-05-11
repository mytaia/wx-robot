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
public enum TermAttrEnum implements EnumAware {
    /** FIXME */
    ZhongHeDa("总和大"),
    /** FIXME */
    ZhongHeXiao("总和小"),
    /** FIXME */
    ZhongHeDan("总和单"),
    /** FIXME */
    ZhongHeSuang("总和双"),
    /** FIXME */
    ZhongHeWeiDa("总和尾大"),
    /** FIXME */
    ZhongHeWeiXiao("总和尾小"),
    /** FIXME */
    Long("龙"),
    /** FIXME */
    Hu("虎");
    
    /** FIXME */
    private String description;
    
    /**
     * 构造函数
     * 
     * @param description x
     */
    TermAttrEnum(String description) {
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
