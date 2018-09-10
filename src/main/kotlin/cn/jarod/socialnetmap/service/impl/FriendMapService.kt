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
                var tag = tagRepository.findById(t.tagId).get()
                tags.add(tag.tagDisplay)
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
        var f =friendRepository.findById(if (friendDTO.id ==null) 0 else friendDTO.id)
        val friend=Friend(
            id = if (f.isPresent())  friendDTO.id else null,
            name = if (friendDTO.name==null && f.isPresent()) f.get().name else friendDTO.name,
            lng = if (friendDTO.lng==null && f.isPresent()) f.get().lng else friendDTO.lng,
            lat = if (friendDTO.lat==null && f.isPresent()) f.get().lat else friendDTO.lat,
            addr = if (friendDTO.addr==null && f.isPresent()) f.get().addr else friendDTO.addr,
            openId = if (friendDTO.openId==null && f.isPresent()) f.get().openId else friendDTO.openId
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
}