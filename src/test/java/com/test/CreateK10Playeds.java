/**create 2017-05-15**/

package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import me.robin.wx.robot.Application;
import me.robin.wx.robot.lot.BallUtils;
import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.BallComboEnum;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.played.factory.BallAttrPlayedFactory;
import me.robin.wx.robot.lot.played.factory.BallComboPlayedFactory;
import me.robin.wx.robot.lot.played.factory.BallNumPlayedFactory;
import me.robin.wx.robot.lot.played.factory.TermAttrPlayedFactory;
import me.robin.wx.robot.lot.service.GamePlayedService;

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
public class CreateK10Playeds {
    
    @Autowired
    private GamePlayedService playedService;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Test
    public void createBallComboPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (BallComboEnum item : BallComboEnum.values()) {
            GamePlayed played = new GamePlayed();
            played.setGame(GameEnum.K10.code());
            played.setCode(item.code());
            played.setName(item.description());
            played.setPlayedClass(BallComboPlayedFactory.class.getName());
            played.setPlayedId(BallUtils.playedId(played.getGame(), played.getCode()));
            list.add(played);
        }
        playedService.save(list);
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    @Test
    public void createTermAttrPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (TermAttrEnum item : TermAttrEnum.values()) {
            GamePlayed played = new GamePlayed();
            played.setGame(GameEnum.K10.code());
            played.setCode(item.code());
            played.setName(item.description());
            played.setPlayedClass(TermAttrPlayedFactory.class.getName());
            played.setPlayedId(BallUtils.playedId(played.getGame(), played.getCode()));
            list.add(played);
        }
        playedService.save(list);
        
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    @Test
    public void createBallAttrPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            for (BallAttrEnum item : BallAttrEnum.values()) {
                GamePlayed played = new GamePlayed();
                played.setGame(GameEnum.K10.code());
                played.setCode("b" + (i) + "_" + item.code());
                played.setBallIndex(i);
                played.setName(BallUtils.ballIndexString(i) + item.description());
                played.setPlayedClass(BallAttrPlayedFactory.class.getName());
                played.setPlayedId(BallUtils.playedId(played.getGame(), played.getCode()));
                list.add(played);
            }
        }
        
        playedService.save(list);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    @Test
    public void createBallNumPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            for (int n = 1; n <= 21; n++) {
                GamePlayed played = new GamePlayed();
                played.setGame(GameEnum.K10.code());
                played.setCode("b" + (i) + "_" + n);
                played.setBallIndex(i);
                played.setName(BallUtils.ballIndexString(i) + BallUtils.ballNum(n, 2));
                played.setPlayedClass(BallNumPlayedFactory.class.getName());
                played.setPlayedId(BallUtils.playedId(played.getGame(), played.getCode()));
                list.add(played);
            }
        }
        playedService.save(list);
    }
    
}
