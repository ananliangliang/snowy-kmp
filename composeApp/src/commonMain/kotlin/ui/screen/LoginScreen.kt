package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import data.network.LoginReq
import data.network.SnowyRes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import snowy_kmp.composeapp.generated.resources.*
import util.SmUtils

@OptIn(ExperimentalResourceApi::class, ExperimentalStdlibApi::class)
@Composable
fun LoginScreen(onLogin: (String) -> Unit) {
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val coroutineScope = rememberCoroutineScope()
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var username by rememberSaveable { mutableStateOf("superAdmin") }
            var password by rememberSaveable { mutableStateOf("123456") }
            val focusManager = LocalFocusManager.current

            Row(
                Modifier.height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(painterResource(Res.drawable.logo), contentDescription = null)
                Spacer(Modifier.width(16.dp))
                Text(stringResource(Res.string.app_name), style = MaterialTheme.typography.headlineMedium)
            }
            OutlinedTextField(
                username,
                { username = it },
                label = { Text(stringResource(Res.string.username)) },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                singleLine = true,
            )
            val onLoginButtonClick: () -> Unit = {
                coroutineScope.launch {
                    println("Current dispatcher: ${coroutineContext[CoroutineDispatcher]}")
                    val client = HttpClient() {
                        install(ContentNegotiation) {
                            json()
                        }
                    }
                    val body = client
                        .post("https://snowyapi.xiaonuo.vip/auth/b/doLogin") {
                            contentType(ContentType.Application.Json)
                            setBody(LoginReq(username, SmUtils.doSm2Encrypt(password)))
                        }.body<SnowyRes<String>>()
                    println(body)
                    onLogin(body.data ?: "无")
                }
            }
            OutlinedTextField(
                password,
                { password = it },
                label = { Text(stringResource(Res.string.password)) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )

            Button(onClick = onLoginButtonClick) { Text(stringResource(Res.string.login)) }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}