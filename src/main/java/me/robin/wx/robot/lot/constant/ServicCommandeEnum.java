/**create 2017-05-15**/

package me.robin.wx.robot.lot.constant;

/**
 * 服务指令
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
public enum ServicCommandeEnum implements EnumAware {
    /** 查余额 */
    Bjoece("Bjoece", "查余额"),
    /** 查投注 */
    Bet("Bet", "查投注"),
    /** 取消投注 */
    CancelBet("CancelBet", "取消投注");
    
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
    ServicCommandeEnum(String code, String description) {
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
    public static ServicCommandeEnum fromCode(String code) {
        for (ServicCommandeEnum item : values()) {
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
    public static ServicCommandeEnum fromDescription(String description) {
        for (ServicCommandeEnum item : values()) {
            if (item.description.equals(description)) {
                return item;
            }
        }
        return null;
    }
    
}
