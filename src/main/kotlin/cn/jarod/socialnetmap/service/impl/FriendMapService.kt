package cn.jarod.socialnetmap.service.impl

import cn.jarod.socialnetmap.entity.Friend
import cn.jarod.socialnetmap.entity.TagLink
import cn.jarod.socialnetmap.entity.Tag
import cn.jarod.socialnetmap.model.FriendDTO
import cn.jarod.socialnetmap.repository.FriendRepository
import cn.jarod.socialnetmap.repository.TagLinkRepository
import cn.jarod.socialnetmap.repository.TagRepository
import cn.jarod.socialnetmap.service.IFriendMapService
import cn.jarod.socialnetmap.utils.CalulateLatLng
import cn.jarod.socialnetmap.utils.Maplevel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional




@Service(value="friendMapService")
@Transactional(readOnly = true)
open class FriendMapService :IFriendMapService {

    private val log = LoggerFactory.getLogger(FriendMapService::class.java)

    @Autowired
    private lateinit var friendRepository: FriendRepository

    @Autowired
    private lateinit var tagRepository: TagRepository

    @Autowired
    private lateinit var tagLinkRepository: TagLinkRepository

    override fun findByName(name:String):List<Friend>{
        return friendRepository.findByName(name);
    }

    override fun findByPoiAndMapLevel(lng: Double, lat: Double, level: Int): List<FriendDTO> {
        var mapLevel = Maplevel.getScaleFromlevel(level)
        var maxlng = lng + CalulateLatLng.getDeltaLngByDistance(lat,mapLevel,2.0)
        var minlng = lng - CalulateLatLng.getDeltaLngByDistance(lat,mapLevel,2.0)
        var maxlat = lat + CalulateLatLng.getDeltaLatByDistance(mapLevel,3.0)
        var minlat = lat - CalulateLatLng.getDeltaLatByDistance(mapLevel,3.0)
        var list = friendRepository.findByVertex(minlng,maxlng,minlat,maxlat)
        val listReturn = mutableListOf<FriendDTO>()
        for (f in list){
            val taglink =  TagLink(
                    id = null,
                    friendId = f.id,
                    tagId = null
            )
            var taglinks = tagLinkRepository.findAll(Example.of(taglink))
            var tags = arrayListOf<String?>()
            for ( t in taglinks){
                var tag = tagRepository.findById(t.id?:0)
                if (!tag.isPresent())
                    tags.add(tag.get().tagDisplay)
            }
            val friendDTO =  FriendDTO(
                    id = f.id,
                    name = f.name,
                    lng = f.lng,
                    lat = f.lat,
                    addr = f.addr,
                    openId = f.openId,
                    tags = tags.toTypedArray()
            )
            listReturn.add(friendDTO)
        }
        return listReturn;
    }

    @Transactional(rollbackFor = arrayOf(Exception::class))
    override fun saveOne(friendDTO: FriendDTO):String{
        log.info("增加FriendMap数据：Name="+friendDTO.name)
        var f = friendRepository.findById(friendDTO.id?:0)
        val friend=Friend(
            id = if (f.isPresent())  friendDTO.id else null,
            name = getValueNotNull(friendDTO.name,f.get().name,f.isPresent()),
            lng = getValueNotNull(friendDTO.lng,f.get().lng,f.isPresent()),
            lat = getValueNotNull(friendDTO.lat,f.get().lat,f.isPresent()),
            addr = getValueNotNull(friendDTO.addr,f.get().addr,f.isPresent()),
            openId = getValueNotNull(friendDTO.openId,f.get().openId,f.isPresent())
        )
        friendRepository.save(friend)
        for (tagName in friendDTO.tags.orEmpty()){
            var tag = Tag(
                id = null,
                tagDisplay = tagName
            )
            var tmp = tagRepository.findOne(Example.of(tag))
            if (!tmp.isPresent())
                tagRepository.save(tag)
            else
                tag = tmp.get()
            val friendTag = TagLink(
                id = null,
                friendId = friend.id,
                tagId = tag.id
            )
            var tk = tagLinkRepository.findOne(Example.of(friendTag))
            if (!tk.isPresent())
                tagLinkRepository.save(friendTag)
        }
        return "success"
    }

    fun <T> getValueNotNull(value1: T?, value2: T?,boo: Boolean): T? {
        if (boo) return value1?:value2 else return value2
        //if (value1==null && boo) return value2 else return value1
    }
}

