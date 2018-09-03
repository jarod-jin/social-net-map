package cn.jarod.socialnetmap.entity

import javax.persistence.*

@Entity
@Table(name = "tag")
data class Tag (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?,
                var tagDisplay: String?){
    constructor() : this(null,null) {
    }
}