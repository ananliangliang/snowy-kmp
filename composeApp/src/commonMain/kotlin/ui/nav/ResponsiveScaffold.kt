package ui.nav

import Screen
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ResponsiveScaffold(
    navController: NavController,
    tabs: List<Screen.Tab>,
    snackbarHostState: SnackbarHostState,
    content: @Composable () -> Unit
) {
    val sizeClass = calculateWindowSizeClass().widthSizeClass
    val showNav = navController.getCurrentRoute().startsWith("/tab")
    Scaffold(
        bottomBar = { if (sizeClass == Compact && showNav) TabNavigationBar(navController, tabs) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        if (sizeClass == Compact) content()
        if (sizeClass == Medium && showNav)
            Row {
                TabNavigationRail(navController, tabs)
                content()
            }
        else if (sizeClass == Medium) content()
        if (sizeClass == Expanded && showNav) TabNavigationDrawer(navController, tabs) { content() }
        else if (sizeClass == Expanded) content()
    }
}


@Composable
private fun NavController.getCurrentRoute(): String {
    val backStackEntry by this.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route ?: ""
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TabNavigationBar(navController: NavController, tabs: List<Screen.Tab>) {
    val currentRoute = navController.getCurrentRoute()
    NavigationBar {
        tabs.forEach {
            val selected = currentRoute == it.route
            NavigationBarItem(
                selected,
                { navController.navigate(it.route) },
                { Icon(if (selected) it.selectedIcon else it.icon, stringResource(it.title)) },
                label = { Text(stringResource(it.title)) }
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TabNavigationRail(navController: NavController, tabs: List<Screen.Tab>) {
    val currentRoute = navController.getCurrentRoute()
    NavigationRail {
        tabs.forEach {
            val selected = currentRoute == it.route
            NavigationRailItem(
                selected,
                { navController.navigate(it.route) },
                { Icon(if (selected) it.selectedIcon else it.icon, stringResource(it.title)) },
                label = { Text(stringResource(it.title)) }
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TabNavigationDrawer(
    navController: NavController,
    tabs: List<Screen.Tab>,
    content: @Composable () -> Unit
) {
    val currentRoute = navController.getCurrentRoute()
    PermanentNavigationDrawer(
        {
            PermanentDrawerSheet {
                tabs.forEach { tab ->
                    val selected = currentRoute == tab.route
                    NavigationDrawerItem(
                        { Text(stringResource(tab.title)) },
                        selected,
                        { navController.navigate(tab.route) },
                        icon = { Icon(if (selected) tab.selectedIcon else tab.icon, stringResource(tab.title)) },
                    )
                }
            }
        },
    ) { content() }
}

