
package me.robin.wx.robot.lot.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.lot.entity.UserMapper;
import me.robin.wx.robot.lot.repository.UserMapperDao;

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
@Transactional
public class UserMapperService {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(UserMapperService.class);
    
    /**
     * 
     */
    @Autowired
    private UserMapperDao userMapperDao;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public List<UserMapper> findAll() {
        return userMapperDao.findAll();
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userMapper userMapperDao
     */
    public void save(UserMapper userMapper) {
        if (StringUtils.isBlank(userMapper.getExUserId())) {
            delete(userMapper.getNickName(), userMapper.getGroupNickName());
        } else {
            UserMapper old = findByNickName(userMapper.getNickName(), userMapper.getGroupNickName());
            old.setExUserId(userMapper.getExUserId());
            userMapperDao.save(old);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param nickName x
     * @param groupNickName x
     * @return x
     */
    public UserMapper findByNickName(String nickName, String groupNickName) {
        return userMapperDao.findByNickName(nickName, groupNickName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @param groupNickName x
     * @return x
     */
    public UserMapper findByUserName(String userName, String groupNickName) {
        return userMapperDao.findByUserName(userName, groupNickName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param nickName x
     * @param groupNickName x
     * @return x
     */
    public List<UserMapper> findByGroupNickName(String groupNickName) {
        return userMapperDao.findByGroupNickName(groupNickName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param exUserId x
     * @return x
     */
    public UserMapper findByExUserId(String exUserId) {
        return userMapperDao.findByExUserId(exUserId);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param nickName x
     * @param groupNickName x
     * @return x
     */
    public int delete(String nickName, String groupNickName) {
        return userMapperDao.deleteByNickName(nickName, groupNickName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param contactList x
     */
    public void initUserMapper(List<WxGroup> contactList) {
        // 清除当的userName
        userMapperDao.clearUserName();
        for (WxGroup group : contactList) {
            List<WxUser> users = group.getMemberList();
            if (CollectionUtils.isEmpty(users)) {
                continue;
            }
            
            List<UserMapper> ums = findByGroupNickName(group.getNickName());
            if (CollectionUtils.isEmpty(ums)) {
                continue;
            }
            
            Multimap<String, WxUser> userMap = HashMultimap.create();
            users.forEach(user -> {
                userMap.put(user.getNickName(), user);
            });
            
            ums.forEach(um -> {
                Collection<WxUser> us = userMap.get(um.getNickName());
                if (us == null || us.size() == 0) {
                    logger.warn("加载群{}时，没有找到昵称{}对应的用户", group.getNickName(), um.getNickName());
                    return;
                } else if (us.size() != 1) {
                    logger.warn("加载群{}时，有{}个重复的昵称{}", group.getNickName(), us.size(), um.getNickName());
                    return;
                }
                
                WxUser u = us.stream().findFirst().get();
                um.setUserName(u.getUserName());
                userMapperDao.save(um);
                logger.info("群{}中的用户{}成功匹配到外部用户ID{}", group.getNickName(), u.getNickName(), um.getExUserId());
            });
            
        }
    }
    
}
