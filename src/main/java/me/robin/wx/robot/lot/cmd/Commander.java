/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import me.robin.wx.robot.lot.cmd.resolver.CommandResolver;
import me.robin.wx.robot.lot.core.RequestContext;

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
public class Commander implements BeanPostProcessor {
    
    /** FIXME */
    private List<CommandResolver> commands = Lists.newCopyOnWriteArrayList();
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     * 
     * @param context x
     * @return x
     */
    public Command resolveCommand(RequestContext context) {
        for (CommandResolver cr : commands) {
            Command cmd = cr.resolveCommand(context);
            if (cmd != null) {
                return cmd;
            }
        }
        return null;
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CommandResolver) {
            commands.add((CommandResolver) bean);
        }
        return bean;
    }
    
}
