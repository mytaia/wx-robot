/**create 2017-05-15**/

package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import me.robin.wx.robot.Application;
import me.robin.wx.robot.frame.api.WxMessageSender;
import me.robin.wx.robot.frame.model.WxMsg;
import me.robin.wx.robot.lot.cmd.BetCommand;
import me.robin.wx.robot.lot.cmd.Commander;
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
 * @version 2017年5月11日 作者
 */
// @ImportResource(value = "classpath:*applicationContext.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommanderTest {
    
    @Autowired
    private Commander commander;
    
    @Autowired
    private WxMessageSender sender;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     */
    @Test
    public void test() {
        sender.sendTextMessage("xx", "abc");
        RequestContext context = new RequestContext();
        WxMsg msg = new WxMsg();
        msg.setContent("平2/单/10");
        msg.setFromUserName("0001");
        context.setMessage(msg);
        BetCommand command = (BetCommand) commander.resolveCommand(context);
        for (int i = 0; i < 5; i++) {
            command.execute(context);
        }
        // SS
    }
    
}
