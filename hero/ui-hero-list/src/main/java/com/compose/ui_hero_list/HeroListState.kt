package com.compose.ui_hero_list

import com.compose.core.ProgressBarState
import com.compose.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf()
)
