package com.compose.hero_interactors

import com.compose.core.DataState
import com.compose.core.ProgressBarState
import com.compose.core.UIComponent
import com.compose.hero_datasource.cache.HeroCache
import com.compose.hero_datasource.network.HeroServices
import com.compose.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

class GetHeros(
    private val cache: HeroCache,
    private val service: HeroServices
) {

    fun execute(): Flow<DataState<List<Hero>>> = flow {

        try {
            emit(DataState.Loading<List<Hero>>(progressBarState = ProgressBarState.Loading))

            val heros: List<Hero> = try {
                service.getHeroStats()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response<List<Hero>>(
                        uiComponent = UIComponent.Dialog(
                            title = "Error",
                            description = e.message ?: "Unknown Error"
                        )
                    )
                )

                listOf()
            }

            // cache the network data
            cache.insert(heros)

            // Emit data from cache (Single source of truth)
            val cachedHeros = cache.selectAll()

            emit(DataState.Data(data = cachedHeros))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Hero>>(
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