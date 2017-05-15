/**create 2017-05-15**/

package me.robin.wx.robot.lot.cmd.resolver.bet;

import java.util.List;

import com.google.common.collect.Lists;

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
public class ComboBetResolver implements BetResolver {
    
    /** FIXME */
    private List<BetResolver> resolvers = Lists.newCopyOnWriteArrayList();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input x
     * @return x
     */
    @Override
    public BetRequest resolver(String input) {
        for (BetResolver betResolver : resolvers) {
            BetRequest request = betResolver.resolver(input);
            if (request != null) {
                return request;
            }
        }
        return null;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param resolver x
     */
    public void addResolver(BetResolver resolver) {
        resolvers.add(resolver);
    }
    
    /**
     * @return the resolvers
     */
    public List<BetResolver> getResolvers() {
        return resolvers;
    }
}
