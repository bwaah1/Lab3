package com.example.lab_3.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Gothic-inspired colors
private val GothicBlack = Color(0xFF000000)
private val GothicGray = Color(0xFF1C1C1C)
private val BloodRed = Color(0xFF8B0000)
private val DarkPurple = Color(0xFF4B0082)
private val SilverGray = Color(0xFFB0B0B0)
private val OffWhite = Color(0xFFF5F5F5)

private val DarkColorScheme = darkColorScheme(
    primary = GothicBlack,
    onPrimary = SilverGray,
    secondary = BloodRed,
    onSecondary = OffWhite,
    tertiary = DarkPurple,
    background = GothicGray,
    surface = GothicGray,
    onBackground = OffWhite,
    onSurface = OffWhite
)

private val LightColorScheme = lightColorScheme(
    primary = GothicBlack,
    onPrimary = OffWhite,
    secondary = BloodRed,
    onSecondary = GothicBlack,
    tertiary = DarkPurple,
    background = OffWhite,
    surface = SilverGray,
    onBackground = GothicBlack,
    onSurface = GothicBlack
)

@Composable
fun TouristAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
