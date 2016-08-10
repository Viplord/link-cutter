package me.ilies.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**********************************************************************************************************************
 * Created by viplord on 8/7/2016.
 *
 **********************************************************************************************************************/
@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    private val map: MutableMap<Long, String> = ConcurrentHashMap()
    private val sequence = AtomicLong(1000000L)

    override fun add(link: String): String {
        val id = sequence.get();
        val key = converter.idToKey(id)
        map.put(id, link)
        return key
    }

    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        val result = map[id]
        if (result == null) {
            return KeyMapperService.Get.NotFound(key);
        } else {
            return KeyMapperService.Get.Link(result);
        }
    }
}