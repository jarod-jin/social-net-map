package cn.jarod.socialnetmap.entity

import javax.persistence.*

@Entity
@Table(name = "tag")
data class Tag (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?,
                var friendId: Long?,
                var name: String?){
    constructor() : this(null,null,null) {
    }
}