/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.mytaia;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
 * @version 2017年5月4日 作者
 */
public class UploadTest {
    
    public static void main(String[] args) throws IOException {
        
        // byte[] bs = FileUtils.readFileToByteArray(new File("C:\\Users\\huchangjiang\\Pictures\\46bd43e36096a7ffc77fecedee40a191.jpg"));
        File file = new File("C:\\Users\\huchangjiang\\Pictures\\22.jpg");
        
        MultipartBody body = new MultipartBody.Builder() //
            .setType(MultipartBody.FORM) //
            .addFormDataPart("id", "WU_FILE_0") //
            .addFormDataPart("type", "image/jpeg") //
            .addFormDataPart("lastModifiedDate", "Fri Apr 21 2017 10:03:38 GMT+0800") //
            .addFormDataPart("mediatype", "pic") //
            .addFormDataPart("size", "553412") //
            .addFormDataPart("uploadmediarequest",
                "{\"UploadType\":2,\"BaseRequest\":{\"Uin\":561459895,\"Sid\":\"qyP+fE/NfFSAy8Fo\",\"Skey\":\"@crypt_c65f5df8_d0bd6be662b47631b0601d17b782870d\",\"DeviceID\":\"e860124195388090\"},\"ClientMediaId\":1493878600285,\"TotalLen\":553412,\"StartPos\":0,\"DataLen\":553412,\"MediaType\":4,\"FromUserName\":\"@1a0e3e09b31f36360d585d0e4779313b\",\"ToUserName\":\"@@105fcca34252b098db2faf09d17da73906a749ccc599120c687996ad47e94887\",\"FileMd5\":\"9d43ce83960ab05b6a17ec9a7abcde55\"}") //
            .addFormDataPart("webwx_data_ticket", "gSf27jU+XpobWptQpVwB+erz") //
            .addFormDataPart("pass_ticket", "undefined") //
            .addFormDataPart("mediatype", "pic") //
            .addFormDataPart("filename", "22.jpg", RequestBody.create(null, file)) //
            
            .build();
        
        Request b = new Request.Builder()//
            .url("https://file.wx2.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json")//
            .post(body) //
            .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(b).execute();
        System.out.println(response.body().string());
    }
}
