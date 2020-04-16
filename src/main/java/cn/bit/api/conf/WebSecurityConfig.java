package cn.bit.api.conf;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录配置 博客出处：http://www.cnblogs.com/GoodHelper/
 *
 */
@Configuration
@Import(value = {cn.bit.api.aspect.SafetyAspect.class})
public class WebSecurityConfig extends WebMvcAutoConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer() {
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("index.html").setViewName("index");
            }
        };
    }

}