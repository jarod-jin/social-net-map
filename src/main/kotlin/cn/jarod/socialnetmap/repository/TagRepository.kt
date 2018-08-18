package cn.jarod.socialnetmap.repository

import cn.jarod.socialnetmap.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface TagRepository : JpaRepository<Tag, Long> {
}