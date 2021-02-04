package com.segunfrancis.cardinfofinder.data.repo

import com.segunfrancis.cardinfofinder.data.api.CardInfoService
import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor(private val service: CardInfoService) :
    CardInfoRepository {

    override fun getCardInfo(number: String): Flow<CardInfoEntity> {
        return flow {
            emit(service.getCardInfo(number))
        }
    }
}