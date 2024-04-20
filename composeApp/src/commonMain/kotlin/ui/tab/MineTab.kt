package ui.tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.logout
import snowy_kmp.composeapp.generated.resources.mine_change_pwd
import snowy_kmp.composeapp.generated.resources.mine_edit_info
import snowy_kmp.composeapp.generated.resources.nav_mine
import ui.nav.TabExt
import ui.nav.TabOptionsExt

data class MineTab(val onLogout: () -> Unit) : TabExt {
    @OptIn(ExperimentalResourceApi::class)
    override val optionsExt: TabOptionsExt
        @Composable get() {
            val title = stringResource(Res.string.nav_mine)
            val icon = rememberVectorPainter(Icons.Outlined.Person)
            val selectedIcon = rememberVectorPainter(Icons.Filled.Person)

            return remember {
                TabOptionsExt(
                    index = 0u,
                    title = title,
                    icon = icon,
                    selectedIcon = selectedIcon
                )
            }
        }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        Column {
            ListItem(
                { Text(stringResource(Res.string.mine_edit_info)) },
                leadingContent = { Icon(Icons.Filled.Person, null) },
                trailingContent = { Icon(Icons.Filled.ChevronRight, null) },
            )
            HorizontalDivider()
            ListItem(
                { Text(stringResource(Res.string.mine_change_pwd)) },
                leadingContent = { Icon(Icons.Filled.Lock, null) },
                trailingContent = { Icon(Icons.Filled.ChevronRight, null) },
            )
            HorizontalDivider()
            ListItem(
                { Text(stringResource(Res.string.logout)) },
                Modifier.clickable { onLogout() },
                leadingContent = { Icon(Icons.AutoMirrored.Filled.Send, null) },
                trailingContent = { Icon(Icons.Filled.ChevronRight, null) },
            )
            HorizontalDivider()
        }
    }
}

@Composable
@Preview
fun MineScreenPreview() {
    MineTab {}.Content()
}