package jp.cordea.notar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.notar.ui.NotarApp
import jp.cordea.notar.ui.theme.NotarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotarTheme {
                NotarApp()
            }
        }
    }
}
