package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName WebConfig.java
 * @Description TODO
 * @createTime 2023年01月06日 16:09:00
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * 解决 swagger静态资源无法访问的问题
	 *
	 * @param registry 资源处理程序注册表
	 */
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html")
		        .addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
