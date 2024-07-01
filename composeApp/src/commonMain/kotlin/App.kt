import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import di.koinModule
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import snowy_kmp.composeapp.generated.resources.*
import ui.nav.ResponsiveScaffold
import ui.screen.*

@Composable
@Preview
fun App() {

    val navController: NavHostController = rememberNavController()

    KoinApplication({ modules(koinModule) }) {
        MaterialTheme(if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            Surface {
                ResponsiveScaffold(
                    navController,
                    listOf(Screen.Tab.Home, Screen.Tab.Workbench, Screen.Tab.Message, Screen.Tab.Mine),
                    snackbarHostState,
                ) {
                    NavHost(navController = navController, startDestination = Screen.Login.route) {
                        composable(Screen.Login.route) {
                            LoginScreen {
                                coroutineScope.launch { snackbarHostState.showSnackbar(it) }
                                navController.navigate(Screen.Tab.Home.route)
                            }
                        }
                        composable(Screen.Tab.Home.route) { HomeScreen() }
                        composable(Screen.Tab.Workbench.route) { WorkbenchScreen() }
                        composable(Screen.Tab.Message.route) { MessageScreen() }
                        composable(Screen.Tab.Mine.route) { MineScreen(navController) }
                    }
                }
            }
        }
    }
}


sealed class Screen(
    val route: String,
    val title: StringResource,
) {
    data object Login : Screen("/login", Res.string.login)
    sealed class Tab(
        route: String,
        title: StringResource,
        val icon: ImageVector,
        val selectedIcon: ImageVector,
    ) : Screen(route, title) {
        data object Home : Tab("/tab/home", Res.string.nav_home, Icons.Outlined.Home, Icons.Filled.Home)
        data object Workbench :
            Tab("/tab/workbench", Res.string.nav_workbench, Icons.Outlined.Checklist, Icons.Filled.Checklist)

        data object Message :
            Tab(
                "/tab/message",
                Res.string.nav_message,
                Icons.AutoMirrored.Outlined.Chat,
                Icons.AutoMirrored.Filled.Chat
            )

        data object Mine : Tab("/tab/mine", Res.string.nav_mine, Icons.Outlined.Person, Icons.Filled.Person)
    }


}