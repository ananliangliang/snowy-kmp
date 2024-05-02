package ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.logout
import snowy_kmp.composeapp.generated.resources.mine_change_pwd
import snowy_kmp.composeapp.generated.resources.mine_edit_info

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MineScreen(navController: NavController) {


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
            Modifier.clickable { navController.navigate(Screen.Login.route) },
            leadingContent = { Icon(Icons.AutoMirrored.Filled.Send, null) },
            trailingContent = { Icon(Icons.Filled.ChevronRight, null) },
        )
        HorizontalDivider()

    }
}

@Composable
@Preview
fun MineScreenPreview() {
}