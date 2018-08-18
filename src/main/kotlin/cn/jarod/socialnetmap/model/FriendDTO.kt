package cn.jarod.socialnetmap.model

class FriendDTO (var id:Long?,
                 var name: String?,
                 var lon: Double?,
                 var lat: Double?,
                 var tags: Array<String>) {
    constructor() : this(null,null,null,null, arrayOf()) {
    }
}