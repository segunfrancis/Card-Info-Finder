package com.segunfrancis.cardinfofinder.usecase

import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepository
import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetCardInfoUseCase @Inject constructor(
    private val repository: CardInfoRepository,
    private val coroutineContext: CoroutineContext
) : FlowUseCase<String, CardInfoEntity, CoroutineContext>() {

    override fun buildFlowUseCase(param: String): Flow<CardInfoEntity> {
        return repository.getCardInfo(param)
    }

    override val context: CoroutineContext
        get() = coroutineContext
}