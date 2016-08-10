package me.ilies.service

import org.junit.Assert.assertEquals
import org.junit.Test


/**********************************************************************************************************************
 * Created by viplord on 8/7/2016.
 *
 **********************************************************************************************************************/
class DefaultKeyMapperServiceTest {
    val service: KeyMapperService =  DefaultKeyMapperService()

    private val  LINK_NEW: String = "http://www.wow.ru"
    private val  KEY: String = "asdsa"
    private val  LINK: String = "http://www.google.com"

    @Test
    fun clientCanAddNewKeyWithLink() {
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotAddExistingKey() {
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }
    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY),service.getLink(KEY))
    }

}
