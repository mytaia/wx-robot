
package me.robin.wx.robot.frame.api;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.util.TypeUtils;

import me.robin.wx.robot.frame.WxConst;
import me.robin.wx.robot.frame.listener.MessageSendListener;
import me.robin.wx.robot.frame.model.LoginUser;
import me.robin.wx.robot.frame.model.WxGroup;
import me.robin.wx.robot.frame.model.WxUser;
import me.robin.wx.robot.frame.util.WxUtil;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xuanlubin on 2017/4/18.
 */
@Component
public class Server extends BaseServer {
    
    @Autowired
    private MessageSendListener messageSendListener;
    
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    
    public Server() {
        super(WxConst.APP_ID);
    }
    
    public void sendTextMessage(String user, String message) {
        
        if (!isLogin()) {
            logger.info("还未完成登录,不能发送消息");
            return;
        }
        
        WxUser wxUser = contactService.queryUser(user);
        if (null == wxUser) {
            logger.info("找不到目标用户,不能发送消息");
            return;
        }
        
        if (StringUtils.equals(wxUser.getUserName(), this.user.getUserName())) {
            logger.warn("WEB微信不能给自己发消息");
            return;
        }
        
        Request.Builder builder = initRequestBuilder("/cgi-bin/mmwebwx-bin/webwxsendmsg");
        Map<String, Object> requestBody = baseRequest();
        String localId = System.currentTimeMillis() + WxUtil.random(4);
        requestBody.put("Scene", 0);
        JSONObject msg = new JSONObject();
        msg.put("Type", 1);
        msg.put("Content", message);
        msg.put("FromUserName", this.user.getUserName());
        msg.put("ToUserName", wxUser.getUserName());
        msg.put("LocalID", localId);
        msg.put("ClientMsgId", localId);
        
        requestBody.put("Msg", msg);
        
        WxUtil.jsonRequest(requestBody, builder::post);
        
        webClient.asynCall(builder.build(), new BaseJsonCallback() {
            
            @Override
            void process(Call call, Response response, JSONObject syncRsp) {
                Integer ret = TypeUtils.castToInt(JSONPath.eval(syncRsp, "BaseResponse.Ret"));
                if (null != ret && 0 == ret) {
                    logger.info("消息发送成功");
                    String msgId = syncRsp.getString("MsgID");
                    msg.put("MsgId", msgId);
                    messageSendListener.success(user, message, msgId, localId);
                } else {
                    logger.info("消息发送失败:{}", syncRsp.toJSONString());
                    messageSendListener.failure(user, message);
                }
            }
        });
    }
    
    /**
     * 发送图片消息
     *
     * @param user x
     * @param message x
     * @param messageSendListener x
     */
    public void sendImageMessage(String touser, String message, File image) {
        // 先上传图片
    }
    
    private String uploadFile(LoginUser user, File file) {
        long size = FileUtils.sizeOf(file);
        MultipartBody body = new MultipartBody.Builder() //
            .setType(MultipartBody.FORM) //
            .addFormDataPart("id", "WU_FILE_0") //
            .addFormDataPart("type", "image/jpeg") //
            .addFormDataPart("lastModifiedDate", new Date().toString()) //
            .addFormDataPart("mediatype", "pic") //
            .addFormDataPart("size", String.valueOf(size)) //
            .addFormDataPart("uploadmediarequest",
                "{\"UploadType\":2,\"BaseRequest\":{\"Uin\":561459895,\"Sid\":\"qyP+fE/NfFSAy8Fo\",\"Skey\":\"@crypt_c65f5df8_d0bd6be662b47631b0601d17b782870d\",\"DeviceID\":\"e860124195388090\"},\"ClientMediaId\":1493878600285,\"TotalLen\":553412,\"StartPos\":0,\"DataLen\":553412,\"MediaType\":4,\"FromUserName\":\"@1a0e3e09b31f36360d585d0e4779313b\",\"ToUserName\":\"@@105fcca34252b098db2faf09d17da73906a749ccc599120c687996ad47e94887\",\"FileMd5\":\"9d43ce83960ab05b6a17ec9a7abcde55\"}") //
            .addFormDataPart("webwx_data_ticket", user.getPassTicket()) //
            .addFormDataPart("pass_ticket", "undefined") //
            .addFormDataPart("mediatype", "pic") //
            .addFormDataPart("filename", "22.jpg", RequestBody.create(null, file)) //
            
            .build();
        
        Request request = new Request.Builder()//
            .url("https://file.wx2.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json")//
            .post(body) //
            .build();
        
        try {
            JSONObject response = webClient.executeJson(request);
        } catch (IOException e) {
            logger.error("上传文件时异常", e);
        }
        return null;
    }
    
    /**
     * 修改群聊名称
     *
     * @param chatRoom
     * @param name
     */
    public void modChatRoomName(String chatRoom, String name) {
        if (!isLogin()) {
            logger.info("还未完成登录,不能发送消息");
        } else {
            WxUser wxUser = contactService.queryUser(chatRoom);
            if (wxUser instanceof WxGroup) {
                // https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxupdatechatroom?fun=modtopic&lang=zh_CN
                Request.Builder builder = initRequestBuilder("/cgi-bin/mmwebwx-bin/webwxupdatechatroom", "fun", "modtopic", "lang", "zh_CN");
                Map<String, Object> requestBody = baseRequest();
                requestBody.put("NewTopic", name);
                requestBody.put("ChatRoomName", wxUser.getUserName());
                WxUtil.jsonRequest(requestBody, builder::post);
                webClient.asynCall(builder.build(), new BaseJsonCallback() {
                    
                    @Override
                    void process(Call call, Response response, JSONObject content) {
                        Integer ret = TypeUtils.castToInt(JSONPath.eval(content, "BaseResponse.Ret"));
                        if (null != ret && 0 == ret) {
                            logger.info("群聊名称修改修改成功");
                        } else {
                            logger.info("群聊名称修改失败:{}", content.toJSONString());
                        }
                    }
                });
            } else {
                logger.warn("要求修改的不是群");
            }
        }
    }
}
