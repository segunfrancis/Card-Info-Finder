package com.segunfrancis.cardinfofinder.data.repo

import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import kotlinx.coroutines.flow.Flow

interface CardInfoRepository {

    fun getCardInfo(number: String): Flow<CardInfoEntity>
}