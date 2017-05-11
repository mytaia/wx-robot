
package me.robin.wx.robot.frame.model.req;

import me.robin.wx.robot.frame.model.LoginUser;
import me.robin.wx.robot.frame.util.WxUtil;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2017年5月4日 作者
 */
public class MsgRequest extends AbstractBaseRequest {
    
    /** FIXME */
    public WxMsg Msg;
    
    /** FIXME */
    public int Scene = 0;
    
    /**
     * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
     * 
     * <pre>
     * [
     * 调用关系:
     * 实现接口及父类:
     * 子类:
     * 内部类列表:
     * ]
     * </pre>
     * 
     * @author 作者
     * @since 1.0
     * @version 2017年5月4日 作者
     */
    public static class Builder {
        
        /** FIXME */
        private LoginUser user;
        
        /** FIXME */
        private WxMsg msg = new WxMsg();
        
        /**
         * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
         *
         * @param user x
         * @return x
         */
        public Builder user(LoginUser user) {
            this.user = user;
            return this;
        }
        
        /**
         * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
         *
         * @param type x
         * @return x
         */
        public Builder type(int type) {
            msg.Type = type;
            return this;
        }
        
        /**
         * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
         *
         * @param content x
         * @return x
         */
        public Builder content(String content) {
            msg.Content = content;
            return this;
        }
        
        /**
         * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
         *
         * @param toUserName x
         * @return x
         */
        public Builder toUserName(String toUserName) {
            msg.ToUserName = toUserName;
            return this;
        }
        
        /**
         * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
         *
         * @return x
         */
        public MsgRequest build() {
            MsgRequest request = new MsgRequest();
            request.BaseRequest = WxBaseRequest.from(user);
            msg.FromUserName = user.getUserName();
            msg.ClientMsgId = msg.LocalID = System.currentTimeMillis() + WxUtil.random(4);
            request.Msg = msg;
            return request;
        }
    }
}
