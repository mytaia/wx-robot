
package me.robin.wx.robot.lot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "lot_game_played")
public class GamePlayed extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8220270052951919500L;
    
    /**
     * ID
     */
    @Id
    @Column(length = 40)
    private String playedId;
    
    /**
     * 游戏名
     */
    @Column(length = 40)
    private String game;
    
    /**
     * 玩法名
     */
    @Column(length = 40)
    private String name;
    
    /**
     * 内部KEY名
     */
    @Column(length = 40)
    private String code;
    
    /** 第几球 */
    @Column
    private Integer ballIndex;
    
    /** 球几号 */
    @Column(length = 10)
    private String ballNum;
    
    /** FIXME */
    @Column(length = 256)
    private String playedClass;
    
    /**
     * @return the playedId
     */
    public String getPlayedId() {
        return playedId;
    }
    
    /**
     * @param playedId the playedId to set
     */
    public void setPlayedId(String playedId) {
        this.playedId = playedId;
    }
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the key
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param code the key to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return the ballNum
     */
    public String getBallNum() {
        return ballNum;
    }
    
    /**
     * @param ballNum the ballNum to set
     */
    public void setBallNum(String ballNum) {
        this.ballNum = ballNum;
    }
    
    /**
     * @return the playedClass
     */
    public String getPlayedClass() {
        return playedClass;
    }
    
    /**
     * @param playedClass the playedClass to set
     */
    public void setPlayedClass(String playedClass) {
        this.playedClass = playedClass;
    }
    
    /**
     * @return the ballIndex
     */
    public Integer getBallIndex() {
        return ballIndex;
    }
    
    /**
     * @param ballIndex the ballIndex to set
     */
    public void setBallIndex(Integer ballIndex) {
        this.ballIndex = ballIndex;
    }
    
    @Override
    public String toString() {
        return "Played [playedId=" + playedId + ", game=" + game + ", name=" + name + ", key=" + code + ", ballIndex=" + ballIndex + ", ballNum="
            + ballNum + ", playedClass=" + playedClass + "]";
    }
    
}
