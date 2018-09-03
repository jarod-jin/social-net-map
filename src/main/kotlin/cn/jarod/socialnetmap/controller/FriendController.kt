package cn.jarod.socialnetmap.controller

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.service.IFriendMapService
import cn.jarod.socialnetmap.service.impl.FriendMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/friend")
class FriendController{

    @Autowired
    lateinit var friendMapService: IFriendMapService

    @RequestMapping("findByName")
    @ResponseBody
    fun finByName(name:String):List<Friend>{
        return friendMapService.findByName(name);
    }

    @RequestMapping("add")
    @ResponseBody
    fun add(name:String,lon:Double,lat:Double,openid:Long,tags:Array<String>):String{
        friendMapService.add(name,lon,lat,openid,tags)
        return "success"
    }
}