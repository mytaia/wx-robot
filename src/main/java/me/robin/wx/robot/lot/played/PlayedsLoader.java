/**create 2017-05-15**/

package me.robin.wx.robot.lot.played;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import me.robin.wx.robot.lot.entity.GamePlayed;
import me.robin.wx.robot.lot.service.GamePlayedService;

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
public class PlayedsLoader implements InitializingBean {
    
    /** FIXME */
    private static final Logger logger = LoggerFactory.getLogger(PlayedsLoader.class);
    
    /** FIXME */
    @Autowired
    private GamePlayedService playedService;
    
    /** FIXME */
    @Autowired
    private ApplicationContext applicationContext;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        loadPlayeds();
    }
    
    /**
     * 所有的玩法
     */
    private void loadPlayeds() {
        
        Map<String, Playeds> map = getPlayeds();
        List<GamePlayed> playedsList = playedService.findAll();
        try {
            for (GamePlayed gp : playedsList) {
                Class<?> clazz = Class.forName(gp.getPlayedClass());
                if (Played.class.isAssignableFrom(clazz)) {
                    Played played = (Played) ConstructorUtils.invokeConstructor(clazz, gp);
                    Playeds playeds = map.get(gp.getGame());
                    if (playeds != null) {
                        playeds.addPlayed(played);
                    }
                } else {
                    throw new RuntimeException("玩法加载失败[" + gp.toString() + "]");
                }
            }
            
            for (Playeds playeds : map.values()) {
                logger.info("游戏{}共加载到{}种玩法", playeds.getGame(), playeds.getAll().size());
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("玩法加载失败", e);
        }
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @return x
     */
    private Map<String, Playeds> getPlayeds() {
        Map<String, Playeds> mapPlayeds = applicationContext.getBeansOfType(Playeds.class);
        Map<String, Playeds> map = Maps.newHashMap();
        for (Playeds played : mapPlayeds.values()) {
            map.put(played.getGame(), played);
        }
        
        return map;
    }
    
}
