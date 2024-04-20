package ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

interface TabExt : Tab {
    val optionsExt: TabOptionsExt
        @Composable get

    override val options: TabOptions
        @Composable get() {
            val index = optionsExt.index
            val title = optionsExt.title
            val icon = optionsExt.icon

            return remember {
                TabOptions(
                    index = index,
                    title = title,
                    icon = icon
                )
            }
        }

}

data class TabOptionsExt(
    val index: UShort,
    val title: String,
    val icon: Painter,
    val selectedIcon: Painter,
)