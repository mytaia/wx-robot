
package me.robin.wx.robot.frame.api;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
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
 * @version 2017年5月3日 作者
 */
@Component
public class WebClient {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(WebClient.class);
    
    /** FIXME */
    private OkHttpClient client;
    
    /**
     * 构造函数
     */
    public WebClient() {
        this.client = new OkHttpClient.Builder() //
            .readTimeout(60, TimeUnit.SECONDS) //
            .connectTimeout(60, TimeUnit.SECONDS) //
            .addInterceptor(new CookieInterceptor()) //
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
     * @version 2017年5月3日 作者
     */
    static class CookieInterceptor implements Interceptor {
        
        /** FIXME */
        private CookieHandler cookieHandler = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        
        /**
         * 构造函数
         */
        public CookieInterceptor() {
        }
        
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            URI uri = request.url().uri();
            List<String> cookies = cookieHandler.get(uri, new HashMap<>()).get("Cookie");
            
            Response networkResponse;
            if (null != cookies && !cookies.isEmpty()) {
                Request.Builder requestBuilder = request.newBuilder();
                requestBuilder.header("Cookie", StringUtils.join(cookies, "; "));
                networkResponse = chain.proceed(requestBuilder.build());
                networkResponse = networkResponse.newBuilder().request(request).build();
            } else {
                networkResponse = chain.proceed(request);
            }
            Headers headers = networkResponse.headers();
            if (headers.size() > 0) {
                Map<String, List<String>> headersMap = new HashMap<>();
                for (String name : headers.names()) {
                    List<String> values = headers.values(name);
                    headersMap.put(name, values);
                }
                cookieHandler.put(uri, headersMap);
            }
            return networkResponse;
        }
    }
    
}
