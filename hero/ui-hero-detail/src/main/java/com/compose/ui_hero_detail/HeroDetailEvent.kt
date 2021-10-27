package com.compose.ui_hero_detail

sealed class HeroDetailEvent {

    data class GetHeroFromCache(
        val id: Int
    ) : HeroDetailEvent()
}
