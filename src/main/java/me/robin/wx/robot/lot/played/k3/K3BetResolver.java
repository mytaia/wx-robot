/**create 2017-05-15**/

package me.robin.wx.robot.lot.played.k3;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class K3BetResolver extends ComboBetResolver {
    
    /** FIXME */
    private static final String MONEY = "\\d{1,5}";
    
    /** FIXME */
    private static final String TERM_ATTR = getRegxString(new TermAttrEnum[] {
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
        TermAttrEnum.Hu }); // "(总和大|总和小|总和单|总和双|龙|虎)";
    
    /** FIXME */
    private static final String BALL_ATTR = getRegxString(new BallAttrEnum[] {
        /** FIXME */
        BallAttrEnum.Da,
        /** FIXME */
        BallAttrEnum.Xiao,
        /** FIXME */
        BallAttrEnum.Dan,
        /** FIXME */
        BallAttrEnum.Shuang });// "(大|小|单|双)";
    
    /** FIXME */
    private final static Pattern TermRegex[] = new Pattern[] { //
        regex("^快(三|3)/(?<termAttr>${TERM_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern BallAttrRegex[] = new Pattern[] { //
        regex("^快(三|3)/平(?<ballIndex>[1-3])/(?<ballAttr>${BALL_ATTR})/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    private final static Pattern TongHaoRegex[] = new Pattern[] { //
        regex("^快(三|3)/(?<ballNum>(111)|(222)|(333)|(444)|(555)|(666))/(?<money>${MONEY})$"), //
    };
    
    /** FIXME */
    @Autowired
    private K3Playeds playeds;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param regex x
     * @return x
     */
    private static Pattern regex(String regex) {
        return Pattern.compile( //
            regex.replace("${BALL_ATTR}", BALL_ATTR)//
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
    public K3BetResolver() {
        addResolver(new TermResolver());
        addResolver(new BallAttrResolver());
        addResolver(new TongHaoResolver());
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
            super(GameEnum.K3.code(), TermRegex);
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
            super(GameEnum.K3.code(), BallAttrRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            String ballAttr = matcher.group("ballAttr");
            String ballIndex = matcher.group("ballIndex");
            
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
    class TongHaoResolver extends RegexBetResolver {
        
        /**
         * 构造函数
         * 
         * @param regexs x
         */
        public TongHaoResolver() {
            super(GameEnum.K3.code(), TongHaoRegex);
        }
        
        @Override
        public void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher) {
            String money = matcher.group("money");
            bet.setMoney(new BigDecimal(money));
            String ballNum = matcher.group("ballNum");
            bet.setInfo(ballNum);
            Played played = playeds.getSanTongHao(ballNum);
            bet.setPlayed(played);
        }
        
    }
}
