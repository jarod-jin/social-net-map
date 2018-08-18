package cn.jarod.socialnetmap

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class SocialNetMapApplication

fun main(args: Array<String>) {
    SpringApplication.run(SocialNetMapApplication::class.java, *args)
}
