package com.compose.ui_hero_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.compose.core.ProgressBarState
import com.compose.ui_hero_list.components.HeroListItem


@Composable
fun HeroList(state: HeroListState) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.heros) { hero ->
                HeroListItem(
                    hero = hero,
                    onSelectHero = {}
                )
            }
        }

        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}