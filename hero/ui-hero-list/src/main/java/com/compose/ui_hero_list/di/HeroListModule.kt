package com.compose.ui_hero_list.di

import com.compose.hero_interactors.GetHeros
import com.compose.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {

    @Provides
    @Singleton
    fun provideGetHeros(
        interactors: HeroInteractors
    ): GetHeros{
        return  interactors.getHeros
    }
}