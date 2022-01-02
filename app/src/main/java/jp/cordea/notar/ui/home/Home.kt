package jp.cordea.notar.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun Home(viewModel: HomeViewModel) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Notar") })
    }) {
    }
}
