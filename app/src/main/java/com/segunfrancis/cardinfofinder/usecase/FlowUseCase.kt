package com.segunfrancis.cardinfofinder.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<Param, Result, CC: CoroutineContext> {

    protected abstract fun buildFlowUseCase(param: Param): Flow<Result>

    protected abstract val context: CC

    operator fun invoke(param: Param): Flow<Result> {
        return buildFlowUseCase(param).flowOn(context)
    }
}