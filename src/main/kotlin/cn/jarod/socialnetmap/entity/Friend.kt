package cn.jarod.socialnetmap.entity
import javax.persistence.*

@Entity
@Table(name = "friend")
data class Friend (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?,
                   var name: String?,
                   var lng: Double?,
                   var lat: Double?,
                   var addr: String?,
                   var openId: Long?) {
    constructor() : this(null,null,null,null,null, null) {
    }
}