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
public enum TermAttrEnum implements EnumAware {
    
    /** FIXME */
    ZhongHeDa("zh_da", "总和大"),
    /** FIXME */
    ZhongHeXiao("zh_xiao", "总和小"),
    /** FIXME */
    ZhongHeDan("zh_dan", "总和单"),
    /** FIXME */
    ZhongHeSuang("zh_shuang", "总和双"),
    /** FIXME */
    ZhongHeWeiDa("zh_weida", "总和尾大"),
    /** FIXME */
    ZhongHeWeiXiao("zh_weixiao", "总和尾小"),
    /** FIXME */
    Long("lh_long", "龙"),
    /** FIXME */
    Hu("lh_hu", "虎");
    
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
    TermAttrEnum(String code, String description) {
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
    public static TermAttrEnum fromCode(String code) {
        for (TermAttrEnum item : values()) {
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
    public static TermAttrEnum fromDescription(String description) {
        for (TermAttrEnum item : values()) {
            if (item.description.equals(description)) {
                return item;
            }
        }
        return null;
    }
    
}
