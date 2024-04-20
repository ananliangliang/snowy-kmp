package ui.nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.tab.HomeTab
import ui.tab.MessageTab
import ui.tab.MineTab
import ui.tab.WorkbenchTab

@Composable
fun NavBar(onLogout: () -> Unit) {
    NavigationBar {
        TabNavigationItem(HomeTab)
        TabNavigationItem(WorkbenchTab)
        TabNavigationItem(MessageTab)
        TabNavigationItem(MineTab(onLogout))
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
@Preview
fun NavBarPreview() {
    NavBar { }
}