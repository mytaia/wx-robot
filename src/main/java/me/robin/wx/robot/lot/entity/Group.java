/******************************************************************************
 * create by 2017年5月16日
 ******************************************************************************/

package me.robin.wx.robot.lot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 受管的群组
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
 * @version 2017年5月16日 作者
 */

@Entity
@Table(name = "lot_group")
public class Group extends BaseEntity {
    
    /** FIXME */
    private static final long serialVersionUID = 8206763957462783758L;
    
    /** FIXME */
    @Id
    @Column(length = 40)
    private String groupId;
    
    /** 群昵称 */
    @Column(length = 40)
    private String nickName;
    
    /** 管理员 */
    @Column(length = 40)
    private String masterNickName;
    
    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }
    
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }
    
    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    /**
     * @return the masterNickName
     */
    public String getMasterNickName() {
        return masterNickName;
    }
    
    /**
     * @param masterNickName the masterNickName to set
     */
    public void setMasterNickName(String masterNickName) {
        this.masterNickName = masterNickName;
    }
    
}
