package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = KidSkyBlueDark,
    onPrimary = Color.White,
    primaryContainer = KidSkyBlueLight,
    onPrimaryContainer = KidSkyBlueDark,

    secondary = KidGrassGreenDark,
    onSecondary = Color.White,
    secondaryContainer = KidGrassGreenLight,
    onSecondaryContainer = KidGrassGreenDark,

    tertiary = KidCoralOrangeDark,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFEBE5),
    onTertiaryContainer = KidCoralOrangeDark,

    background = KidCreamBackground,
    onBackground = KidTextPrimary,

    surface = KidWarmCardSurface,
    onSurface = KidTextPrimary,
    surfaceVariant = Color(0xFFFFF3E0),
    onSurfaceVariant = KidTextSecondary
)

private val KidShapes = Shapes(
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(24.dp),
    large = RoundedCornerShape(32.dp),
    extraLarge = RoundedCornerShape(40.dp)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = KidShapes,
        content = content
    )
}
