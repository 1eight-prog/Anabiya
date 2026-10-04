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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.model.CharacterBible
import com.example.model.Mission
import com.example.model.ToolItem
import com.example.ui.components.CelebrationOverlay
import com.example.ui.components.CharacterAvatarView
import com.example.ui.components.KidTopBar
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow
import com.example.viewmodel.CharacterExpression
import com.example.viewmodel.MissionPhase

@Composable
fun MissionPlayScreen(
    mission: Mission,
    growthPoints: Int,
    missionPhase: MissionPhase,
    spokenNarration: String,
    characterExpression: CharacterExpression,
    wrongAttemptCount: Int,
    actionAnimationActive: Boolean,
    onSpeechClick: () -> Unit,
    onBackClick: () -> Unit,
    onProceedToSelection: () -> Unit,
    onToolSelected: (ToolItem) -> Unit,
    onCompleteAction: () -> Unit,
    onTriggerCelebration: () -> Unit,
    onOpenWhatIf: () -> Unit,
    onContinueAfterCelebration: () -> Unit,
    isVoiceEnabled: Boolean,
    onToggleVoice: () -> Unit,
    onParentGateClick: () -> Unit,
    isHindi: Boolean,
    modifier: Modifier = Modifier
) {
    val character = CharacterBible.getCharacterForProfession(mission.profession)

    val infiniteTransition = rememberInfiniteTransition(label = "mission_action_anim")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val bounceActionY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -16f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "action_bounce"
    )

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(KidCreamBackground)
                .verticalScroll(rememberScrollState())
        ) {
            KidTopBar(
                title = if (isHindi) mission.titleHi else mission.titleEn,
                onBackClick = onBackClick,
                growthPoints = growthPoints,
                isVoiceEnabled = isVoiceEnabled,
                onToggleVoice = onToggleVoice,
                onParentGateClick = onParentGateClick
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Animated Character Speaking
            CharacterAvatarView(
                characterName = character.name,
                characterEmoji = character.emojiAvatar,
                speechText = spokenNarration,
                expression = characterExpression,
                onSpeechClick = onSpeechClick
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Central Interactive Play Stage Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(210.dp)
                    .shadow(6.dp, RoundedCornerShape(28.dp))
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFF9E6), Color.White, Color(0xFFF1F8E9))
                        )
                    )
                    .border(2.5.dp, Color(mission.profession.themeColor).copy(alpha = 0.5f), RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                when (missionPhase) {
                    MissionPhase.PROBLEM_DISCOVERY -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = mission.subjectEmoji, fontSize = 72.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isHindi) "👉 इसे ध्यान से देखो!" else "👉 Look closely at our friend!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF5D4037)
                            )
                        }
                    }

                    MissionPhase.TOOL_SELECTION -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = mission.subjectEmoji,
                                fontSize = 68.sp,
                                modifier = Modifier.scale(if (wrongAttemptCount >= 2) pulseScale else 1f)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (isHindi) "नीचे से सही औज़ार चुनो 👇" else "Pick a tool from below 👇",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = Color(0xFF0277BD)
                            )
                        }
                    }

                    MissionPhase.ACTION_IN_PROGRESS -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier.offset(y = bounceActionY.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = mission.subjectEmoji, fontSize = 64.sp)
                                    Text(text = "✨", fontSize = 38.sp)
                                    val correctTool = mission.toolChoices.firstOrNull { it.isCorrect }
                                    Text(text = correctTool?.iconEmoji ?: "🛠️", fontSize = 56.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = if (isHindi) mission.actionVerbHi else mission.actionVerbEn,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }

                    MissionPhase.CONSEQUENCE, MissionPhase.CELEBRATION -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${mission.subjectEmoji} 💖",
                                fontSize = 72.sp,
                                modifier = Modifier.scale(pulseScale)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isHindi) "बहुत बढ़िया! सब ठीक हो गया!" else "Wonderful! Everything is great!",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = Color(0xFFE65100)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lower Action Controls based on Phase
            when (missionPhase) {
                MissionPhase.PROBLEM_DISCOVERY -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onProceedToSelection,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("mission_proceed_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text(
                                text = if (isHindi) "चलो मदद करें! 🤝" else "Let's Help! 🤝",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp
                            )
                        }
                    }
                }

                MissionPhase.TOOL_SELECTION -> {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                        Text(
                            text = if (isHindi) "औज़ार (Choose a tool):" else "Available Items:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF455A64),
                            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                        )

                        // Big accessible tool cards
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            items(mission.toolChoices) { tool ->
                                val shouldGlow = tool.isCorrect && wrongAttemptCount >= 2
                                Card(
                                    modifier = Modifier
                                        .size(width = 145.dp, height = 125.dp)
                                        .shadow(if (shouldGlow) 10.dp else 4.dp, RoundedCornerShape(24.dp))
                                        .scale(if (shouldGlow) pulseScale else 1f)
                                        .clickable { onToolSelected(tool) }
                                        .testTag("tool_choice_${tool.id}"),
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (shouldGlow) Color(0xFFFFF9C4) else Color.White
                                    ),
                                    border = androidx.compose.foundation.BorderStroke(
                                        width = if (shouldGlow) 3.5.dp else 2.dp,
                                        color = if (shouldGlow) Color(0xFFFFA000) else Color(0xFFFFCC80)
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize().padding(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = tool.iconEmoji, fontSize = 42.sp)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = if (isHindi) tool.nameHi else tool.nameEn,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF3E2723),
                                            lineHeight = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                MissionPhase.ACTION_IN_PROGRESS -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onCompleteAction,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("mission_action_finish_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1)),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text(
                                text = if (isHindi) "आगे देखो क्या हुआ! ✨" else "See what happens! ✨",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp
                            )
                        }
                    }
                }

                MissionPhase.CONSEQUENCE -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onTriggerCelebration,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("mission_celebrate_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text(
                                text = if (isHindi) "बधाई! जश्न मनाएँ! 🎉" else "Celebrate! 🎉",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp
                            )
                        }
                    }
                }

                MissionPhase.CELEBRATION -> {
                    // Celebration overlay handles this state!
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Star Celebration Overlay Dialog
        CelebrationOverlay(
            visible = missionPhase == MissionPhase.CELEBRATION,
            title = if (isHindi) "शाबाश! आपने मदद की! 🌟" else "Hooray! You Saved the Day! 🌟",
            celebrationText = if (isHindi) mission.celebrationNarrationHi else mission.celebrationNarrationEn,
            skillLearned = mission.learningSkill,
            onContinueClick = onContinueAfterCelebration,
            onWhatIfClick = onOpenWhatIf
        )
    }
}
