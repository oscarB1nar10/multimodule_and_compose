package com.compose.multimodulecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.compose.multimodulecompose.ui.theme.DotaInfoTheme
import com.compose.ui_hero_list.ui.HeroList
import com.compose.ui_hero_list.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageLoader = ImageLoader.Builder(applicationContext)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .availableMemoryPercentage(.25)
            .crossfade(true)
            .build()


        setContent {
            DotaInfoTheme {
                val viewModel: HeroListViewModel = hiltViewModel()
                HeroList(
                    state = viewModel.state.value,
                    imageLoader = imageLoader
                )
            }

        }
    }
}