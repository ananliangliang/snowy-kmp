package ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.nav_home
import ui.nav.TabExt
import ui.nav.TabOptionsExt

object WorkbenchTab : TabExt {
    @Composable
    override fun Content() {
        Text("Workbench")
    }

    @OptIn(ExperimentalResourceApi::class)
    override val optionsExt: TabOptionsExt
        @Composable get() {
            val title = stringResource(Res.string.nav_home)
            val icon = rememberVectorPainter(Icons.Outlined.Checklist)
            val selectedIcon = rememberVectorPainter(Icons.Filled.Checklist)

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
