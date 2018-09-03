package cn.jarod.socialnetmap.entity

import javax.persistence.*

@Entity
@Table(name = "tag_link")
data class TagLink (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?,
                    var friendId: Long?,
                    var tagId: Long?) {
    constructor() : this(null,null, null) {
    }
}