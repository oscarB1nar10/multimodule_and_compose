package com.compose.hero_interactors

import com.compose.hero_datasource.cache.HeroCache
import com.compose.hero_datasource.cache.HeroDatabase
import com.compose.hero_datasource.network.HeroServices
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeros: GetHeros,
    val getHeroFromCache: GetHeroFromCache
    // TODO("Add other hero interactors")
) {

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = HeroServices.build()
            val cache = HeroCache.build(sqlDriver = sqlDriver)

            return HeroInteractors(
                getHeros = GetHeros(service = service, cache = cache),
                getHeroFromCache = GetHeroFromCache(cache = cache)
            )
        }

        val schema: SqlDriver.Schema = HeroDatabase.Schema

        const val dbName: String = "heros.db"
    }
}
