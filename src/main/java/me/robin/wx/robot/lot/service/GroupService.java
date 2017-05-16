
package me.robin.wx.robot.lot.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

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
    private Cache<String, Group> cache = CacheBuilder.newBuilder().build();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param groupNickName x
     * @return x
     */
    public Group getByNickName(String groupNickName) {
        try {
            return cache.get(groupNickName, new Callable<Group>() {
                
                @Override
                public Group call() throws Exception {
                    return findByNickName(groupNickName);
                }
                
            });
        } catch (ExecutionException e) {
            logger.error("读取群组管理时异常", e);
        }
        return null;
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
