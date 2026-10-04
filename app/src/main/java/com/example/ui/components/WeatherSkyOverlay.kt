package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.model.TownWeather

@Composable
fun WeatherSkyOverlay(
    weather: TownWeather,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "weather_anim")

    // Cloud drift
    val cloudOffset by transition.animateFloat(
        initialValue = -50f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(22000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "cloud_x"
    )

    // Rain drop descent
    val rainDropY by transition.animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rain_y"
    )

    // Sun gentle pulse
    val sunPulse by transition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sun_pulse"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        when (weather) {
            TownWeather.SUNNY -> {
                // Sun rays
                drawCircle(
                    color = Color(0x33FFEB3B),
                    radius = 90f * sunPulse,
                    center = Offset(size.width - 60f, 80f)
                )
                drawCircle(
                    color = Color(0xFFFFD54F),
                    radius = 45f * sunPulse,
                    center = Offset(size.width - 60f, 80f)
                )

                // Fluffy gentle cloud
                drawCircle(
                    color = Color(0x99FFFFFF),
                    radius = 35f,
                    center = Offset((cloudOffset) % size.width, 100f)
                )
                drawCircle(
                    color = Color(0x99FFFFFF),
                    radius = 45f,
                    center = Offset((cloudOffset + 30f) % size.width, 90f)
                )
                drawCircle(
                    color = Color(0x99FFFFFF),
                    radius = 35f,
                    center = Offset((cloudOffset + 60f) % size.width, 100f)
                )
            }
            TownWeather.RAINY -> {
                // Gentle raindrops
                val dropColor = Color(0x9964B5F6)
                for (i in 0..12) {
                    val startX = (i * 35f + 20f) % size.width
                    val currentY = (rainDropY + i * 45f) % size.height
                    drawLine(
                        color = dropColor,
                        start = Offset(startX, currentY),
                        end = Offset(startX - 6f, currentY + 18f),
                        strokeWidth = 3f
                    )
                }
            }
            TownWeather.EVENING -> {
                // Golden sun setting
                drawCircle(
                    color = Color(0x44FF7043),
                    radius = 80f,
                    center = Offset(size.width - 70f, 130f)
                )
                drawCircle(
                    color = Color(0xFFFF8A65),
                    radius = 40f,
                    center = Offset(size.width - 70f, 130f)
                )
            }
            TownWeather.NIGHT -> {
                // Soft twinkling stars
                val starPositions = listOf(
                    Offset(40f, 50f),
                    Offset(120f, 90f),
                    Offset(220f, 40f),
                    Offset(size.width - 120f, 70f),
                    Offset(size.width - 40f, 110f),
                    Offset(160f, 140f)
                )
                for (pos in starPositions) {
                    drawCircle(
                        color = Color(0xCCFFF9C4),
                        radius = 3.5f,
                        center = pos
                    )
                }
                // Gentle crescent moon
                drawCircle(
                    color = Color(0xFFFFF59D),
                    radius = 28f,
                    center = Offset(size.width - 70f, 75f)
                )
                drawCircle(
                    color = Color(0xFF283593),
                    radius = 24f,
                    center = Offset(size.width - 80f, 70f)
                )
            }
        }
    }
}
