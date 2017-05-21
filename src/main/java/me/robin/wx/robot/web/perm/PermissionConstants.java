/******************************************************************************
 * create by 2017年5月20日
 ******************************************************************************/

package me.robin.wx.robot.web.perm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

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
public class PermissionConstants {
    
    /** 管理员权限串 */
    public final static String PERMSTR_ADMIN = "admin";
    
    /** 所有权限 */
    protected final static Map<String, PermItem> PERMISTRS = new LinkedHashMap<String, PermItem>();
    
    static {
        addPermistr(PermissionConstants.PERMSTR_ADMIN, "系统管理员", "这里可以配置服务的系统管理员");
    }
    
    /**
     * 获取所有权限
     * 
     * @return List
     */
    public static List<PermItem> allPermistrs() {
        Collection<PermItem> collection = PERMISTRS.values();
        if (collection == null) {
            return new ArrayList<PermItem>(0);
        }
        return Lists.newArrayList(collection);
    }
    
    /**
     * 增加权限
     * 
     * @param permStr permStr
     * @param name name
     * @param remarks remarks
     * @param permistr 权限字符串
     */
    public static void addPermistr(String permStr, String name, String remarks) {
        PermItem item = PERMISTRS.get(permStr);
        if (item == null) {
            item = new PermItem(permStr, name, remarks);
            PERMISTRS.put(permStr, item);
        } else {
            item.setName(name);
            item.setRemarks(remarks);
        }
    }
}
