package cn.jarod.socialnetmap.repository

import cn.jarod.socialnetmap.entity.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional
interface FriendRepository : JpaRepository<Friend, Long> {

    @Query(value = "select m from Friend m where m.name = :name")
    fun findByName(@Param("name")name: String): List<Friend>

    @Query(value = "select m from Friend m where m.lng > :minlng and m.lat > :minlat and m.lng < :maxlng and m.lat < :maxlat")
    fun findByVertex(@Param("minlng") minlng:Double,
                     @Param("maxlng") maxlng:Double,
                     @Param("minlat") minlat:Double,
                     @Param("maxlat") maxlat:Double): List<Friend>
}