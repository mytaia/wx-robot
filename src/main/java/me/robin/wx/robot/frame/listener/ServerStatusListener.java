
package me.robin.wx.robot.frame.listener;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import me.robin.wx.robot.frame.api.WxApi;
import me.robin.wx.robot.frame.message.MsgHandler;
import me.robin.wx.robot.frame.model.WxMsg;

/**
 * Created by xuanlubin on 2017/4/19.
 */
public interface ServerStatusListener {
    
    void registerMessageHandler(MsgHandler msgHandler);
    
    void onUUIDSuccess(String url);
    
    void onAddMsgList(List<WxMsg> messages, WxApi api);
    
    void onModContactList(JSONArray modContactList, WxApi api);
    
    void onDelContactList(JSONArray delContactList, WxApi api);
    
    void onModChatRoomMemberList(JSONArray modChatRoomMemberList, WxApi api);
    
}
