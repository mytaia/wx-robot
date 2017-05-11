
package me.robin.wx.robot.frame;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xuanlubin on 2017/4/18.
 */
public interface WxConst {
    
    /** web微信appId */
    String APP_ID = "wx782c26e4c19acffb";
    
    String QR_CODE_API = "https://login.weixin.qq.com/jslogin";
    
    String LOGIN_CHECK_API = "https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login";
    
    String INIT_URL = "/cgi-bin/mmwebwx-bin/webwxinit";
    
    // https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxstatusnotify?lang=zh_CN&pass_ticket=Me48r4e05o5ztyB3CG3wGR9ZQgfDTISx1VnOceJnuvWmUz5IdisOjtPITI7hji04
    String STATUS_NOTIFY = "/cgi-bin/mmwebwx-bin/webwxstatusnotify";
    
    /** 机器人发出的消息前的前缀 */
    public static final String ADMIN_MESSAGE_PREX = "@_@";
    
    /** 特殊用户 须过滤 */
    public static final List<String> FILTER_USERS = Arrays.asList("newsapp", "fmessage", "filehelper", "weibo", "qqmail", "fmessage", "tmessage",
        "qmessage", "qqsync", "floatbottle", "lbsapp", "shakeapp", "medianote", "qqfriend", "readerapp", "blogapp", "facebookapp", "masssendapp",
        "meishiapp", "feedsapp", "voip", "blogappweixin", "weixin", "brandsessionholder", "weixinreminder", "wxid_novlwrv3lqwv11", "gh_22b87fa7cb3c",
        "officialaccounts", "notification_messages", "wxid_novlwrv3lqwv11", "gh_22b87fa7cb3c", "wxitil", "userexperience_alarm",
        "notification_messages");
    
    /** 同步服务器 */
    public static final List<String> SYNC_HOST = Arrays.asList( //
        "webpush.weixin.qq.com", //
        "webpush2.weixin.qq.com", //
        "webpush.wechat.com", //
        "webpush1.wechat.com", //
        "webpush2.wechat.com", //
        "webpush1.wechatapp.com" //
    );
    
    /**
     * Created by xuanlubin on 2017/4/20.
     */
    enum MessageTarget {
        GROUP, SINGLE
    }
    
    interface MessageType {
        
        int TEXT = 1;
        
        int IMG = 3;
        
        int VOICE = 34;
        
        int CONTACT = 42;
        
        int VIDEO = 43;
        
        int APP_MSG = 49;
        
        int SYS_MSG = 10000;
        
        int REVOKE_MSG = 10002;
    }
    
    interface SpecialMsg {
        
        String BRIBERY_MONEY = "收到红包，请在手机上查看";
    }
}
