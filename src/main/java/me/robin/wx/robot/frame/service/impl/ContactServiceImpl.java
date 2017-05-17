
package me.robin.wx.robot.frame.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.service.ContactService;
import me.robin.wx.robot.frame.util.WxUtil;

/**
 * Created by xuanlubin on 2017/4/19.
 */
@Component
public class ContactServiceImpl implements ContactService {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    
    /** FIXME */
    private Map<String, String> keysUser = Maps.newConcurrentMap();
    
    /** FIXME */
    private Map<String, WxUser> userNameUserMap = Maps.newConcurrentMap();
    
    /** FIXME */
    /** FIXME */
    private Map<String, WxGroup> groupNameMap = Maps.newConcurrentMap();
    
    @Override
    public void updateContact(JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject item = array.getJSONObject(i);
            String userName = item.getString("UserName");
            if (StringUtils.startsWith(userName, "@@")) {
                WxGroup wxGroup = item.toJavaObject(WxGroup.class);
                addWxGroup(wxGroup);
                addWxUser(wxGroup);
            } else if (StringUtils.startsWith(userName, "@")) {
                WxUser wxUser = item.toJavaObject(WxUser.class);
                addWxUser(wxUser);
            } else if (!WxConst.FILTER_USERS.contains(userName)) {
                logger.info("不支持的用户类型: {} {} {} {}", userName, item.getString("Alias"), item.getString("NickName"), item.getString("Signature"));
            }
        }
    }
    
    @Override
    public void updateGroup(List<WxGroup> groups) {
        for (WxGroup group : groups) {
            String userName = group.getUserName();
            if (WxUtil.isGroup(userName)) {
                addWxGroup(group);
                addWxUser(group);
            } else if (StringUtils.startsWith(userName, "@")) {
                addWxUser(group);
            } else if (!WxConst.FILTER_USERS.contains(userName)) {
                logger.info("不支持的用户类型: {} {} {} {}", userName, group.getAlias(), group.getNickName(), group.getSignature());
            }
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param wxGroup xx
     */
    private void addWxGroup(WxGroup wxGroup) {
        groupNameMap.put(wxGroup.getUserName(), wxGroup);
        addWxUser(wxGroup.getMemberList());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param users x
     */
    private void addWxUser(List<WxUser> users) {
        if (CollectionUtils.isEmpty(users)) {
            return;
        }
        for (WxUser user : users) {
            addWxUser(user);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param userName x
     * @return x
     */
    @Override
    public WxGroup getWxGroup(String userName) {
        return groupNameMap.get(userName);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param wxUser x
     */
    private void addWxUser(WxUser wxUser) {
        saveUser(NameKey.Alias, wxUser);
        saveUser(NameKey.NickName, wxUser);
        saveUser(NameKey.RemarkName, wxUser);
        updateWxUser(wxUser);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param nameKey x
     * @param user x
     */
    private void saveUser(NameKey nameKey, WxUser user) {
        String key = getKey(nameKey, user);
        if (key == null) {
            return;
        }
        keysUser.forEach((k, v) -> {
            if (StringUtils.equals(v, user.getUserName()) && k.startsWith(nameKey.name()) && !StringUtils.equals(k, key)) {
                keysUser.remove(k);
                keysUser.put(key, user.getUserName());
            }
            
        });
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param nameKey x
     * @param user x
     * @return x
     */
    private String getKey(NameKey nameKey, WxUser user) {
        String name = null;
        switch (nameKey) {
            case Alias:
                name = user.getAlias();
                break;
            case UserName:
                name = user.getUserName();
                break;
            case NickName:
                name = user.getNickName();
                break;
            case RemarkName:
                name = user.getRemarkName();
                break;
            
            default:
                break;
        }
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        
        return nameKey.name() + ":\u2005\u2009\u2090:" + name;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param map x
     * @param key x
     * @param wxUser x
     */
    private void updateWxUser(WxUser wxUser) {
        WxUser old = userNameUserMap.putIfAbsent(wxUser.getUserName(), wxUser);
        if (old == null) {
            return;
        }
        
        if (!StringUtils.equals(old.getRemarkName(), wxUser.getRemarkName()) && !StringUtils.isEmpty(wxUser.getRemarkName())) {
            old.setRemarkName(wxUser.getRemarkName());
        } else if (!StringUtils.equals(old.getNickName(), wxUser.getNickName()) && !StringUtils.isEmpty(wxUser.getNickName())) {
            old.setNickName(wxUser.getNickName());
        } else if (!StringUtils.equals(old.getDisplayName(), wxUser.getDisplayName()) && !StringUtils.isEmpty(wxUser.getDisplayName())) {
            old.setDisplayName(wxUser.getDisplayName());
        } else if (!StringUtils.equals(old.getAlias(), wxUser.getAlias()) && !StringUtils.isEmpty(wxUser.getAlias())) {
            old.setAlias(wxUser.getAlias());
        }
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param nameKey x
     * @param key x
     * @return x
     */
    private WxUser queryUserByKey(NameKey nameKey, String key) {
        String nk = nameKey.name() + ":\u2005\u2009\u2090:" + key;
        if (StringUtils.isNotEmpty(nk)) {
            return userNameUserMap.get(key);
        }
        return null;
    }
    
    @Override
    public WxUser queryUserByAlias(String alias) {
        return queryUserByKey(NameKey.Alias, alias);
        
    }
    
    @Override
    public WxUser queryUserByRemark(String remark) {
        return queryUserByKey(NameKey.RemarkName, remark);
    }
    
    @Override
    public WxUser queryUserByNickName(String nickName) {
        return queryUserByKey(NameKey.NickName, nickName);
    }
    
    @Override
    public WxUser queryUserByUserName(String userName) {
        return userNameUserMap.get(userName);
    }
    
    @Override
    public WxUser queryUser(String queryString) {
        // 根据备注名查找用户
        WxUser wxUser = queryUserByRemark(queryString);
        if (null == wxUser) {
            // 根据昵称找用户
            wxUser = queryUserByNickName(queryString);
        }
        if (null == wxUser) {
            // 根据登录名查找用户
            wxUser = queryUserByAlias(queryString);
        }
        if (null == wxUser) {
            // 根据加密名称查找用户
            wxUser = queryUserByUserName(queryString);
        }
        return wxUser;
    }
    
    @Override
    public List<WxGroup> getAllWxGroup() {
        return Lists.newArrayList(groupNameMap.values());
    }
    
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
     * @version 2017年5月17日 作者
     */
    enum NameKey {
        /** FIXME */
        Alias,
        /** FIXME */
        NickName,
        /** FIXME */
        UserName,
        /** FIXME */
        RemarkName
    }
}
