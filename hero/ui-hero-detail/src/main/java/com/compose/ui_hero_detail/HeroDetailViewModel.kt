package com.compose.ui_hero_detail

import androidx.lifecycle.ViewModel
import com.compose.hero_interactors.GetHeroFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel
@Inject
constructor(
    private val getHeroFromCache: GetHeroFromCache
) : ViewModel() {
}