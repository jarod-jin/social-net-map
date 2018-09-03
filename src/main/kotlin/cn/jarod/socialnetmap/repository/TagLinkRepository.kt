package cn.jarod.socialnetmap.repository

import cn.jarod.socialnetmap.entity.TagLink
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface TagLinkRepository: JpaRepository<TagLink, Long> {

}