
package me.robin.wx.robot.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import me.robin.wx.robot.lot.entity.Group;

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
 * @version 2017年5月13日 作者
 */
public interface GroupDao extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param groupNickName x
     * @return x
     */
    @Query("from Group where nickName=?1")
    Group findByNickName(String groupNickName);
    
}
