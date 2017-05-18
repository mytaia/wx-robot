
package me.robin.wx.robot.lot.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

import me.robin.wx.robot.lot.entity.Group;
import me.robin.wx.robot.lot.repository.GroupDao;

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
@Service
public class GroupService {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    /**
     * 
     */
    @Autowired
    private GroupDao groupDao;
    
    /** FIXME */
    private Map<String, Group> cache = Maps.newConcurrentMap();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param groupNickName x
     * @return x
     */
    public Group getByNickName(String groupNickName) {
        if (CollectionUtils.isEmpty(cache)) {
            List<Group> groups = findAll();
            for (Group group : groups) {
                cache.put(group.getNickName(), group);
            }
        }
        
        return cache.get(groupNickName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public List<Group> findAll() {
        return groupDao.findAll();
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param groupNickName x
     * @return x
     */
    public Group findByNickName(String groupNickName) {
        return groupDao.findByNickName(groupNickName);
    }
    
}
