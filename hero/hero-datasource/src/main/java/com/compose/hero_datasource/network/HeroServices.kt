package com.compose.hero_datasource.network

import com.compose.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface HeroServices {
    suspend fun getHeroStats(): List<Hero>

    companion object Factory {
        fun build(): HeroServices {
            return HeroServiceImpl(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys =
                                    true // If the service returns extra fields ignore them.
                            }
                        )
                    }
                }
            )
        }
    }
}