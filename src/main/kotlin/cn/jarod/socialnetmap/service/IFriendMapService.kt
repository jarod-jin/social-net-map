package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.model.FriendDTO

interface IFriendMapService {

    fun add(name:String,lon:Double,lat:Double,openid:Long,tags:Array<String>):String

    fun findByName(name:String):List<Friend>

    fun findByPoiAndMapLevel(lon: Double, lat: Double, level: Int):List<FriendDTO>?

}