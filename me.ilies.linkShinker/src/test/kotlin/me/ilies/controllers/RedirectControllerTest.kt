package me.ilies.controllers

import me.ilies.Controllers.RedirectController
import me.ilies.LinkShinkerApplication
import me.ilies.service.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**********************************************************************************************************************
 * Created by viplord on 8/6/2016.
 *
 **********************************************************************************************************************/
@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@SpringBootTest(classes = arrayOf(LinkShinkerApplication::class))
class RedirectControllerTest {

    private val HEADEAR_NAME = "Location"
    private val HEADER_VAL = "http://www.google.com"
    private val REDIRECT_STATUS: Int = 302
    private val PATH = "asasddsa"
    private val BAD_PATH = "/SAdsad"
    private val NOT_FOUND: Int = 404

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    lateinit var mocMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mocMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()

        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VAL))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))
    }

    @Test fun controllerMustRedirectUsWhenRequestIsSuccesfull() {
        mocMvc.perform(get("/$PATH"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADEAR_NAME, HEADER_VAL))
    }

    @Test fun controllerMustReturn404IfBadKey() {
        mocMvc.perform(get("/$BAD_PATH"))
                .andExpect(status().`is`(NOT_FOUND))
    }
}