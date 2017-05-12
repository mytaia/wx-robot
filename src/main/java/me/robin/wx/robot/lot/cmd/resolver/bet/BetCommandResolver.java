/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd.resolver.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.cmd.BetCommand;
import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.RequestContext;
import me.robin.wx.robot.lot.cmd.resolver.CommandResolver;
import me.robin.wx.robot.lot.model.BetRequest;
import me.robin.wx.robot.lot.played.k10.K10BetResolver;
import me.robin.wx.robot.lot.played.k3.K3BetResolver;

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
 * @version 2017年5月14日 作者
 */
@Component
public class BetCommandResolver implements CommandResolver {
    
    /** FIXME */
    private ComboBetResolver betResolver;
    
    /** FIXME */
    @Autowired
    private BetCommand betCommand;
    
    /**
     * 构造函数
     * 
     * @param k3BetResolver x
     * @param k10betResolver x
     */
    public BetCommandResolver(@Autowired K3BetResolver k3BetResolver, @Autowired K10BetResolver k10betResolver) {
        betResolver = new ComboBetResolver();
        betResolver.addResolver(k10betResolver);
        betResolver.addResolver(k3BetResolver);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input x
     * @return x
     */
    @Override
    public Command resolveCommand(RequestContext context) {
        String input = context.getInput();
        BetRequest request = betResolver.resolver(input);
        if (request == null) {
            return null;
        }
        context.setBetRequest(request);
        return betCommand;
        
    }
    
}
