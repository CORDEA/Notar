package jp.cordea.notar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xffb4ffff),
    primaryVariant = Color(0xff80deea),
    secondary = Color(0xff9c786c),
    secondaryVariant = Color(0xff6d4c41)
)

private val LightColorPalette = lightColors(
    primary = Color(0xff80deea),
    primaryVariant = Color(0xff4bacb8),
    secondary = Color(0xff6d4c41),
    secondaryVariant = Color(0xff40241a),
    onPrimary = Color(0xff000000),
    onSecondary = Color(0xffffffff)
)

@Composable
fun NotarTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
