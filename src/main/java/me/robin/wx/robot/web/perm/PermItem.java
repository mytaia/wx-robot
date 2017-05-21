/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/
package me.robin.wx.robot.web.perm;


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
 * @version 2017年5月20日 作者
 */
public class PermItem {
    
    /** 权限标识串，在一个模块中不能重复 如"view" */
    private String PermStr;
    
    /** 权限项名字 */
    private String name;
    

    /** 权限项备注说明 */
    private String remarks;
    
    /**
     * 构造函数
     * 
     * @param PermStr 权限串
     * @param name name
     * @param remarks 备注说明
     */
    public PermItem(String PermStr, String name, String remarks) {
        this.PermStr = PermStr;
        this.name = name;
        this.remarks = remarks;
    }


    /**
     * @return permStr
     */
    public String getPermStr() {
        return PermStr;
    }

    
    /**
     * @param permStr 要设置的 permStr
     */
    public void setPermStr(String permStr) {
        PermStr = permStr;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }
    
    /**
     * @param remarks 要设置的 remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
