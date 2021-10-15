package com.compose.ui_hero_list.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.core.DataState
import com.compose.core.Logger
import com.compose.core.UIComponent
import com.compose.hero_interactors.GetHeros
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel
@Inject
constructor(
    private val getHeros: GetHeros,
) : ViewModel() {

    private val logger = Logger("HeroListViewModel")
    val state: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        getHeros()
    }

    private fun getHeros() {
        getHeros.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }

                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                    }
                }

                is DataState.Data -> {
                    state.value = state.value.copy(heros = dataState.data ?: listOf())
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}