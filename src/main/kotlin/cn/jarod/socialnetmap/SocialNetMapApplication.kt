package cn.jarod.socialnetmap

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
open class SocialNetMapApplication{

/*    @Bean
    open fun fastJsonHttpMessageConverters(): HttpMessageConverters {
        val fastConverter = FastJsonHttpMessageConverter()
        val fastJsonConfig = FastJsonConfig()
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat)
        fastConverter.fastJsonConfig = fastJsonConfig
        return HttpMessageConverters(fastConverter)
    }*/
}

fun main(args: Array<String>) {
    SpringApplication.run(SocialNetMapApplication::class.java, *args)
}