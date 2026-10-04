package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterBible
import com.example.model.Mission
import com.example.model.MissionCatalog
import com.example.model.TownLocation
import com.example.ui.components.CharacterAvatarView
import com.example.ui.components.KidTopBar
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow
import com.example.viewmodel.CharacterExpression

@Composable
fun LocationOverviewScreen(
    location: TownLocation,
    growthPoints: Int,
    spokenNarration: String,
    characterExpression: CharacterExpression,
    onSpeechClick: () -> Unit,
    onBackClick: () -> Unit,
    onStartMission: (Mission) -> Unit,
    onFreeExploreClick: () -> Unit,
    isVoiceEnabled: Boolean,
    onToggleVoice: () -> Unit,
    onParentGateClick: () -> Unit,
    isHindi: Boolean,
    stage: String?,
    modifier: Modifier = Modifier
) {
    val character = CharacterBible.getCharacterForProfession(location.profession)
    val missions = MissionCatalog.getMissionsForProfession(location.profession, stage)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(location.badgeColor).copy(alpha = 0.35f),
                        KidCreamBackground,
                        Color.White
                    )
                )
            )
    ) {
        KidTopBar(
            title = if (isHindi) location.nameHi else location.nameEn,
            onBackClick = onBackClick,
            growthPoints = growthPoints,
            isVoiceEnabled = isVoiceEnabled,
            onToggleVoice = onToggleVoice,
            onParentGateClick = onParentGateClick
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Animated Character & Speech Bubble
            item {
                CharacterAvatarView(
                    characterName = character.name,
                    characterEmoji = character.emojiAvatar,
                    speechText = spokenNarration,
                    expression = characterExpression,
                    onSpeechClick = onSpeechClick
                )
            }

            // Big "Free Explore" Sandbox Banner
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(24.dp))
                        .clickable { onFreeExploreClick() }
                        .testTag("location_free_explore_banner"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    border = androidx.compose.foundation.BorderStroke(2.dp, KidSunYellow)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🎈", fontSize = 34.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = if (isHindi) "खुली दुनिया में घूमो (Free Explore)" else "Free Pretend & Explore",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp,
                                    color = Color(0xFFE65100)
                                )
                                Text(
                                    text = if (isHindi) "बिना नियमों के जो चाहें छुओ!" else "Touch, tap & make sounds freely!",
                                    fontSize = 12.sp,
                                    color = Color(0xFF6D4C41)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(KidSunYellow),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Start explore",
                                tint = Color(0xFFE65100)
                            )
                        }
                    }
                }
            }

            // Story Missions Section Header
            item {
                Text(
                    text = if (isHindi) "🌟 कहानी मिशन (Missions):" else "🌟 Story Adventures:",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = Color(0xFF263238),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (missions.isEmpty()) {
                item {
                    Text(
                        text = if (isHindi) "जल्द ही और कहानियाँ आ रही हैं!" else "More story adventures coming soon!",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                items(missions) { mission ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(3.dp, RoundedCornerShape(20.dp))
                            .clickable { onStartMission(mission) }
                            .testTag("mission_card_${mission.id}"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFFFCC80))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Color(location.badgeColor).copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = mission.subjectEmoji, fontSize = 28.sp)
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = if (isHindi) mission.titleHi else mission.titleEn,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xFF37474F)
                                    )
                                    Text(
                                        text = "Skill: ${mission.learningSkill}",
                                        fontSize = 12.sp,
                                        color = Color(0xFF00897B),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Button(
                                onClick = { onStartMission(mission) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF43A047)
                                ),
                                shape = RoundedCornerShape(16.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "Play ▶",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
