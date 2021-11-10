package com.compose.core

sealed class FilterOrder {
    object Ascending : FilterOrder()
    object Descending : FilterOrder()
}
