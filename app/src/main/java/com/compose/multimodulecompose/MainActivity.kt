package com.compose.multimodulecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.compose.core.DataState
import com.compose.core.Logger
import com.compose.core.ProgressBarState
import com.compose.core.UIComponent
import com.compose.hero_interactors.HeroInteractors
import com.compose.multimodulecompose.ui.theme.DotaInfoTheme
import com.compose.ui_hero_list.HeroList
import com.compose.ui_hero_list.HeroListState
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainActivity : ComponentActivity() {

    private val state: MutableState<HeroListState> = mutableStateOf(HeroListState())
    private val progressBarState: MutableState<ProgressBarState> =
        mutableStateOf(ProgressBarState.Idle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getHeros = HeroInteractors.build(
            sqlDriver = AndroidSqliteDriver(
                schema = HeroInteractors.schema,
                context = this,
                name = HeroInteractors.dbName
            )
        ).getHeros
        val logger = Logger("GetHerosTest")
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
                    progressBarState.value = dataState.progressBarState
                }
            }
        }.launchIn(CoroutineScope(IO))

        setContent {
            DotaInfoTheme {
                HeroList(state = state.value)
            }

        }
    }
}