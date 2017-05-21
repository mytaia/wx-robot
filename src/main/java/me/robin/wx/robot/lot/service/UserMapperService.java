
package me.robin.wx.robot.lot.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
}
