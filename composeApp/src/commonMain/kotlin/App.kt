import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.nav.NavBar
import ui.screen.LoginScreen
import ui.screen.OriginScreen

@Composable
@Preview
fun App() {
    MaterialTheme {

        var isLogin by remember { mutableStateOf(false) }

        if (isLogin)
            Navigator(OriginScreen) {
                Scaffold(
                    bottomBar = { NavBar() },
                    content = {
                        Box(Modifier.padding(it)) {
                            CurrentScreen()
                        }
                    },
                )
            }
        else
            LoginScreen { isLogin = true }


    }
}