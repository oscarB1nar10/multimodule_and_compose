package com.compose.ui_hero_list.ui

import com.compose.core.ProgressBarState
import com.compose.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf(),
    val filteredHeros: List<Hero> = listOf(),
    val heroName: String = ""
)
