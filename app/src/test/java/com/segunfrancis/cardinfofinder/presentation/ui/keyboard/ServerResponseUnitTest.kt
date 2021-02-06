package com.segunfrancis.cardinfofinder.presentation.ui.keyboard

import com.segunfrancis.cardinfofinder.data.api.CardInfoService
import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepository
import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepositoryImpl
import com.segunfrancis.cardinfofinder.util.TestUtils.MOCK_WEBSERVER_POT
import com.segunfrancis.cardinfofinder.util.TestUtils.TEST_CARD_NUMBER
import com.segunfrancis.cardinfofinder.util.provideService
import com.segunfrancis.cardinfofinder.util.serverResponse
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ServerResponseUnitTest {

    private val server: MockWebServer = MockWebServer()

    lateinit var cardInfoService: CardInfoService
    lateinit var repository: CardInfoRepository

    @Before
    fun setup() {
        server.start(MOCK_WEBSERVER_POT)

        cardInfoService = provideService(server.url("/"))
        repository = CardInfoRepositoryImpl(cardInfoService)
    }

    @Test
    fun `network calls returns a response`(): Unit = runBlocking {
        server.apply {
            enqueue(MockResponse().setBody(serverResponse))
        }
        val response = repository.getCardInfo(TEST_CARD_NUMBER).first()
        assertNotNull(response)
    }

    @Test
    fun `network calls returns correct response`(): Unit = runBlocking {
        server.apply {
            enqueue(MockResponse().setBody(serverResponse))
        }
        val response = repository.getCardInfo(TEST_CARD_NUMBER).first()
        Assert.assertEquals(response.country?.name, "Denmark")
    }

    @After
    fun shutdown() {
        server.shutdown()
    }
}
