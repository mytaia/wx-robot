/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/

package me.robin.wx.robot.web.velocity;

import java.util.List;

import com.google.common.collect.Lists;

import me.robin.wx.robot.web.perm.FirstLevelMenu;
import me.robin.wx.robot.web.perm.PermissionConstants;

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
public class DefalutMenuTool {
    
    /** 管理页面菜单 */
    protected static List<FirstLevelMenu> ADMIN_MENUS;
    
    /*** 初始化管理页面菜单 ***/
    static {
        ADMIN_MENUS = Lists.newArrayList();
        FirstLevelMenu fstMenu;
        
        fstMenu = new FirstLevelMenu("datasync", "用户同步", "/admin/datasyn", PermissionConstants.PERMSTR_ADMIN,
            "/include/images/news_file_icon_normal.png");
        ADMIN_MENUS.add(fstMenu);
        
        fstMenu = new FirstLevelMenu("perm", "系统管理", "/admin/perm", PermissionConstants.PERMSTR_ADMIN, "/include/images/news_file_icon_normal.png");
        ADMIN_MENUS.add(fstMenu);
    }
    
    /**
     * 获取管理页面菜单
     * 
     * @return val
     */
    public static List<FirstLevelMenu> getAdminMenu() {
        List<FirstLevelMenu> newList = Lists.newArrayList();
        String account = ""; // PsUserUtils.getAccount();
        // 如果为系统管理员，直接配置权限
        if (account != null && account.equals("admin")) {
            for (int i = 0; i < ADMIN_MENUS.size(); i++) {
                FirstLevelMenu fm = ADMIN_MENUS.get(i);
                // 防止静态变量被修改
                FirstLevelMenu newFirstLevelMenu = new FirstLevelMenu(fm.getId(), fm.getName(), fm.getUrl(), fm.getMenus(), null, fm.getLogoUrl(),
                    fm.getDescription());
                newList.add(newFirstLevelMenu);
            }
        } else {// 如果不是系统管理员，判定是否具备权限项
            List<FirstLevelMenu> munes = getMenu(ADMIN_MENUS);
            newList.addAll(munes);
        }
        return newList;
    }
    
    /**
     * 获得权限菜单
     * 
     * @param menus menus
     * @return menu
     */
    public static List<FirstLevelMenu> getMenu(List<FirstLevelMenu> menus) {
        List<FirstLevelMenu> newList = Lists.newArrayList();
        ShiroTool shiroTool = new ShiroTool();
        for (int i = 0; i < menus.size(); i++) {
            FirstLevelMenu fm = menus.get(i);
            // 防止静态变量被修改
            FirstLevelMenu newFirstLevelMenu = new FirstLevelMenu(fm.getId(), fm.getName(), fm.getUrl(), fm.getMenus(), fm.getPermStr(),
                fm.getLogoUrl(), fm.getDescription());
            if (shiroTool.hasPermission(newFirstLevelMenu.getPermStr())) {
                newList.add(newFirstLevelMenu);
            }
        }
        return newList;
    }
    
}
