/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.framwork;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

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
 * @version 2017年5月23日 作者
 */
public class AssignedUIDGenerator extends UIDGenerator {
    
    /** FIXME */
    private String entityName;
    
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        entityName = params.getProperty(ENTITY_NAME);
        if (entityName == null) {
            throw new MappingException("no entity name");
        }
        
        super.configure(type, params, serviceRegistry);
    }
    
    /**
     * @param session session
     * @param object object
     * @return Serializable
     * @see com.comtop.cfp.framework.persistence.UIDGenerator#generate(org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
     */
    @Override
    public Serializable generate(SessionImplementor session, Object object) {
        Serializable id = session.getEntityPersister(entityName, object).getIdentifier(object, session);
        if (StringUtils.isEmpty((String) id)) {
            id = super.generate(session, object);
        }
        return id;
    }
    
}
