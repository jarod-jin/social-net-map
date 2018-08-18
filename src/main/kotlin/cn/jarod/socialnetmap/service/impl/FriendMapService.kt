package cn.jarod.socialnetmap.service.impl

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.entity.Tag
import cn.jarod.socialnetmap.repository.FriendRepository
import cn.jarod.socialnetmap.repository.TagRepository
import cn.jarod.socialnetmap.service.IFriendMapService
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.annotation.Propagation



@Service(value="friendMapService")
@Transactional(readOnly = true)
class FriendMapService :IFriendMapService {

    private val log = LoggerFactory.getLogger(FriendMapService::class.java)

    @Autowired
    private lateinit var friendRepository: FriendRepository

    @Autowired
    private lateinit var tagRepository: TagRepository

    @Transactional
    override open fun add(name:String,lon:Double,lat:Double,openid:Long,tags:Array<String>):String{
        log.info("增加FriendMap数据：Name="+name);
        val friend=Friend(
                id=null,
                name = name,
                lon = lon,
                lat = lat,
                openId = openid
        )
        friendRepository.save(friend)
        for (tagName in tags){
            val tag = Tag(
                id = null,
                name = null,
                friendId = friend.id
            )
            tagRepository.save(tag)
        }
        return "success"
    }

    override fun finByName(name:String):List<Friend>{
        return friendRepository.findByName(name);
    }
}