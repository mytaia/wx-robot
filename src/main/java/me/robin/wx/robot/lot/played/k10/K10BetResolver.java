/**create 2017-05-15**/

package me.robin.wx.robot.lot.played.k10;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.cmd.resolver.CommandResolverException;
import me.robin.wx.robot.lot.cmd.resolver.bet.ComboBetResolver;
import me.robin.wx.robot.lot.cmd.resolver.bet.RegexBetResolver;
import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.EnumAware;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.played.Played;

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
    private static final String BALL_NUM_TE = "特码";
    
    /** FIXME */
    private final static Pattern TermRegex[] = new Pattern[] { //
        regex("^(?<termAttr>${TERM_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern BallAttrRegex[] = new Pattern[] { //
        regex("^(?<ball>平(?<ballIndex>[1-5])|" + BALL_NUM_TE + ")/(?<ballAttr>${BALL_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern BallNumRegex[] = new Pattern[] { //
        regex("^(?<ball>平(?<ballIndex>[1-5])|" + BALL_NUM_TE + ")/(?<ballNum>${BALL_NUM})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern comboRegex[] = new Pattern[] { //
        regex("^(?<ballCount>1)中(?<ballIn>1)/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){0,4})/(?<money>${MONEY})$"), //
        regex("^(?<ballCount>2)中(?<ballIn>2)/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){1,4})/(?<money>${MONEY})$"), //
        regex("^(?<ballCount>[3-5])中(?<ballIn>3)/(?<ballNum>${BALL_NUM}([,，]${BALL_NUM}){2,6})/(?<money>${MONEY})$"), //
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
            sb.append(e.description());
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
            super(GameEnum.K10.code(), TermRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            TermAttrEnum tremAttr = TermAttrEnum.fromDescription(matcher.group("termAttr"));
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
            super(GameEnum.K10.code(), BallAttrRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            String ballAttr = matcher.group("ballAttr");
            String ballIndex = matcher.group("ballIndex");
            if (ballIndex == null && BALL_NUM_TE.equals(matcher.group("ball"))) {
                // 说明是特码
                ballIndex = "5";
            }
            
            int index = Integer.parseInt(ballIndex);
            
            Played played = playeds.getBallAttrPlayed(index, BallAttrEnum.fromDescription(ballAttr));
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
    class BallNumResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public BallNumResolver() {
            super(GameEnum.K10.code(), BallNumRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            String ballNum = matcher.group("ballNum");
            String ballIndex = matcher.group("ballIndex");
            if (ballIndex == null && BALL_NUM_TE.equals(matcher.group("ball"))) {
                // 说明是特码
                ballIndex = "5";
            }
            int index = Integer.parseInt(ballIndex);
            Played played = playeds.getBallNumPlayed(index, ballNum);
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
    class ComboResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public ComboResolver() {
            super(GameEnum.K10.code(), comboRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest request, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            request.setMoney(new BigDecimal(money));
            int count = Integer.parseInt(matcher.group("ballCount"));
            int in = Integer.parseInt(matcher.group("ballIn"));
            String ballNum = matcher.group("ballNum");
            String[] nums = ballNum.split("[,，]");
            HashSet<String> set = Sets.newHashSet();
            set.addAll(Arrays.asList(nums));
            if (set.size() != nums.length) {
                throw new CommandResolverException("复选不能有重复的号码");
            }
            request.setInfo(nums);
            Played played = playeds.getBallComboPlayed(count, in);
            request.setPlayed(played);
        }
        
    }
}
