package cn.jarod.socialnetmap.model

data class FriendDTO (var name: String?,
                 var lng: Double?,
                 var lat: Double?,
                 var addr: String?,
                 var tags: Array<String?>?) {
    constructor() : this(null,null,null,null,null) {
    }
}