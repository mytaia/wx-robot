
package me.robin.wx.robot.frame.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by xuanlubin on 2017/4/18.
 */
public class WxUser {
    
    /** FIXME */
    @JSONField(name = "UserName")
    private String userName;
    
    /** FIXME */
    @JSONField(name = "NickName")
    private String nickName;
    
    /** FIXME */
    @JSONField(name = "HeadImgUrl")
    private String headImgUrl;
    
    /** FIXME */
    @JSONField(name = "ContactFlag")
    private int contactFlag;
    
    /** FIXME */
    @JSONField(name = "RemarkName")
    private String remarkName;
    
    /** FIXME */
    @JSONField(name = "Sex")
    private int sex;
    
    /** FIXME */
    @JSONField(name = "Signature")
    private String signature;
    
    /** FIXME */
    @JSONField(name = "VerifyFlag")
    private int verifyFlag;
    
    /** FIXME */
    @JSONField(name = "StarFriend")
    private int starFriend;
    
    /** FIXME */
    @JSONField(name = "AttrStatus")
    private long attrStatus;
    
    /** FIXME */
    @JSONField(name = "Province")
    private String province;
    
    /** FIXME */
    @JSONField(name = "City")
    private String city;
    
    /** FIXME */
    @JSONField(name = "Alias")
    private String alias;
    
    /** FIXME */
    @JSONField(name = "SnsFlag")
    private int snsFlag;
    
    /** FIXME */
    @JSONField(name = "DisplayName")
    private String displayName;
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }
    
    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    /**
     * @return the headImgUrl
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }
    
    /**
     * @param headImgUrl the headImgUrl to set
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    
    /**
     * @return the contactFlag
     */
    public int getContactFlag() {
        return contactFlag;
    }
    
    /**
     * @param contactFlag the contactFlag to set
     */
    public void setContactFlag(int contactFlag) {
        this.contactFlag = contactFlag;
    }
    
    /**
     * @return the remarkName
     */
    public String getRemarkName() {
        return remarkName;
    }
    
    /**
     * @param remarkName the remarkName to set
     */
    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }
    
    /**
     * @return the sex
     */
    public int getSex() {
        return sex;
    }
    
    /**
     * @param sex the sex to set
     */
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }
    
    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    /**
     * @return the verifyFlag
     */
    public int getVerifyFlag() {
        return verifyFlag;
    }
    
    /**
     * @param verifyFlag the verifyFlag to set
     */
    public void setVerifyFlag(int verifyFlag) {
        this.verifyFlag = verifyFlag;
    }
    
    /**
     * @return the starFriend
     */
    public int getStarFriend() {
        return starFriend;
    }
    
    /**
     * @param starFriend the starFriend to set
     */
    public void setStarFriend(int starFriend) {
        this.starFriend = starFriend;
    }
    
    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }
    
    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }
    
    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * @return the snsFlag
     */
    public int getSnsFlag() {
        return snsFlag;
    }
    
    /**
     * @param snsFlag the snsFlag to set
     */
    public void setSnsFlag(int snsFlag) {
        this.snsFlag = snsFlag;
    }
    
    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * @return the attrStatus
     */
    public long getAttrStatus() {
        return attrStatus;
    }
    
    /**
     * @param attrStatus the attrStatus to set
     */
    public void setAttrStatus(long attrStatus) {
        this.attrStatus = attrStatus;
    }
    
}
