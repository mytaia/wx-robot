
package me.robin.wx.robot.frame.service;

import com.alibaba.fastjson.JSONArray;

import me.robin.wx.robot.frame.model.WxUser;

/**
 * Created by xuanlubin on 2017/4/19.
 */
public interface ContactService {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param contactJson x
     */
    void updateContact(JSONArray contactJson);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param alias x
     * @return x
     */
    WxUser queryUserByAlias(String alias);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param remark x
     * @return x
     */
    WxUser queryUserByRemark(String remark);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param nickName x
     * @return x
     */
    WxUser queryUserByNickName(String nickName);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param userName x
     * @return x
     */
    WxUser queryUserByUserName(String userName);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param queryString x
     * @return x
     */
    WxUser queryUser(String queryString);
    
}
