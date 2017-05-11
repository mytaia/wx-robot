/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.domain.k10;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.EnumAware;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.model.Played;
import me.robin.wx.robot.lot.resolver.ComboBetResolver;
import me.robin.wx.robot.lot.resolver.RegexBetResolver;

/**
 * 指令识别
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
@Component
public class K10BetResolver extends ComboBetResolver {
    
    /** FIXME */
    private static final String MONEY = "\\d{1,5}";
    
    /** FIXME */
    private static final String TERM_ATTR = getRegxString(TermAttrEnum.values()); // "(总和大|总和小|总和单|总和双|总和尾大|总和尾小|龙|虎)";
    
    /** FIXME */
    private static final String BALL_ATTR = getRegxString(BallAttrEnum.values()); // "(大|小|单|双|尾大|尾小|合单|合双|合大|合小)";
    
    /** FIXME */
    private static final String BALL_NUM = "([1-9]|0[1-9]|1\\d|2[01])";
    
    /** FIXME */
    private final static Pattern TermRegex[] = new Pattern[] { //
        regex("^$(?<termAttr>{TERM_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern BallAttrRegex[] = new Pattern[] { //
        regex("^(平(?<ballIndex>[1-5])|特码)/(?<ballAttr>${BALL_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern BallNumRegex[] = new Pattern[] { //
        regex("^(平(?<ballIndex>[1-5])|特码)/(?<ballNum>${BALL_NUM})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern comboRegex[] = new Pattern[] { //
        regex("^(?<ballCount>1)中1/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){0,4})/(?<money>${MONEY})$"), //
        regex("^(?<ballCount>2)中2/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){1,4})/(?<money>${MONEY})$"), //
        regex("^(?<ballCount>[3-5])中3/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){2,4})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    @Autowired
    private K10Playeds playeds;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param regex x
     * @return x
     */
    private static Pattern regex(String regex) {
        return Pattern.compile( //
            regex.replace("${BALL_NUM}", BALL_NUM) //
                .replace("${BALL_ATTR}", BALL_ATTR)//
                .replace("${TERM_ATTR}", TERM_ATTR)//
                .replace("${MONEY}", MONEY));
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param es x
     * @return x
     */
    private static String getRegxString(EnumAware[] es) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (EnumAware e : es) {
            if (sb.length() > 1) {
                sb.append("|");
            }
            sb.append(e.id());
        }
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * 构造函数
     */
    public K10BetResolver() {
        addResolver(new TermResolver());
        addResolver(new BallAttrResolver());
        addResolver(new BallNumResolver());
        addResolver(new ComboResolver());
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
     * @version 2017年5月11日 作者
     */
    class TermResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public TermResolver() {
            super(GameEnum.K10.id(), TermRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            TermAttrEnum tremAttr = TermAttrEnum.valueOf(matcher.group("tremAttr"));
            Played played = playeds.getTermAttrPlayed(tremAttr);
            bet.setPlayed(played);
        }
        
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
     * @version 2017年5月11日 作者
     */
    class BallAttrResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public BallAttrResolver() {
            super(GameEnum.K10.id(), BallAttrRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            String ballNum = matcher.group("ballNum");
            String ballIndex = matcher.group("ballIndex");
            Played played = playeds.getBallNumPlayed(ballIndex, ballNum);
            bet.setPlayed(played);
        }
        
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
     * @version 2017年5月11日 作者
     */
    static class BallNumResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public BallNumResolver() {
            super(GameEnum.K10.id(), BallNumRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
        }
        
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
     * @version 2017年5月11日 作者
     */
    static class ComboResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public ComboResolver() {
            super(GameEnum.K10.id(), comboRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
        }
        
    }
}
