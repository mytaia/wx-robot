/**create 2017-05-15**/

package me.robin.wx.robot.lot.played.k3;

import org.springframework.stereotype.Component;

import me.robin.wx.robot.lot.constant.BallAttrEnum;
import me.robin.wx.robot.lot.constant.GameEnum;
import me.robin.wx.robot.lot.constant.TermAttrEnum;
import me.robin.wx.robot.lot.played.Played;
import me.robin.wx.robot.lot.played.Playeds;

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
@Component
public class K3Playeds extends Playeds {
    
    /**
     * 构造函数
     * 
     * @param game x
     */
    public K3Playeds() {
        super(GameEnum.K3.code());
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param tremAttr x
     * @return x
     */
    public Played getTermAttrPlayed(TermAttrEnum tremAttr) {
        return getPlayed(tremAttr.code());
    }
    
    /**
     * 返回 第几球单双
     *
     * @param index x
     * @param ballAttr x
     * @return x
     */
    public Played getBallAttrPlayed(int index, BallAttrEnum ballAttr) {
        return getPlayed(ballAttrPlayedKey(index, ballAttr));
    }
    
    /**
     * 全同号
     *
     * @param num 111 或者 222 必须3位一样
     * @return x
     */
    public Played getSanTongHao(String num) {
        return getPlayed(String.format("%s_%s", K3PlayedEnum.SanTongHaoDanXuan.code(), num));
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param ballIndex x
     * @param ballAttr x
     * @return x
     */
    private String ballAttrPlayedKey(int ballIndex, BallAttrEnum ballAttr) {
        return String.format("b%s_%s", ballIndex, ballAttr.code());
    }
    
}
