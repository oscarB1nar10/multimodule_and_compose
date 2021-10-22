package com.compose.ui_hero_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId: Int?
) {
    Text("Hero id: $heroId")
}