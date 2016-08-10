package me.ilies.service

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**********************************************************************************************************************
 * Created by viplord on 8/7/2016.
 *
 **********************************************************************************************************************/

class DefaultKeyMapperServiceTest {

    @InjectMocks
    val service: KeyMapperService =  DefaultKeyMapperService()

    private val  KEY: String = "asdsa"
    private val  LINK_A: String = "http://www.google.com"
    private val  LINK_B: String = "http://yahoo.com"


    @Mock
    lateinit var converter: KeyConverterService

    private val  KEY_A: String = "abc"
    private val  KEY_B: String = "cde"
    private val  ID_A: Long = 10000000L
    private val  ID_B: Long = 10000001L

    @Before
    fun init(){

        MockitoAnnotations.initMocks(this)

        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }

    @Test
    fun clientCanAddLinks(){
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY),service.getLink(KEY))
    }

}
