package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.SocialNetMapApplication
import cn.jarod.socialnetmap.service.impl.FriendMapService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
class FriendMapServiceTest{

    @Autowired
    private lateinit var friendMapService: FriendMapService;

    @Test
    @DisplayName("测试查询")
    fun findByPoiAndMapLevel(){
        var boo = friendMapService.findByPoiAndMapLevel(1.0,1.0,5)
        assert(boo.isNotEmpty())
    }

    @Test
    fun findByName(){
        var boo = friendMapService.findByName("王五")
        assert(boo.isNotEmpty())
    }

    @Test
    fun addOne(){
        var result = friendMapService.add("王五",120.62034,30.463911,2431829283445,"传化大厦", arrayOf("傻瓜","2货"))
        assert(result=="success")
    }



}