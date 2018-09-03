package cn.jarod.socialnetmap.repository

import cn.jarod.socialnetmap.entity.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

@Transactional
interface FriendRepository : JpaRepository<Friend, Long> {

    fun findByName(name: String): List<Friend>

    //@Query("select m from UserInfo m where m.name like %:name%")
    //fun findByArea(minlon:Double,maxlon:Double,minlat:Double,maxlat:Double): List<Friend>
}