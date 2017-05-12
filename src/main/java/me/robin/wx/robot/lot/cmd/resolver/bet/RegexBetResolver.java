/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd.resolver.bet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.robin.wx.robot.lot.model.BetRequest;

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
public abstract class RegexBetResolver extends GameBetResolver {
    
    /** FIXME */
    private Pattern[] regexs;
    
    /**
     * 构造函数
     * 
     * @param game x
     * @param regexs x
     */
    public RegexBetResolver(String game, Pattern[] regexs) {
        super(game);
        this.regexs = regexs;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input x
     * @return x
     */
    public boolean matches(String input) {
        for (Pattern regex : regexs) {
            if (regex.matcher(input).matches()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public BetRequest resolver(String input) {
        for (Pattern regex : regexs) {
            Matcher matcher = regex.matcher(input);
            if (matcher.find()) {
                BetRequest bet = new BetRequest();
                bet.setGame(this.getGame());
                bet.setInput(input);
                resolver(input, bet, regex, matcher);
                return bet;
            }
        }
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input x
     * @param bet x
     * @param regex x
     * @param matcher x
     */
    public abstract void resolver(String input, BetRequest bet, Pattern regex, Matcher matcher);
    
}
