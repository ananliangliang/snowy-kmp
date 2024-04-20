package ui.tab

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import snowy_kmp.composeapp.generated.resources.Res
import snowy_kmp.composeapp.generated.resources.compose_multiplatform
import snowy_kmp.composeapp.generated.resources.nav_home
import ui.nav.TabExt
import ui.nav.TabOptionsExt

object HomeTab : TabExt {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    override val optionsExt: TabOptionsExt
        @Composable get() {
            val title = stringResource(Res.string.nav_home)
            val icon = rememberVectorPainter(Icons.Outlined.Home)
            val selectedIcon = rememberVectorPainter(Icons.Filled.Home)

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
