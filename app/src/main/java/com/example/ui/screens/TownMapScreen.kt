package com.example.ui.screens

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ChildProfile
import com.example.data.TownProgression
import com.example.model.TownLocation
import com.example.model.TownWeather
import com.example.model.TownWorld
import com.example.ui.components.KidTopBar
import com.example.ui.components.WeatherSkyOverlay
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow

@Composable
fun TownMapScreen(
    childProfile: ChildProfile?,
    currentWeather: TownWeather,
    onSelectWeather: (TownWeather) -> Unit,
    onLocationClick: (TownLocation) -> Unit,
    onEasterEggClick: (message: String) -> Unit,
    isVoiceEnabled: Boolean,
    onToggleVoice: () -> Unit,
    onParentGateClick: () -> Unit,
    townItems: List<TownProgression>,
    isHindi: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "town_ambient_anim")
    val gentleSway by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sway"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(currentWeather.skyColorTop),
                        Color(currentWeather.skyColorBottom),
                        Color(0xFFE8F5E9)
                    )
                )
            )
    ) {
        WeatherSkyOverlay(weather = currentWeather)

        Column(modifier = Modifier.fillMaxSize()) {
            // Accessible Kid Top Bar
            KidTopBar(
                title = "Little Town",
                growthPoints = childProfile?.townGrowthPoints ?: 5,
                isVoiceEnabled = isVoiceEnabled,
                onToggleVoice = onToggleVoice,
                onParentGateClick = onParentGateClick
            )

            // Day / Weather Mode Selector Pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isHindi) "दिन का समय:" else "Town Sky:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color(0xFF3E2723)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    TownWeather.values().forEach { weather ->
                        val isSelected = weather == currentWeather
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) Color.White else Color(0x77FFFFFF))
                                .border(
                                    width = if (isSelected) 2.dp else 0.5.dp,
                                    color = if (isSelected) Color(0xFFFB8C00) else Color(0x33000000),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clickable { onSelectWeather(weather) }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                                .testTag("weather_pill_${weather.name}"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = weather.icon, fontSize = 16.sp)
                        }
                    }
                }
            }

            // Living Town Interactive Meadow Banner (Touch Easter Eggs!)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xDDFFFFFF))
                    .border(1.5.dp, Color(0xFFC8E6C9), RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Easter egg: Daisy Cow
                    Box(
                        modifier = Modifier
                            .offset(y = gentleSway.dp)
                            .clickable {
                                onEasterEggClick(if (isHindi) "डेज़ी गाय बोली: माँऽऽ! नमस्ते प्यारे दोस्त!" else "Daisy the cow says: Mooo! Hello little helper!")
                            }
                            .testTag("easter_egg_cow")
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🐮", fontSize = 28.sp)
                            Text(text = "Daisy", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                        }
                    }

                    // Easter egg: Sunflowers in Bloom
                    Box(
                        modifier = Modifier
                            .offset(y = (-gentleSway).dp)
                            .clickable {
                                onEasterEggClick(if (isHindi) "सूरजमुखी मुस्कुराया: हम पानी पीकर खिले हैं!" else "The sunflowers say: We love the bright sunshine!")
                            }
                            .testTag("easter_egg_sunflower")
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🌻", fontSize = 28.sp)
                            Text(text = "Garden", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                        }
                    }

                    // Easter egg: Fire Truck Blaze
                    Box(
                        modifier = Modifier
                            .clickable {
                                onEasterEggClick(if (isHindi) "दमकल गाड़ी बोली: पों-पों! मैं हमेशा तैयार हूँ!" else "Blaze the Fire Truck beeps: Beep-beep! Ready to help!")
                            }
                            .testTag("easter_egg_firetruck")
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🚒", fontSize = 28.sp)
                            Text(text = "Blaze", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                        }
                    }

                    // Easter egg: Duck Pond
                    Box(
                        modifier = Modifier
                            .offset(y = gentleSway.dp)
                            .clickable {
                                onEasterEggClick(if (isHindi) "बत्तखें बोलीं: क्वैक क्वैक! पानी बहुत ठंडा है!" else "Little ducks say: Quack quack! The water is lovely!")
                            }
                            .testTag("easter_egg_ducks")
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🦆", fontSize = 28.sp)
                            Text(text = "Pond", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                        }
                    }
                }
            }

            // Subtitle Guidance
            Text(
                text = if (isHindi) "👉 किसी भी जगह को छुओ और खेलो!" else "👉 Tap any place to explore and help friends!",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color(0xFF4E342E),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
            )

            // The 10 Town Locations Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(TownWorld.locations) { location ->
                    TownBuildingCard(
                        location = location,
                        isHindi = isHindi,
                        onClick = { onLocationClick(location) }
                    )
                }
            }
        }
    }
}

@Composable
fun TownBuildingCard(
    location: TownLocation,
    isHindi: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(4.dp, RoundedCornerShape(22.dp))
            .clickable { onClick() }
            .testTag("town_location_${location.profession.name}"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(location.badgeColor).copy(alpha = 0.6f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Building Emoji Badge
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color(location.badgeColor).copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = location.buildingEmoji, fontSize = 26.sp)
                }

                // Play / Explore Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(location.badgeColor).copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Enter ➡️",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(location.badgeColor)
                    )
                }
            }

            Column {
                Text(
                    text = if (isHindi) location.nameHi else location.nameEn,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = Color(0xFF263238),
                    lineHeight = 18.sp
                )
                Text(
                    text = location.profession.characterName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color(0xFF78909C)
                )
            }
        }
    }
}
