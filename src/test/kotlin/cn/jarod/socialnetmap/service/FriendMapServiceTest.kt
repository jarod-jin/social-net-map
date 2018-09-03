package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.SocialNetMapApplication
import cn.jarod.socialnetmap.service.impl.FriendMapService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(SocialNetMapApplication::class))
class FriendMapServiceTest{

    @Autowired
    private lateinit var friendMapService: FriendMapService;

    @Test
    fun findByPoiAndMapLevel(){
        friendMapService.findByPoiAndMapLevel(1.0,1.0,5)
    }

    @Test
    fun findByName(){
        var boo = friendMapService.findByName("王五")
        assert(boo.isNotEmpty())
    }

    @Test
    fun addOne(){
        var result = friendMapService.add("王五",120.62034,30.463911,2431829283445, arrayOf("傻瓜","2货"))
        assert(result=="success")
    }


}