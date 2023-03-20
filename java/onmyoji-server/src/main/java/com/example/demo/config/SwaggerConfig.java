package com.example.demo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName SwaggerConfig.java
 * @Description Swagger配置
 * @createTime 2023年01月03日 11:41:00
 */

@Configuration //声明一个类为配置类，用于取代bean.xml配置文件注册bean对象。
@EnableSwagger2  //启动Swagger2相关功能
@EnableKnife4j   //启动Knife4j相关功能
@Import (BeanValidatorPluginsConfiguration.class) //@Import 注解引入 数据验证插件配置类 BeanValidatorPluginsConfiguration
public class SwaggerConfig {
	
	@Bean //等价于Spring中的bean标签用于注册bean对象的，内部有一些初始化、销毁的属性
	public Docket docket (Environment environment) {
		//如果在dev环境下（开发环境）就开启Swagger
		//boolean isDev = environment.acceptsProfiles (Profiles.of ("dev"));
		return new Docket (DocumentationType.SWAGGER_2)
				.apiInfo (apiInfo ())
				.groupName ("默认接口")
				.enable (true)
				.select ()
				.apis (RequestHandlerSelectors.basePackage ("com.example.demo.controller"))
				.paths (PathSelectors.any ())
				.build ();
	}
	
	private ApiInfo apiInfo () {
		return new ApiInfoBuilder ()
				.title ("阴阳师脚本后端接口文档")
				.description ("通过此文档可以查看、测试后端api")
				.contact (new Contact ("逆戟之刃", "https://github.com/orcakill/leetcode", "1283906021@qq.com"))
				.version ("v1.0.0")
				.build ();
	}
}
