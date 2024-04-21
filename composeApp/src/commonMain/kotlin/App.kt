import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import di.koinModule
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.nav.ResponsiveScaffold
import ui.screen.LoginScreen
import ui.tab.HomeTab
import ui.tab.MessageTab
import ui.tab.MineTab
import ui.tab.WorkbenchTab

@Composable
@Preview
fun App() {
    KoinApplication({ modules(koinModule) }) {
        MaterialTheme(if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
            var isLogin by remember { mutableStateOf(false) }
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            if (isLogin)
                TabNavigator(HomeTab) {
                    ResponsiveScaffold(
                        listOf(HomeTab, WorkbenchTab, MessageTab, MineTab { isLogin = false }),
                        snackbarHostState
                    ) {
                        CurrentTab()
                    }
                }
            else
                Surface {
                    LoginScreen {
                        coroutineScope.launch { snackbarHostState.showSnackbar(it) }
                        isLogin = true
                    }
                }
        }
    }
}