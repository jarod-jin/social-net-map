package cn.jarod.socialnetmap.service

import cn.jarod.socialnetmap.entity.Friend

interface IFriendMapService {

    fun add(name:String,lon:Double,lat:Double,openid:Long,tags:Array<String>):String

    fun finByName(name:String):List<Friend>

}