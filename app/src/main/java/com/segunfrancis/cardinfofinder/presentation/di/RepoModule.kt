package com.segunfrancis.cardinfofinder.presentation.di

import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepository
import com.segunfrancis.cardinfofinder.data.repo.CardInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepoModule {

    @Binds
    fun cardInfoRepository(cardInfoRepositoryImpl: CardInfoRepositoryImpl): CardInfoRepository
}