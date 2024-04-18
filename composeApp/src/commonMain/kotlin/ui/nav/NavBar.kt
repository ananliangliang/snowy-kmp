package ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import snowy_kmp.composeapp.generated.resources.*
import ui.screen.OriginScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        stringResource(Res.string.nav_home),
        stringResource(Res.string.nav_workbench),
        stringResource(Res.string.nav_massage),
        stringResource(Res.string.nav_mine),
    )
    val filledIcons = listOf(
        Icons.Filled.Home, Icons.Filled.Apps, Icons.AutoMirrored.Filled.Chat, Icons.Filled.Person
    )
    val outlinedIcons = listOf(
        Icons.Outlined.Home, Icons.Outlined.Apps, Icons.AutoMirrored.Outlined.Chat, Icons.Outlined.Person
    )
    val screens = listOf(OriginScreen, OriginScreen, OriginScreen, OriginScreen)
    val navigation = LocalNavigator.currentOrThrow



    NavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                icon = {
                    Icon(if (isSelected) filledIcons[index] else outlinedIcons[index], contentDescription = item)
                },
                label = { Text(item) },
                selected = isSelected,
                onClick = {
                    selectedItem = index
                    navigation.push(screens[index])
                }
            )
        }
    }
}