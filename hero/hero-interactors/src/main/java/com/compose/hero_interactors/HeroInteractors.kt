package com.compose.hero_interactors

import com.compose.hero_datasource.cache.HeroCache
import com.compose.hero_datasource.network.HeroServices
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeros: GetHeros,
    // TODO("Add other hero interactors")
) {

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = HeroServices.build()
            val cache = HeroCache.build(sqlDriver = sqlDriver)

            return HeroInteractors(
                getHeros = GetHeros(service = service, cache = cache)
            )
        }
    }
}
