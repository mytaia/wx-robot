
package me.robin.wx.robot.web.velocity;

import java.util.Arrays;

import org.apache.velocity.tools.config.DefaultKey;

import me.robin.wx.robot.web.perm.ActivatorMenu;
import me.robin.wx.robot.web.perm.FirstLevelMenu;
import me.robin.wx.robot.web.perm.PermissionConstants;

/**
 * 菜单工具栏
 */
@DefaultKey("menu")
public class MenuTool extends DefalutMenuTool {
    
    /**
     * 构造函数
     */
    public MenuTool() {
        init();
    }
    
    /*** 初始化管理页面菜单 ***/
    protected void init() {
        PermissionConstants.addPermistr(PermissionConstants.PERMSTR_ADMIN, "系统管理员", "可以在这里添加系统管理员");
        
        ADMIN_MENUS = Arrays.asList( //
            new FirstLevelMenu("home", "首页", "/home", "", ""), //
            new FirstLevelMenu("system", "群管理", "", //
                Arrays.asList( //
                    new ActivatorMenu("group", "成员管理", "/group", PermissionConstants.PERMSTR_ADMIN), //
                    new ActivatorMenu("setting", "群设置", "/admin/dict", PermissionConstants.PERMSTR_ADMIN)) //
            ), //
            new FirstLevelMenu("system", "系统管理", "", //
                Arrays.asList( //
                    new ActivatorMenu("group", "群管理", "/admin/datasyn", PermissionConstants.PERMSTR_ADMIN), //
                    new ActivatorMenu("setting", "系统设置", "/admin/dict", PermissionConstants.PERMSTR_ADMIN)) //
            ));
            
    }
    
}
