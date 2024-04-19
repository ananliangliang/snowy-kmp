package ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.logout

data class MineScreen(val onLogout: () -> Unit) : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        Column {
            ListItem(
                { Text(stringResource(Res.string.logout)) },
                Modifier.clickable { onLogout() },
                leadingContent = { Icon(Icons.AutoMirrored.Filled.Send, null) },
                trailingContent = { Icon(Icons.Filled.ChevronRight, null) }
            )
            HorizontalDivider()
        }
    }
}