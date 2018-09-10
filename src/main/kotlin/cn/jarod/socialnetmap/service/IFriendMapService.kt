package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.model.FriendDTO

interface IFriendMapService {

    fun findByName(name:String):List<Friend>

    fun findByPoiAndMapLevel(lon: Double, lat: Double, level: Int):List<FriendDTO>

    fun saveOne(friendDTO: FriendDTO): String

}