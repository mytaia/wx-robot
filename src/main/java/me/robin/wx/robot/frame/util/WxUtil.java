
package me.robin.wx.robot.frame.util;

import java.util.function.Consumer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import me.robin.wx.robot.frame.model.WxMsg;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import sun.misc.BASE64Decoder;

/**
 * Created by xuanlubin on 2017/4/18.
 */
public class WxUtil {
    
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
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
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
    
    public static String revertXml(String content) {
        content = StringUtils.replace(content, "&lt;", "<");
        return StringUtils.replace(content, "&gt;", ">");
    }
    
    /**
     * 是否是群组消息
     *
     * @param msg x
     * @return x
     */
    public static boolean isGroupMessage(WxMsg msg) {
        return msg != null && msg.getToUserName() != null && msg.getToUserName().startsWith("@@");
    }
}
