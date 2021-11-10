package com.compose.ui_hero_list.ui

import com.compose.core.ProgressBarState
import com.compose.hero_domain.Hero
import com.compose.hero_domain.HeroAttribute
import com.compose.hero_domain.HeroFilter

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf(),
    val filteredHeros: List<Hero> = listOf(),
    val heroName: String = "",
    val heroFilter: HeroFilter = HeroFilter.Hero(),
    val primaryAttribute: HeroAttribute = HeroAttribute.Unknown
)
