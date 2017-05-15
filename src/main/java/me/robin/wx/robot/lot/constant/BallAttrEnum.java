/**create 2017-05-15**/

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
public enum BallAttrEnum implements EnumAware {
    
    /** FIXME */
    Da("da", "大"),
    /** FIXME */
    Xiao("xiao", "小"),
    /** FIXME */
    Dan("dan", "单"),
    /** FIXME */
    Shuang("shuang", "双"),
    /** FIXME */
    WeiDa("weida", "尾大"),
    /** FIXME */
    WeiXiao("weixiao", "尾小"),
    /** FIXME */
    HeDan("hedan", "合单"),
    /** FIXME */
    HeShuang("heshuang", "合双");
    
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
    BallAttrEnum(String code, String description) {
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
    public static BallAttrEnum fromCode(String code) {
        for (BallAttrEnum item : values()) {
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
    public static BallAttrEnum fromDescription(String description) {
        for (BallAttrEnum item : values()) {
            if (item.description.equals(description)) {
                return item;
            }
        }
        return null;
    }
}
