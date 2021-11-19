package com.compose.hero_interactors

import com.compose.core.FilterOrder
import com.compose.hero_domain.Hero
import com.compose.hero_domain.HeroAttribute
import com.compose.hero_domain.HeroFilter
import kotlin.math.round

class FilterHeros {

    fun execute(
        current: List<Hero>,
        heroName: String,
        heroFilter: HeroFilter,
        attributeFilter: HeroAttribute
    ): List<Hero> {

        var filteredList: MutableList<Hero> = current.filter {
            it.localizedName.lowercase().contains(heroName.lowercase())
        }.toMutableList()

        when (heroFilter) {
            is HeroFilter.Hero -> {
                when (heroFilter.order) {
                    is FilterOrder.Descending -> {
                        filteredList.sortByDescending { it.localizedName }
                    }

                    is FilterOrder.Ascending -> {
                        filteredList.sortBy { it.localizedName }
                    }
                }
            }
            is HeroFilter.ProWins -> {
                when (heroFilter.order) {
                    is FilterOrder.Descending -> {
                        filteredList.sortByDescending {
                            getWinRate(
                                proPick = it.proPick.toDouble(),
                                proWins = it.proWins.toDouble()
                            )
                        }
                    }

                    is FilterOrder.Ascending -> {
                        filteredList.sortBy {
                            getWinRate(
                                proPick = it.proPick.toDouble(),
                                proWins = it.proWins.toDouble()
                            )
                        }
                    }
                }
            }
        }

        when (attributeFilter) {
            is HeroAttribute.Strength -> {
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Strength }
                    .toMutableList()
            }
            is HeroAttribute.Agility -> {
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Agility }
                    .toMutableList()
            }
            is HeroAttribute.Intelligence -> {
                filteredList =
                    filteredList.filter { it.primaryAttribute is HeroAttribute.Intelligence }
                        .toMutableList()
            }
            is HeroAttribute.Unknown -> {
                // do not filter
            }
        }

        return filteredList
    }

    private fun getWinRate(proPick: Double, proWins: Double): Int {
        return if (proPick <= 0) {
            0
        } else {
            val winRate: Int =
                round(proWins / proPick * 100).toInt()
            winRate
        }
    }
}
