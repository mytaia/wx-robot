
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
public class ActivatorMenu {
    
    /** 菜单id */
    private String id;// 可用于菜单项高亮
    
    /** 菜单名称 */
    private String name;
    
    /** 访问url */
    private String url;
    
    /** 权限字符串 */
    private String permStr;
    
    /** 这个字段不需要设置，在菜单展示的时候获取Activator的code字段 */
    private String appCode;
    
    /** 打开新的窗口 */
    private boolean blankOpen;
    
    /**
     * 构造函数
     */
    public ActivatorMenu() {
    }
    
    /**
     * 构造函数
     * 
     * @param id id
     * @param name name
     * @param url url
     */
    public ActivatorMenu(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
    
    /**
     * 构造函数
     * 
     * @param id 菜单id
     * @param name name
     * @param url url
     * @param permStr permStr
     */
    public ActivatorMenu(String id, String name, String url, String permStr) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.permStr = permStr;
    }
    
    /**
     * @return id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id 要设置的 id
     */
    public void setId(String id) {
        this.id = id;
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
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url 要设置的 url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return permStr
     */
    public String getPermStr() {
        return permStr;
    }
    
    /**
     * @param permStr 要设置的 permStr
     */
    public void setPermStr(String permStr) {
        this.permStr = permStr;
    }
    
    /**
     * @return appCode
     */
    public String getAppCode() {
        return appCode;
    }
    
    /**
     * @param appCode 要设置的 appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
    
    /**
     * @return blankOpen
     */
    public boolean isBlankOpen() {
        return blankOpen;
    }
    
    /**
     * @param blankOpen 要设置的 blankOpen
     */
    public void setBlankOpen(boolean blankOpen) {
        this.blankOpen = blankOpen;
    }
    
}
