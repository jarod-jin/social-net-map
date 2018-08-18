package cn.jarod.socialnetmap.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
open class Swagger2 {

    @Bean
    open fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)  // 使用Swagger2
                .apiInfo(apiInfo())                 // 设置接口页面信息
                .select()                           // 返回ApiSelectorBuilder的实例
                .apis(RequestHandlerSelectors.basePackage("io.ride.vote.web"))      // api接口所在的包
                .paths(PathSelectors.any())
                .build()
    }

    /**
     * 页面信息展示
     */
    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Vote RestFul APIs文档")
                .description("项目API接口文档")
                .contact(Contact("ride", "", "supreDong@gamil.com"))
                .version("0.0.1")
                .build()
    }
}