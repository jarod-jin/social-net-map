package cn.jarod.socialnetmap.controller

import cn.jarod.socialnetmap.SocialNetMapApplication
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URL


@ExtendWith(SpringExtension::class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = arrayOf(SocialNetMapApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerWebTest {

    @LocalServerPort
    private val port: Int = 0

    private var base: URL? = null

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @BeforeAll
    @Throws(Exception::class)
    fun setUp() {
        this.base = URL("http://localhost:$port/index")
    }

    @Test
    @Throws(Exception::class)
    fun getIndex() {
        val response = restTemplate?.getForEntity(base.toString(), String::class.java)
        assertEquals(200,response?.statusCodeValue)
        assertSame(response?.body, containsString("<title>Spring Boot Kotlin</title>"))
    }
}