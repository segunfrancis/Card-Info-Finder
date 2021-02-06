package com.segunfrancis.cardinfofinder.util

import com.google.gson.GsonBuilder
import com.segunfrancis.cardinfofinder.data.api.CardInfoService
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serverResponse = """
    {
      "number": {
        "length": 16,
        "luhn": true
      },
      "scheme": "visa",
      "type": "debit",
      "brand": "Visa/Dankort",
      "prepaid": false,
      "country": {
        "numeric": "208",
        "alpha2": "DK",
        "name": "Denmark",
        "emoji": "🇩🇰",
        "currency": "DKK",
        "latitude": 56,
        "longitude": 10
      },
      "bank": {
        "name": "Jyske Bank",
        "url": "www.jyskebank.dk",
        "phone": "+4589893300",
        "city": "Hjørring"
      }
    }
""".trimIndent()

fun provideService(baseurl: HttpUrl): CardInfoService = Retrofit.Builder().baseUrl(baseurl)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .build()
    .create(CardInfoService::class.java)

object TestUtils {
    const val MOCK_WEBSERVER_POT = 8080
    const val TEST_CARD_NUMBER = "45717360"
    const val TEST_CARD_NUMBER_INCORRECT = "1211111"
}