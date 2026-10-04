package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.KidSunYellow
import com.example.ui.theme.KidWarmCardSurface
import com.example.viewmodel.CharacterExpression

@Composable
fun CharacterAvatarView(
    characterName: String,
    characterEmoji: String,
    speechText: String,
    expression: CharacterExpression = CharacterExpression.HAPPY,
    onSpeechClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "character_bounce")

    // Gentle vertical float / bounce animation
    val bounceY by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce_y"
    )

    // Expression indicator emoji
    val expressionEmoji = when (expression) {
        CharacterExpression.HAPPY -> "✨"
        CharacterExpression.THINKING -> "🤔"
        CharacterExpression.CHEERING -> "🎉"
        CharacterExpression.LOVING -> "💖"
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Speech Bubble
        if (speechText.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .shadow(6.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .border(2.dp, Color(0xFFFFE0B2), RoundedCornerShape(24.dp))
                    .clickable { onSpeechClick() }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = speechText,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF3E2723),
                        lineHeight = 23.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF3E0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Speak aloud again",
                            tint = Color(0xFFE65100),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Character Face & Avatar
        Box(
            modifier = Modifier
                .offset(y = bounceY.dp)
                .size(92.dp),
            contentAlignment = Alignment.Center
        ) {
            // Glow backdrop
            Box(
                modifier = Modifier
                    .size(86.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(KidSunYellow.copy(alpha = 0.6f), Color.White)
                        )
                    )
                    .border(3.dp, Color(0xFFFFB74D), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = characterEmoji,
                    fontSize = 48.sp
                )
            }

            // Emotion badge on corner
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(1.5.dp, Color(0xFFFFCC80), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = expressionEmoji,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = characterName,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(0xFF5D4037)
        )
    }
}
