/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/

package me.robin.wx.robot.web.perm;

import java.util.List;

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
public class FirstLevelMenu {
    
    /** 在面板中显示 */
    public static final boolean SHOW_IN_PANEL = true;
    
    /** 菜单id */
    private String id;// 可用于菜单项高亮
    
    /** 菜单名称 */
    private String name;
    
    /** 描述 */
    private String description;
    
    /** 访问url，在没有子菜单情况下有效 */
    private String url;
    
    /** 权限字符串 */
    private String permStr;
    
    /** 图标url */
    private String logoUrl;
    
    /** 子菜单集合 */
    private List<ActivatorMenu> menus;
    
    /** menu的所有id集合 */
    private String ids = "";// 将menu的所有id通过,分隔，用于页面中高亮显示
    
    /**
     * 构造函数
     * 
     * @param id 菜单Id
     * @param name 菜单名称
     * @param url 菜单访问的url
     * @param permStr 权限字符串
     * @param logoUrl logo的url
     */
    public FirstLevelMenu(String id, String name, String url, String permStr, String logoUrl) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.permStr = permStr;
        this.logoUrl = logoUrl;
    }
    
    /**
     * 构造函数
     * 
     * @param id id
     * @param name name
     * @param url url
     * @param menus 菜单集合
     * @param permStr permStr
     * @param logoUrl logoUrl
     * @param description description
     */
    public FirstLevelMenu(String id, String name, String url, List<ActivatorMenu> menus, String permStr, String logoUrl) {
        this(id, name, url, menus, permStr, logoUrl, null);
    }
    
    /**
     * 构造函数
     * 
     * @param id id
     * @param name name
     * @param url url
     * @param menus 菜单集合
     */
    public FirstLevelMenu(String id, String name, String url, List<ActivatorMenu> menus) {
        this(id, name, url, menus, null, null, null);
    }
    
    /**
     * 构造函数
     * 
     * @param id id
     * @param name name
     * @param url url
     * @param menus 菜单集合
     * @param permStr permStr
     * @param logoUrl logoUrl
     * @param description description
     */
    public FirstLevelMenu(String id, String name, String url, List<ActivatorMenu> menus, String permStr, String logoUrl, String description) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.menus = menus;
        if (menus != null) {// 用于拼装,org,innerOrg...
            StringBuffer sb = new StringBuffer();
            for (ActivatorMenu menu : menus) {
                sb.append(",");
                sb.append(menu.getId());
            }
            this.ids = sb.toString();
        } else {
            this.ids = "," + id;
        }
        this.permStr = permStr;
        this.logoUrl = logoUrl;
        this.description = description;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return the permStr
     */
    public String getPermStr() {
        return permStr;
    }
    
    /**
     * @param permStr the permStr to set
     */
    public void setPermStr(String permStr) {
        this.permStr = permStr;
    }
    
    /**
     * @return the logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }
    
    /**
     * @param logoUrl the logoUrl to set
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    /**
     * @return the menus
     */
    public List<ActivatorMenu> getMenus() {
        return menus;
    }
    
    /**
     * @param menus the menus to set
     */
    public void setMenus(List<ActivatorMenu> menus) {
        this.menus = menus;
    }
    
    /**
     * @return the ids
     */
    public String getIds() {
        return ids;
    }
    
    /**
     * @param ids the ids to set
     */
    public void setIds(String ids) {
        this.ids = ids;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
