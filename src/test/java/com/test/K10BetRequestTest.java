/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import me.robin.wx.robot.Application;
import me.robin.wx.robot.lot.constant.BallComboEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.played.k10.K10BetResolver;

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
// @ImportResource(value = "classpath:*applicationContext.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class K10BetRequestTest {
    
    @Autowired
    private K10BetResolver betResolver;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Test
    public void test() {
        BetRequest request = betResolver.resolver("平2/单/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), "b2_dan");
        
        request = betResolver.resolver("平5/单/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), "b5_dan");
        
        request = betResolver.resolver("特码/单/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), "b5_dan");
        
        request = betResolver.resolver("虎/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), TermAttrEnum.Hu.code());
        
        request = betResolver.resolver("1中1/10");
        Assert.assertEquals(request, null);
        
        request = betResolver.resolver("1中1/2,3/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), BallComboEnum.In1_1.code());
        
        request = betResolver.resolver("5中3/2,3，4,5,6,7/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), BallComboEnum.In5_3.code());
    }
    
}
