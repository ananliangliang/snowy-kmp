package ui.nav

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ResponsiveScaffold(
    tabs: List<TabExt>,
    snackbarHostState: SnackbarHostState,
    content: @Composable () -> Unit
) {
    val sizeClass = calculateWindowSizeClass().widthSizeClass
    Scaffold(
        bottomBar = { if (sizeClass == Compact) NavigationBar { tabs.forEach { TabNavigationItem(it) } } },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        if (sizeClass == Compact) content()
        if (sizeClass == Medium) {
            Row {
                NavigationRail { tabs.forEach { TabNavigationRailItem(it) } }
                content()
            }
        }
        if (sizeClass == Expanded) {
            PermanentNavigationDrawer(
                { PermanentDrawerSheet { tabs.forEach { TabNavigationDrawerItem(it) } } },
            ) {
                content()
            }
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: TabExt) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab

    NavigationBarItem(
        selected,
        { tabNavigator.current = tab },
        {
            Icon(
                painter = if (selected) tab.optionsExt.selectedIcon else tab.optionsExt.icon,
                contentDescription = tab.options.title
            )
        },
        label = { Text(tab.options.title) },
    )
}

@Composable
private fun TabNavigationRailItem(tab: TabExt) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab

    NavigationRailItem(
        selected,
        { tabNavigator.current = tab },
        {
            Icon(
                painter = if (selected) tab.optionsExt.selectedIcon else tab.optionsExt.icon,
                contentDescription = tab.options.title
            )
        },
        label = { Text(tab.options.title) },
    )
}

@Composable
private fun TabNavigationDrawerItem(tab: TabExt) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab

    NavigationDrawerItem(
        { Text(tab.options.title) },
        selected,
        { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = if (selected) tab.optionsExt.selectedIcon else tab.optionsExt.icon,
                contentDescription = tab.options.title
            )
        },
    )
}