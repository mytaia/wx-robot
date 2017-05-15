/**create 2017-05-15**/

package me.robin.wx.robot.lot.core;

/**
 * 每期的开奖结果
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
public class TermResult {
    
    /** FIXME */
    private String game;
    
    /** FIXME */
    private String term;
    
    /** FIXME */
    private String[] balls;
    
    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }
    
    /**
     * @param game the game to set
     */
    public void setGame(String game) {
        this.game = game;
    }
    
    /**
     * @return the balls
     */
    public String[] getBalls() {
        return balls;
    }
    
    /**
     * @param balls the balls to set
     */
    public void setBalls(String[] balls) {
        this.balls = balls;
    }
    
    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }
    
    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }
    
}
