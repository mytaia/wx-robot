/**create 2017-05-15**/

package me.robin.wx.robot.compant;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;

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
 * @version 2017年5月18日 作者
 */
@Component
public class InfoNotifier {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(InfoNotifier.class);
    
    /** FIXME */
    private final static String NOTIFY_URL = "http://sc.ftqq.com/SCU8041Tcda432d492f51ce7100418910f7a8e445907fc1f5bb15.send";
    
    /** FIXME */
    @Value("${loginNotify:false}")
    private boolean loginNotify;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param title x
     * @param content x
     */
    public void notify(String title, String content) {
        if (!loginNotify) {
            return;
        }
        
        try {
            String res = WebClient.instance.downloadString(NOTIFY_URL, ImmutableMap.<String, String> of("text", title, "desp", content));
            Result result = JSON.parseObject(res, Result.class);
            if (result.errno != 0) {
                logger.error("通知异常{} [title={},content={}],{}", result.errmsg, title, content);
            }
        } catch (Exception e) {
            logger.error(String.format("通知异常[title=%s,content=%s]", title, content), e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param title x
     */
    public void notify(String title) {
        Objects.requireNonNull(title);
        notify(title, "");
    }
    
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
     * @version 2017年5月18日 作者
     */
    static class Result {
        
        /** FIXME */
        public int errno;
        
        /** FIXME */
        public String errmsg;
        
        /** FIXME */
        public String dataset;
    }
}
