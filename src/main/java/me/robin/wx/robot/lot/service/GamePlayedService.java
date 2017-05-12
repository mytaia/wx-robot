
package me.robin.wx.robot.lot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.repository.GamePlayedDao;

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
@Service
public class GamePlayedService {
    
    /**
     * 
     */
    @Autowired
    private GamePlayedDao playDao;
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param game x
     * @return x
     */
    public List<GamePlayed> findByGame(String game) {
        return playDao.findByGame(game);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param Played x
     * @return x
     */
    public GamePlayed save(GamePlayed Played) {
        return playDao.save(Played);
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param Playeds x
     * @return x
     */
    public List<GamePlayed> save(List<GamePlayed> Playeds) {
        return playDao.save(Playeds);
    }
}
