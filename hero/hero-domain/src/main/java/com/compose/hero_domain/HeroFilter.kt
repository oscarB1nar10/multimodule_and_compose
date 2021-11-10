package com.compose.hero_domain

import com.compose.core.FilterOrder

sealed class HeroFilter(val uiValue: String) {

    data class Hero(
        val order: FilterOrder = FilterOrder.Descending
    ) : HeroFilter("Hero")

    data class ProWins(
        val order: FilterOrder = FilterOrder.Descending
    ) : HeroFilter("Pro win-rate")
}
