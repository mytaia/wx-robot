/**create 2017-05-15**/

package com.test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
 * @version 2017年5月11日 作者
 */
public class JsonTest {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    public void test() {
        File file;
        try {
            
            file = ResourceUtils.getFile(JsonTest.class.getResource("json.txt"));
            String str = FileUtils.readFileToString(file, "utf-8");
            JSONObject obj = JSON.parseObject(str);
            JSONArray arr = obj.getJSONArray("AddMsgList");
            Object ss1 = obj.get("AddMsgList");
            
            System.out.println(ss1.getClass());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void test1() {
        final Pattern MESSAGE_PATTERN = Pattern.compile("^(@[0-9a-z]*?):<br/>");
        String content = "@123:<br/>content\n456";
        Matcher matcher = MESSAGE_PATTERN.matcher(content);
        if (matcher.find()) {
            String sender = matcher.group(1);
            Assert.assertEquals(sender, "@123");
            content = content.substring(matcher.group().length());
            
            Assert.assertEquals(content, "content\n456");
        }
    }
}
