package cn.jarod.socialnetmap.service.impl

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.entity.TagLink
import cn.jarod.socialnetmap.entity.Tag
import cn.jarod.socialnetmap.model.FriendDTO
import cn.jarod.socialnetmap.repository.FriendRepository
import cn.jarod.socialnetmap.repository.TagLinkRepository
import cn.jarod.socialnetmap.repository.TagRepository
import cn.jarod.socialnetmap.service.IFriendMapService
import cn.jarod.socialnetmap.utils.Maplevel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional




@Service(value="friendMapService")
@Transactional(readOnly = true)
class FriendMapService :IFriendMapService {

    private val log = LoggerFactory.getLogger(FriendMapService::class.java)

    @Autowired
    private lateinit var friendRepository: FriendRepository

    @Autowired
    private lateinit var tagRepository: TagRepository

    @Autowired
    private lateinit var tagLinkRepository: TagLinkRepository

    @Transactional(rollbackFor = arrayOf(Exception::class))
    override fun add(name:String, lng:Double, lat:Double, openid:Long, tags:Array<String>):String{
        log.info("增加FriendMap数据：Name="+name);
        val friend=Friend(
                id=null,
                name = name,
                lng = lng,
                lat = lat,
                openId = openid
        )
        friendRepository.save(friend)
        for (tagName in tags){
            val tag = Tag(
                id = null,
                tagDisplay = tagName
            )
            tagRepository.findOne(Example.of(tag))
            if (tag.id==null)
                tagRepository.save(tag)
            val friendTag = TagLink(
                id = null,
                friendId = friend.id,
                tagId = tag.id
            )
            tagLinkRepository.save(friendTag)
        }
        return "success"
    }

    override fun findByName(name:String):List<Friend>{
        return friendRepository.findByName(name);
    }

    override fun findByPoiAndMapLevel(lon: Double, lat: Double, level: Int): List<FriendDTO>? {
        var scale = Maplevel.getScaleFromlevel(level)
        var maxlon = lon+scale*2
        var minlon = lon-scale*2
        var maxlat = lat+scale*3
        var minlat = lat-scale*3
        //friendRepository.findByArea(minlon,maxlon,minlat,maxlat)
        return null;
    }
}