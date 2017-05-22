
package me.robin.wx.robot.lot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import me.robin.wx.robot.lot.entity.UserMapper;

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
public interface UserMapperDao extends JpaRepository<UserMapper, String>, JpaSpecificationExecutor<UserMapper> {
    
    /**
     * 根据群中的昵称和群昵称找到对应的记录
     * 
     * @param nickName x
     * @param groupNickName x
     * @return x
     */
    @Query("from UserMapper where nickName=?1 and groupNickName=?2")
    UserMapper findByNickName(String nickName, String groupNickName);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param userName x
     * @param groupNickName x
     * @return x
     */
    @Query("from UserMapper where userName=?1 and groupNickName=?2")
    UserMapper findByUserName(String userName, String groupNickName);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param exUserId x
     * @return x
     */
    @Query("from UserMapper where exUserId=?1 ")
    UserMapper findByExUserId(String exUserId);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param nickName x
     * @param groupNickName x
     * @return x
     */
    @Query("delete from UserMapper where nickName=?1 and groupNickName=?2")
    @Modifying
    int deleteByNickName(String nickName, String groupNickName);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param groupNickName x
     * @return x
     */
    @Query("from UserMapper where groupNickName=?1 ")
    List<UserMapper> findByGroupNickName(String groupNickName);
    
}
