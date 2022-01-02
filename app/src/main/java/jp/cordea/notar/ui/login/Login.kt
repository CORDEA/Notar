package jp.cordea.notar.ui.login

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun Login() {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Login") })
    }) {
    }
}
