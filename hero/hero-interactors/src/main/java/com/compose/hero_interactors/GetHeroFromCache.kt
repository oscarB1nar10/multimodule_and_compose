package com.compose.hero_interactors

import com.compose.core.DataState
import com.compose.core.ProgressBarState
import com.compose.core.UIComponent
import com.compose.hero_datasource.cache.HeroCache
import com.compose.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroFromCache(
    private val cache: HeroCache
) {

    fun execute(
        heroId: Int
    ): Flow<DataState<Hero>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            // Emit data from cache (Single source of truth)
            val cachedHero =
                cache.getHero(heroId) ?: throw Exception("That hero doesn't exist in the cache.")

            emit(DataState.Data(data = cachedHero))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}