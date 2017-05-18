/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.compant;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
 * @version 2017年5月14日 作者
 */
public class WebClient {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(WebClient.class);
    
    /** FIXME */
    private OkHttpClient client;
    
    /** FIXME */
    public static WebClient instance = new WebClient();
    
    /**
     * 构造函数
     */
    private WebClient() {
        this.client = new OkHttpClient.Builder() //
            .readTimeout(25, TimeUnit.SECONDS) //
            .connectTimeout(25, TimeUnit.SECONDS) //
            .build();
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @param callback x
     */
    public void asynCall(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @param callback x
     * @return x
     * @throws IOException x
     */
    public Response execute(Request request) throws IOException {
        return client.newCall(request).execute();
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @param callback x
     * @return x
     * @throws IOException x
     */
    public String executeString(Request request) throws IOException {
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param request x
     * @param callback x
     * @return x
     * @throws IOException x
     */
    public JSONObject executeJson(Request request) throws IOException {
        String content = executeString(request);
        if (StringUtils.isNotBlank(content)) {
            return JSONObject.parseObject(content);
        }
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param url x
     * @return x
     */
    public String downloadString(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("请求异常 url =" + url, e);
        }
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param url x
     * @param params x
     * @return x
     */
    public String downloadString(String url, Map<String, String> params) {
        
        Request.Builder request = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(params)) {
            FormBody.Builder form = new FormBody.Builder();
            params.forEach((k, v) -> form.add(k, v));
            request.post(form.build());
        }
        try {
            Response response = client.newCall(request.build()).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("请求异常 url =" + url, e);
        }
        return null;
    }
    
}
