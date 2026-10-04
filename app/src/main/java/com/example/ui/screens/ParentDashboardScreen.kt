package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ChildProfile
import com.example.data.CompletedMission
import com.example.data.ParentSettings
import com.example.ui.theme.KidCreamBackground

data class SkillCategory(val name: String, val icon: String, val description: String)

@Composable
fun ParentDashboardScreen(
    childProfile: ChildProfile?,
    completedMissions: List<CompletedMission>,
    parentSettings: ParentSettings?,
    onSaveSettings: (voiceEnabled: Boolean, sfxEnabled: Boolean, languageCode: String, sessionMinutes: Int) -> Unit,
    onEditProfileClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var voiceEnabled by remember(parentSettings) {
        mutableStateOf(parentSettings?.voiceNarrationEnabled ?: true)
    }
    var sfxEnabled by remember(parentSettings) {
        mutableStateOf(parentSettings?.soundEffectsEnabled ?: true)
    }
    var selectedLanguage by remember(parentSettings) {
        mutableStateOf(parentSettings?.languageCode ?: "en")
    }
    var sessionTimer by remember(parentSettings) {
        mutableStateOf(parentSettings?.sessionTimerMinutes ?: 20)
    }

    val skillsExplored = listOf(
        SkillCategory("Empathy & Care", "🤝", "Helped patients, cared for pets & comforted friends."),
        SkillCategory("Nature & Growth", "🌱", "Learned how plants drink water and grow towards the sun."),
        SkillCategory("Emergency & Safety", "🚦", "Learned road crossing lights and firefighter teamwork."),
        SkillCategory("Mechanics & Shapes", "🔧", "Discovered why circular wheels roll smoothly."),
        SkillCategory("Nutrition & Hygiene", "🍎", "Discovered washing fresh fruits and rainbow healthy snacks.")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KidCreamBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag("parent_dashboard_back_btn")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Game",
                    tint = Color(0xFF4527A0)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = "Parent Dashboard",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF311B92)
                )
                Text(
                    text = "Learning Insights & Healthy Play Controls",
                    fontSize = 12.sp,
                    color = Color(0xFF5E35B1)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Child Activity Overview Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(22.dp)),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD1C4E9))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Playing as: ${childProfile?.name ?: "Aarav"}",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            color = Color(0xFF311B92)
                        )
                        Text(
                            text = "Stage ${childProfile?.developmentalStage ?: "A"} • Playful Explorer",
                            fontSize = 13.sp,
                            color = Color(0xFF673AB7)
                        )
                    }
                    Button(
                        onClick = onEditProfileClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDE7F6), contentColor = Color(0xFF5E35B1)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Edit Profile", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Divider(color = Color(0xFFEDE7F6))
                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🌟 ${childProfile?.townGrowthPoints ?: 5}", fontWeight = FontWeight.Black, fontSize = 20.sp, color = Color(0xFFF57F17))
                        Text(text = "Town Stars", fontSize = 12.sp, color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🎯 ${completedMissions.size}", fontWeight = FontWeight.Black, fontSize = 20.sp, color = Color(0xFF2E7D32))
                        Text(text = "Missions Solved", fontSize = 12.sp, color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "⏱️ ${childProfile?.totalPlayMinutes ?: 15}m", fontWeight = FontWeight.Black, fontSize = 20.sp, color = Color(0xFF1565C0))
                        Text(text = "Play Time", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Developmental Skills Encountered (Not graded!)
        Text(
            text = "🌱 Real-World Concepts Explored:",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color(0xFF263238)
        )
        Text(
            text = "Natural discoveries made through play without tests or stress:",
            fontSize = 12.sp,
            color = Color(0xFF546E7A)
        )

        Spacer(modifier = Modifier.height(10.dp))

        skillsExplored.forEach { skill ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = skill.icon, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = skill.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF263238)
                        )
                        Text(
                            text = skill.description,
                            fontSize = 12.sp,
                            color = Color(0xFF616161)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Parental Controls & Audio Settings
        Text(
            text = "⚙️ Play & Audio Settings:",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color(0xFF263238)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD1C4E9))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Voice Narration Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Character Voice Narration", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(text = "Warm spoken dialogue via Text-to-Speech", fontSize = 12.sp, color = Color.Gray)
                    }
                    Switch(
                        checked = voiceEnabled,
                        onCheckedChange = { voiceEnabled = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF5E35B1)),
                        modifier = Modifier.testTag("parent_toggle_voice")
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color(0xFFF3E5F5))
                Spacer(modifier = Modifier.height(12.dp))

                // Sound Effects Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Procedural Kid Sound Effects", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(text = "Heartbeats, water drops, bell chimes", fontSize = 12.sp, color = Color.Gray)
                    }
                    Switch(
                        checked = sfxEnabled,
                        onCheckedChange = { sfxEnabled = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF5E35B1)),
                        modifier = Modifier.testTag("parent_toggle_sfx")
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color(0xFFF3E5F5))
                Spacer(modifier = Modifier.height(12.dp))

                // Language Selector
                Text(text = "App Spoken Language:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        Pair("en", "English 🇬🇧"),
                        Pair("hi", "हिंदी (Hindi) 🇮🇳")
                    ).forEach { (code, label) ->
                        val isSelected = selectedLanguage == code
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) Color(0xFF5E35B1) else Color(0xFFEDE7F6))
                                .clickable { selectedLanguage = code }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                                .testTag("lang_select_$code"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                color = if (isSelected) Color.White else Color(0xFF5E35B1),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Save Settings Button
        Button(
            onClick = {
                onSaveSettings(voiceEnabled, sfxEnabled, selectedLanguage, sessionTimer)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("parent_save_settings_btn"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E35B1)),
            shape = RoundedCornerShape(26.dp)
        ) {
            Text(
                text = "Apply Settings & Return to Game ✨",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
