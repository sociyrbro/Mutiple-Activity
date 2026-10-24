package cn.edu.sicnu.cs.stu.guolijie.mutopleactivity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Teal80,
    background = Color(0xFF111418),
    surface = Color(0xFF171B20),
    surfaceVariant = Color(0xFF29323C),
    outline = Color(0xFF8B99A9),
    onSurface = Color(0xFFE5EAF0),
    onSurfaceVariant = Color(0xFFC3CCD7)
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Teal40,
    background = AppBackground,
    surface = AppSurface,
    surfaceVariant = AppSurfaceVariant,
    outline = AppOutline
)

@Composable
fun MutopleActivityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
