/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

import me.robin.wx.robot.compant.WebClient;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下注的指令处理器
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
 * @version 2017年5月14日 作者
 */
@Component
public class WangPanCommandSupport {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(WangPanCommandSupport.class);
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param url x
     * @param params x
     * @return x
     * @throws IOException x
     */
    public WangPanRespon doRequest(String url, Map<String, Object> params) throws IOException {
        
        Request req = builderRequest(url, params);
        
        Response response = WebClient.instance.execute(req);
        String json = response.body().string();
        return JSON.parseObject(json, WangPanRespon.class);
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param url x
     * @param params x
     * @return x
     */
    private Request builderRequest(String url, Map<String, Object> params) {
        Request.Builder req = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(params)) {
            FormBody.Builder form = new FormBody.Builder();
            params.forEach((k, v) -> {
                form.add(k, String.valueOf(v));
            });
        }
        return req.build();
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
     * @version 2017年5月17日 作者
     */
    public static class WangPanRespon {
        
        /** FIXME */
        public String msg;
        
        /** FIXME */
        public BigDecimal totalsum;
        
        /** FIXME */
        public String yuer;
        
        /** FIXME */
        public int code;
    }
    
}
