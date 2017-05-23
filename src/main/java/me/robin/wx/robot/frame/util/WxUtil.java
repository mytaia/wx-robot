
package me.robin.wx.robot.frame.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;

import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.model.WxMsg;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by xuanlubin on 2017/4/18.
 */
public class WxUtil {
    
    /** FIXME */
    private static final String HeadImgPath = "robot/headImg/";
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    public static String randomDeviceId() {
        return "e" + random(15);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param length x
     * @return x
     */
    public static String random(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
    
    public static byte[] fromBase64Img(String imgStr) {
        
        if (imgStr == null) // 图像数据为空
            return null;
        Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getValueFromXml(String xml, String element) {
        return StringUtils.substringBetween(xml, "<" + element + ">", "</" + element + ">");
    }
    
    public static void jsonRequest(Object data, Consumer<RequestBody> consumer) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JSON.toJSONString(data));
        consumer.accept(requestBody);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param data x
     * @return x
     */
    public static RequestBody createJsonRequest(Object data) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JSON.toJSONString(data));
    }
    
    public static String revertXml(String content) {
        // String str = HtmlEscapers.htmlEscaper().escape(content) StringUtils.trim(content);
        
        return HtmlUtils.htmlUnescape(content);
        // str = StringUtils.replace(str, "&lt;", "<");
        // return StringUtils.replace(str, "&gt;", ">");
    }
    
    /**
     * 是否是群组消息
     *
     * @param msg x
     * @return x
     */
    public static boolean isGroupMessage(WxMsg msg) {
        return msg != null && (StringUtils.startsWith(msg.getFromUserName(), "@@") || StringUtils.startsWith(msg.getToUserName(), "@@"));
    }
    
    /**
     * 是否是群组
     *
     * @param userName x
     * @return x
     */
    public static boolean isGroup(String userName) {
        return StringUtils.startsWith(userName, "@@");
    }
    
    /**
     * 是否是群组消息
     *
     * @param msg x
     * @return x
     */
    public static boolean isAdminMessage(String msg) {
        return msg != null && msg.startsWith(WxConst.ADMIN_MESSAGE_PREX);
    }
    
    /**
     * 是否是群组消息
     *
     * @param msg x
     * @return x
     */
    public static String builddminMessage(String msg) {
        return WxConst.ADMIN_MESSAGE_PREX + msg;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param rootPath x
     * @param userName x
     * @return x
     */
    public static String getUserHeadImgDir() {
        String root = FileUtils.getTempDirectoryPath();
        if (!root.endsWith("/")) {
            root = root + "/";
        }
        
        return root + HeadImgPath;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param rootPath x
     * @param userName x
     * @return x
     */
    public static String getUserHeadImgPath(String userName) {
        String root = getUserHeadImgDir();
        return root + userName + ".png";
    }
}
