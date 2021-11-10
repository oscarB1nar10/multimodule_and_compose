package com.compose.ui_hero_list.ui

sealed class HeroListEvents {

    object GetHeros : HeroListEvents()

    object FilterHeros : HeroListEvents()

    data class UpdateHeroName(
        val heroName: String
    ) : HeroListEvents()
}
