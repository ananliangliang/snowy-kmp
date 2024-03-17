package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import snowy_kmp.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var username by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

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
            OutlinedTextField(
                password,
                { password = it },
                label = { Text(stringResource(Res.string.password)) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )
            Button(onClick = {
                
                onLogin()
            }) {
                Text(stringResource(Res.string.login))
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}