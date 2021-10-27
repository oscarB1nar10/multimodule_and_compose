package com.compose.ui_hero_detail

import com.compose.core.ProgressBarState
import com.compose.hero_domain.Hero

data class HeroDetailState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val hero: Hero? = null
)