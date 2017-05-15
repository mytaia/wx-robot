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
public enum GameEnum implements EnumAware {
    /** FIXME */
    K10("k10", "快乐十分"),
    /** FIXME */
    K3("k3", "快三");
    
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
    GameEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    /**
     * @return the text
     */
    @Override
    public String code() {
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
