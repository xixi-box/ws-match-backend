package com.ws.match.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//表示这个类是一个配置类,会把这个类注入到ioc容器中
@Configuration
//开启swagger2的功能
@EnableSwagger2WebMvc

@Profile("dev")
public class SwaggerConfig {

    @Bean(value = "defaultApi 2")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.ws.match.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("匹配项目")
                .description("匹配项目接口")
                .termsOfServiceUrl("https://github.com/xixi-box")
                .contact(new Contact("wangshun", "https://github.com/xixi-box", "2606209307@qq.com"))
                .version("1.0")
                .build();
    }
}