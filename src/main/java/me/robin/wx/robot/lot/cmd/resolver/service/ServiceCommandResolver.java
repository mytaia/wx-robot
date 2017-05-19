/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.lot.cmd.resolver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.cmd.Command;
import me.robin.wx.robot.lot.cmd.resolver.CommandResolver;
import me.robin.wx.robot.lot.constant.ServicCommandeEnum;
import me.robin.wx.robot.lot.core.RequestContext;
import me.robin.wx.robot.lot.core.ServiceRequest;

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
public class ServiceCommandResolver implements CommandResolver {
    
    /** FIXME */
    @Autowired
    private ServiceCommand serviceCommand;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param input x
     * @return x
     */
    @Override
    public Command resolveCommand(RequestContext context) {
        String input = context.getInput();
        ServicCommandeEnum sce = ServicCommandeEnum.fromDescription(input);
        if (sce == null) {
            return null;
        }
        ServiceRequest request = new ServiceRequest();
        request.setServicCommandeEnum(sce);
        context.setMessageRequest(request);
        return serviceCommand;
    }
    
}
