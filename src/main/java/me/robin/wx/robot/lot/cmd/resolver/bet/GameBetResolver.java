/**create 2017-05-15**/

package me.robin.wx.robot.lot.cmd.resolver.bet;

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
public abstract class GameBetResolver implements BetResolver {
    
    /** FIXME */
    private String game;
    
    /**
     * 构造函数
     * 
     * @param game x
     * @param regexs x
     */
    public GameBetResolver(String game) {
        this.game = game;
    }
    
    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }
}
