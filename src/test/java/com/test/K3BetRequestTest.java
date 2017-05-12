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
import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.played.BallAttrPlayed;
import me.robin.wx.robot.lot.played.BallNumMatchPlayed;
import me.robin.wx.robot.lot.played.k3.K3BetResolver;
import me.robin.wx.robot.lot.played.k3.K3PlayedEnum;

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
public class K3BetRequestTest {
    
    @Autowired
    private K3BetResolver betResolver;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Test
    public void test() {
        /*
         * 快3/总和大/10 快3/总和小/10 快3/总和单/10 快3/总和双/10 快3/龙/10 快3/虎/10 快3/平1/单/10 快3/平1双/10 快3/平1/大/10 快3/平1/小10 快3/平2/单/10 快3/平1双/10 快3/平2/大/10 快2/平1/小10
         * 快3/平3/单/10 快3/平3双/10 快3/平3/大/10 快3/平3/小10 快3/111/10 快3/222/10 快3/333/10 快3/444/10 快3/555/10 快3/666/10
         */
        
        BetRequest request = betResolver.resolver("快3/总和大/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), TermAttrEnum.ZhongHeDa.code());
        
        request = betResolver.resolver("快3/总和单/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), TermAttrEnum.ZhongHeDan.code());
        
        request = betResolver.resolver("快3/平1/单/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(((BallAttrPlayed) request.getPlayed()).getBallIndex(), 1);
        Assert.assertEquals(request.getPlayed().getCode(), "b1_" + BallAttrEnum.Dan.code());
        
        request = betResolver.resolver("快3/虎/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), TermAttrEnum.Hu.code());
        
        request = betResolver.resolver("快3/龙/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), TermAttrEnum.Long.code());
        
        request = betResolver.resolver("快3/333/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), K3PlayedEnum.SanTongHaoDanXuan.code() + "_333");
        Assert.assertEquals(((BallNumMatchPlayed) request.getPlayed()).getNum(), "333");
        
        request = betResolver.resolver("快3/111/10");
        Assert.assertEquals(request.getMoney(), new BigDecimal("10"));
        Assert.assertEquals(request.getPlayed().getCode(), K3PlayedEnum.SanTongHaoDanXuan.code() + "_111");
        Assert.assertEquals(((BallNumMatchPlayed) request.getPlayed()).getNum(), "111");
        
        request = betResolver.resolver("快3/123/10");
        Assert.assertEquals(request, null);
        
    }
    
}
