/******************************************************************************
 * create by 2017年5月13日
 ******************************************************************************/

package me.robin.wx.robot.lot.played.k3;

import me.robin.wx.robot.lot.constant.EnumAware;

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
 * @version 2017年5月13日 作者
 */
public enum K3PlayedEnum implements EnumAware {
    
    /** FIXME */
    SanTongHaoDanXuan("santonghaodanxuan", "三同号单选");
    
    /** FIXME */
    private String code;
    
    /** FIXME */
    private String description;
    
    /**
     * 构造函数
     * 
     * @param code x
     * @param description x
     */
    K3PlayedEnum(String code, String description) {
        this.code = code;
        this.description = description;
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
     * x
     * 
     * @param code x
     * @return the text
     */
    public static K3PlayedEnum fromCode(String code) {
        for (K3PlayedEnum item : values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * x
     * 
     * @param description x
     * @return the text
     */
    public static K3PlayedEnum fromDescription(String description) {
        for (K3PlayedEnum item : values()) {
            if (item.description.equals(description)) {
                return item;
            }
        }
        return null;
    }
}
