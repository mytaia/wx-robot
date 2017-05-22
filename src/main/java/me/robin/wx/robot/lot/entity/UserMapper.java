/******************************************************************************
 * create by 2017年5月16日
 ******************************************************************************/

package me.robin.wx.robot.lot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 微信用户与网盘用户的对应关系
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
@Table(name = "lot_user_info", indexes = { @Index(columnList = "nickName,groupNickName", unique = true) })

public class UserMapper extends BaseEntity {
    
    /** FIXME */
    private static final long serialVersionUID = 8206763957462783758L;
    
    /** FIXME */
    @Id
    @Column(length = 40)
    private String userInfoId;
    
    /** 用户在群中的昵称 */
    @Column(length = 40)
    private String nickName;
    
    /** 用户在群中的userName */
    @Column(length = 80)
    private String userName;
    
    /** 群昵称 */
    @Column(length = 40)
    private String groupNickName;
    
    /** 网盘用户对应的ID */
    @Column(length = 40, unique = true)
    private String exUserId;
    
    /**
     * @return the userInfoId
     */
    public String getUserInfoId() {
        return userInfoId;
    }
    
    /**
     * @param userInfoId the userInfoId to set
     */
    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
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
     * @return the exUserId
     */
    public String getExUserId() {
        return exUserId;
    }
    
    /**
     * @param exUserId the exUserId to set
     */
    public void setExUserId(String exUserId) {
        this.exUserId = exUserId;
    }
    
    /**
     * @return the groupNickName
     */
    public String getGroupNickName() {
        return groupNickName;
    }
    
    /**
     * @param groupNickName the groupNickName to set
     */
    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
