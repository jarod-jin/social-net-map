package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.model.FriendDTO
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
    @DisplayName("插入一条数据王五的朋友数据")
    fun add_wang5(){
        var friendDTO = FriendDTO(
                id = null,
                name = "王五",
                lng = 120.62034,
                lat = 30.463911,
                addr = "传化大厦",
                openId = 2431829283445,
                tags = arrayOf("傻瓜","2货")
        )
        var result = friendMapService.saveOne(friendDTO)
        assert(result=="success")
    }

    @Test
    @DisplayName("插入一条数据马六的朋友数据，tags比王五多一条")
    fun add_ma6(){
        var friendDTO = FriendDTO(
                id = null,
                name = "马六",
                lng = 120.020035,
                lat = 30.433911,
                addr = "传化大厦",
                openId = 2431829283445,
                tags = arrayOf("傻瓜","2货","逗比")
        )
        var result = friendMapService.saveOne(friendDTO)
        assert(result=="success")
    }


    @Test
    @DisplayName("修改王五数据")
    fun update(){
        var friendDTO = FriendDTO(
                id = 1,
                name = null,
                lng = 120.020356,
                lat = null,
                addr = "传化大厦112",
                openId = 2431829283446,
                tags = arrayOf("傻瓜","2货","损友")
        )
        var result = friendMapService.saveOne(friendDTO)
        assert(result=="success")
    }

    @Test
    @DisplayName("查询给定中心点，10级地图中朋友列表")
    fun findByPoiAndMapLevel(){
        var boo = friendMapService.findByPoiAndMapLevel(120.0,30.0,10)
        assert(boo.isNotEmpty())
    }

    @Test
    @DisplayName("查询王五名字的数据")
    fun findByName(){
        var boo = friendMapService.findByName("王五")
        assert(boo.isNotEmpty())
    }

}