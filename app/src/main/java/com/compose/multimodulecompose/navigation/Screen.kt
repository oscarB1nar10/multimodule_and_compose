package com.compose.multimodulecompose.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object HeroList : Screen(
        route = "heroList",
        arguments = emptyList()
    )

    // The arguments passed through <nav Arguments> are directly saved into
    // <SavedStateHandle> so we can retrieve those in whatever viewModel.
    object HeroDetail : Screen(
        route = "heroDetail",
        arguments = listOf(
            navArgument(name = "heroId") {
                type = NavType.IntType
            }
        )
    )
}
