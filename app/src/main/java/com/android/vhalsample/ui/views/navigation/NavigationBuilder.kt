package com.android.vhalsample.ui.views.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.android.vhalsample.ui.nav.NavDestinations
import com.android.vhalsample.ui.views.home.HomeView

@Composable
fun NavigationBuilder(modifier: Modifier) {
    val backStack = remember { mutableListOf(NavDestinations.HOME) }
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when(key) {
                is NavDestinations.HOME -> NavEntry(key) {
                    HomeView(modifier = modifier)
                }
                else -> NavEntry(key){}
            }
        }
    )
}