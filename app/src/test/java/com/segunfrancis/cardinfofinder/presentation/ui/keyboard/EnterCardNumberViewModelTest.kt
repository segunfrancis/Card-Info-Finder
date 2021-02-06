package com.segunfrancis.cardinfofinder.presentation.ui.keyboard

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.segunfrancis.cardinfofinder.data.api.CardInfoService
import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepository
import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepositoryImpl
import com.segunfrancis.cardinfofinder.presentation.util.Result
import com.segunfrancis.cardinfofinder.usecase.GetCardInfoUseCase
import com.segunfrancis.cardinfofinder.util.TestUtils.MOCK_WEBSERVER_POT
import com.segunfrancis.cardinfofinder.util.TestUtils.TEST_CARD_NUMBER
import com.segunfrancis.cardinfofinder.util.getOrAwaitValue
import com.segunfrancis.cardinfofinder.util.provideService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.nullValue
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class EnterCardNumberViewModelTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val server: MockWebServer = MockWebServer()
    private lateinit var viewModel: EnterCardNumberViewModel
    private lateinit var repository: CardInfoRepository
    private lateinit var service: CardInfoService
    private lateinit var useCase: GetCardInfoUseCase

    @Before
    fun setUp() {
        server.start(MOCK_WEBSERVER_POT)
        service = provideService(server.url("/"))
        repository = CardInfoRepositoryImpl(service)

        useCase = GetCardInfoUseCase(repository, Dispatchers.Main)

        viewModel = EnterCardNumberViewModel(useCase)

        viewModel.getCardInfo(TEST_CARD_NUMBER)
    }

    @Test
    fun `test getCardInfo method returns a response when successful`() {
        val response = viewModel.cardResponse.getOrAwaitValue()

        if (response is Result.Success)
            assertEquals(response.data, not(nullValue()))
    }

    @Test
    fun `test getCardInfo method returns the correct response when successful`() {
        val response = viewModel.cardResponse.getOrAwaitValue()

        if (response is Result.Success)
            assertEquals(response.data.country?.name, "Denmark")
    }
}