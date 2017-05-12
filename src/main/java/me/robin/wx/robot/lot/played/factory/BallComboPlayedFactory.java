
package me.robin.wx.robot.lot.played.factory;

import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.played.BallComboPlayed;
import me.robin.wx.robot.lot.played.Played;

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
 * @version 2017年5月13日 作者
 */
@Component
public class BallComboPlayedFactory implements PlayedFactory {
    
    @Override
    public Played load(GamePlayed gamePlayed) {
        BallComboPlayed played = new BallComboPlayed(gamePlayed);
        return played;
    }
    
}
