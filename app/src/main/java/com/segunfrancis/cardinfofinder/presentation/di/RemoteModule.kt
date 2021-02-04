package com.segunfrancis.cardinfofinder.presentation.di

import com.google.gson.GsonBuilder
import com.segunfrancis.cardinfofinder.data.api.CardInfoService
import com.segunfrancis.cardinfofinder.presentation.util.AppConstants.BASE_URL
import com.segunfrancis.cardinfofinder.presentation.util.AppConstants.TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideApiService(): CardInfoService {
        val level = HttpLoggingInterceptor.Level.BODY

        val interceptor = HttpLoggingInterceptor().setLevel(level)

        val gson = GsonBuilder().setLenient().create()

        val gsonConverter = GsonConverterFactory.create(gson)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()

        return retrofit.create(CardInfoService::class.java)
    }

    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}