/**create 2017-05-15**/

package com.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.played.BallAttrPlayed;
import me.robin.wx.robot.lot.played.BallNumMatchPlayed;
import me.robin.wx.robot.lot.played.TermAttrPlayed;
import me.robin.wx.robot.lot.played.k3.K3PlayedEnum;
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
public class CreateK3Playeds {
    
    /** FIXME */
    private static final TermAttrEnum[] TERM_ATTR = new TermAttrEnum[] {
        /** FIXME */
        TermAttrEnum.ZhongHeDa,
        /** FIXME */
        TermAttrEnum.ZhongHeXiao,
        /** FIXME */
        TermAttrEnum.ZhongHeDan,
        /** FIXME */
        TermAttrEnum.ZhongHeSuang,
        /** FIXME */
        TermAttrEnum.Long,
        /** FIXME */
        TermAttrEnum.Hu }; // "(总和大|总和小|总和单|总和双|龙|虎)";
    
    /** FIXME */
    private static final BallAttrEnum[] BALL_ATTR = new BallAttrEnum[] {
        /** FIXME */
        BallAttrEnum.Da,
        /** FIXME */
        BallAttrEnum.Xiao,
        /** FIXME */
        BallAttrEnum.Dan,
        /** FIXME */
        BallAttrEnum.Shuang };// "(大|小|单|双)";
    
    @Autowired
    private GamePlayedService playedService;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param items x
     */
    @Test
    public void createTermAttrPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (TermAttrEnum item : TERM_ATTR) {
            GamePlayed played = new GamePlayed();
            played.setGame(GameEnum.K3.code());
            played.setCode(item.code());
            played.setName(item.description());
            played.setPlayedClass(TermAttrPlayed.class.getName());
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
        for (int i = 1; i <= 3; i++) {
            for (BallAttrEnum item : BALL_ATTR) {
                GamePlayed played = new GamePlayed();
                played.setGame(GameEnum.K3.code());
                played.setCode("b" + (i) + "_" + item.code());
                played.setBallIndex(i);
                played.setName(BallUtils.ballIndexString(i) + item.description());
                played.setPlayedClass(BallAttrPlayed.class.getName());
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
    public void createTongHaoPlayed() {
        List<GamePlayed> list = Lists.newArrayList();
        for (int i = 1; i <= 6; i++) {
            String num = StringUtils.repeat("" + i, 3);
            GamePlayed played = new GamePlayed();
            played.setGame(GameEnum.K3.code());
            played.setBallNum(num);
            played.setCode(K3PlayedEnum.SanTongHaoDanXuan.code() + "_" + num);
            played.setName(K3PlayedEnum.SanTongHaoDanXuan.description() + num);
            played.setPlayedClass(BallNumMatchPlayed.class.getName());
            played.setPlayedId(BallUtils.playedId(played.getGame(), played.getCode()));
            list.add(played);
            
        }
        playedService.save(list);
    }
    
}
