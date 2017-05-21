
package me.robin.wx.robot;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityToolboxView;
import org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityViewResolver;
import org.springframework.context.annotation.Bean;

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
 * @version 2017年5月3日 作者
 */
@SpringBootConfiguration
public class Beans {
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param properties x
     * @return x
     */
    @SuppressWarnings("deprecation")
    @Bean(name = "velocityViewResolver")
    public EmbeddedVelocityViewResolver velocityViewResolver(VelocityProperties properties) {
        EmbeddedVelocityViewResolver resolver = new EmbeddedVelocityViewResolver();
        resolver.setViewClass(EmbeddedVelocityToolboxView.class);
        
        // resolver.setToolboxConfigLocation(url);
        properties.applyToViewResolver(resolver);
        return resolver;
    }
    
}
