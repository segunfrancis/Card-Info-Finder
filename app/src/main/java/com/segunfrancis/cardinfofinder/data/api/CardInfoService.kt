package com.segunfrancis.cardinfofinder.data.api

import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface CardInfoService {

    @GET("{number}")
    suspend fun getCardInfo(@Path("number") number: String): CardInfoEntity
}