package ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.nav_message
import ui.nav.TabExt
import ui.nav.TabOptionsExt

object MessageTab : TabExt {
    @Composable
    override fun Content() {
        Text("Message")
    }

    @OptIn(ExperimentalResourceApi::class)
    override val optionsExt: TabOptionsExt
        @Composable get() {
            val title = stringResource(Res.string.nav_message)
            val icon = rememberVectorPainter(Icons.AutoMirrored.Outlined.Chat)
            val selectedIcon = rememberVectorPainter(Icons.AutoMirrored.Filled.Chat)

            return remember {
                TabOptionsExt(
                    index = 0u,
                    title = title,
                    icon = icon,
                    selectedIcon = selectedIcon
                )
            }
        }




}
