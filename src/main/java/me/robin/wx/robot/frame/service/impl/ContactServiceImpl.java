
package me.robin.wx.robot.frame.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    
    /** FIXME */
    private Map<String, String> keysMap = Maps.newConcurrentMap();
    
    /** FIXME */
    private Map<String, WxUser> userNameMap = new ConcurrentHashMap<>();
    
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
        keysMap.put(getKey("alias", wxUser.getAlias()), wxUser.getUserName());
        keysMap.put(getKey("remark", wxUser.getRemarkName()), wxUser.getUserName());
        keysMap.put(getKey("nickName", wxUser.getNickName()), wxUser.getUserName());
        updateWxUser(userNameMap, wxUser.getUserName(), wxUser);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param pre x
     * @param key x
     * @return x
     */
    private String getKey(String pre, String key) {
        return pre + ":\u2005\u2009\u2090:" + key;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param map x
     * @param key x
     * @param wxUser x
     */
    private void updateWxUser(Map<String, WxUser> map, String key, WxUser wxUser) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        
        WxUser old = map.get(key);
        if (old == null) {
            map.put(key, wxUser);
            return;
        }
        if (StringUtils.equals(old.getUserName(), wxUser.getUserName())) {
            if (!StringUtils.isEmpty(wxUser.getRemarkName())) {
                old.setRemarkName(wxUser.getRemarkName());
            } else if (!StringUtils.isEmpty(wxUser.getNickName())) {
                old.setNickName(wxUser.getNickName());
            } else if (!StringUtils.isEmpty(wxUser.getDisplayName())) {
                old.setDisplayName(wxUser.getDisplayName());
            } else if (!StringUtils.isEmpty(wxUser.getAlias())) {
                old.setAlias(wxUser.getAlias());
            }
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param key x
     * @return x
     */
    private WxUser queryUserByKey(String key) {
        if (StringUtils.isNotEmpty(key)) {
            return userNameMap.get(key);
        }
        return null;
    }
    
    @Override
    public WxUser queryUserByAlias(String alias) {
        return queryUserByKey(keysMap.get(getKey("alias", alias)));
        
    }
    
    @Override
    public WxUser queryUserByRemark(String remark) {
        return queryUserByKey(keysMap.get(getKey("remark", remark)));
    }
    
    @Override
    public WxUser queryUserByNickName(String nickName) {
        return queryUserByKey(keysMap.get(getKey("nickName", nickName)));
    }
    
    @Override
    public WxUser queryUserByUserName(String userName) {
        return userNameMap.get(userName);
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
}
