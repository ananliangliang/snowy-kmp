import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import di.koinModule
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.nav.NavBar
import ui.tab.HomeTab
import ui.screen.LoginScreen

@Composable
@Preview
fun App() {
    KoinApplication({ modules(koinModule) }) {
        MaterialTheme {
            var isLogin by remember { mutableStateOf(false) }
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            if (isLogin)
                TabNavigator(HomeTab) {
                    Scaffold(
                        bottomBar = { NavBar { isLogin = false } },
                        content = { Box(Modifier.padding(it)) { CurrentTab() } },
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    )
                }
            else
                LoginScreen {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(it)
                    }
                    isLogin = true
                }
        }
    }
}