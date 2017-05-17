
package me.robin.wx.robot.frame.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuanlubin on 2017/4/20.
 */
public class WxGroupMsg extends WxMsg {
    
    /**  */
    private final static Pattern MESSAGE_PATTERN = Pattern.compile("^(@[0-9a-z]*?):<br/>");
    
    /** 群 */
    private String group;
    
    /** 发送人的userNamer */
    private String sender;
    
    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }
    
    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }
    
    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }
    
    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    @Override
    public String toString() {
        return "WxGroupMsg [group=" + group + ", sender=" + sender + ", getMsgID()=" + getMsgID() + ", getFromUserName()=" + getFromUserName()
            + ", getContent()=" + getContent() + ", getToUserName()=" + getToUserName() + ", getCreateTime()=" + getCreateTime() + ", getFileName()="
            + getFileName() + ", getFileSize()=" + getFileSize() + ", getMediaId()=" + getMediaId() + ", getMsgType()=" + getMsgType() + ", getUrl()="
            + getUrl() + ", getSubMsgType()=" + getSubMsgType() + ", getAppMsgType()=" + getAppMsgType() + ", getRecommendInfo()="
            + getRecommendInfo() + "]";
    }
    
    /**
     * 构造函数
     * 
     * @param msg x
     * @return x
     */
    public static WxGroupMsg from(WxMsg msg) {
        
        WxGroupMsg gmsg = new WxGroupMsg();
        gmsg.setMsgID(msg.getMsgID());
        gmsg.setFromUserName(msg.getFromUserName());
        String content = msg.getContent();
        Matcher matcher = MESSAGE_PATTERN.matcher(content);
        if (matcher.find()) {
            String sender = matcher.group(1);
            gmsg.sender = sender;
            content = content.substring(matcher.group().length());
            gmsg.setSender(sender);
            gmsg.group = msg.getFromUserName();
        } else {
            gmsg.group = msg.getToUserName();
            gmsg.sender = msg.getFromUserName();
        }
        gmsg.setContent(content);
        gmsg.setToUserName(msg.getToUserName());
        gmsg.setCreateTime(msg.getCreateTime());
        gmsg.setFileName(msg.getFileName());
        gmsg.setFileSize(msg.getFileSize());
        gmsg.setMediaId(msg.getMediaId());
        gmsg.setMsgType(msg.getMsgType());
        gmsg.setUrl(msg.getUrl());
        gmsg.setSubMsgType(msg.getSubMsgType());
        gmsg.setAppMsgType(msg.getAppMsgType());
        gmsg.setRecommendInfo(msg.getRecommendInfo());
        
        return gmsg;
    }
    
}
