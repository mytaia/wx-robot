
package me.robin.wx.robot.lot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /** FIXME */
    @Column
    private Date createTime;
    
    /** FIXME */
    @Column
    private Date updateTime;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @PrePersist
    protected void beforeNew() {
        if (createTime == null) {
            createTime = new Date();
        }
        if (updateTime == null) {
            updateTime = new Date();
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @PreUpdate
    protected void beforeUpdate() {
        updateTime = new Date();
    }
}
