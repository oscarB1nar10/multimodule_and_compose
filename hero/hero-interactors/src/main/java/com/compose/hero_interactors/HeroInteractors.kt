package com.compose.hero_interactors

import com.compose.hero_datasource.network.HeroServices

data class HeroInteractors(
    val getHeros: GetHeros,
    // TODO("Add other hero interactors")
) {

    companion object Factory {
        fun build(): HeroInteractors {
            val service = HeroServices.build()

            return HeroInteractors(
                getHeros = GetHeros(service = service)
            )
        }
    }
}
