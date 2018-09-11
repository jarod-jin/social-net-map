package cn.jarod.socialnetmap

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
//@EnableAutoConfiguration(exclude = arrayOf(DataSourceAutoConfiguration::class))
@EnableTransactionManagement
open class SocialNetMapApplication{
}

fun main(args: Array<String>) {
    SpringApplication.run(SocialNetMapApplication::class.java, *args)
}