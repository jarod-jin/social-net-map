package cn.jarod.socialnetmap.model

import java.util.*

data class FriendDTO (var id: Long?,
                 var name: String?,
                 var lng: Double?,
                 var lat: Double?,
                 var addr: String?,
                 var openId : Long?,
                 var tags: Array<String?>?) {
    constructor() : this(null,null,null,null,null,null,null) {
    }
}