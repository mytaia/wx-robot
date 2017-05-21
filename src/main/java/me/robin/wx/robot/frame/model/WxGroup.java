
package me.robin.wx.robot.frame.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by xuanlubin on 2017/4/18.
 */
public class WxGroup extends WxUser {
    
    /** FIXME */
    @JSONField(name = "MemberCount")
    private int memberCount;
    
    /** FIXME */
    @JSONField(name = "EncryChatRoomId")
    private String encryChatRoomId;
    
    /** FIXME */
    @JSONField(name = "MemberList")
    private List<WxUser> memberList;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public int getMemberCount() {
        return memberCount;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param memberCount x
     */
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public List<WxUser> getMemberList() {
        return memberList;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param memberList x
     */
    public void setMemberList(List<WxUser> memberList) {
        this.memberList = memberList;
    }
    
    /**
     * @return the encryChatRoomId
     */
    public String getEncryChatRoomId() {
        return encryChatRoomId;
    }
    
    /**
     * @param encryChatRoomId the encryChatRoomId to set
     */
    public void setEncryChatRoomId(String encryChatRoomId) {
        this.encryChatRoomId = encryChatRoomId;
    }
    
}
