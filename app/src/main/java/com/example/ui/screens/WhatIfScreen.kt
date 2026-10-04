package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.model.WhatIfChoice
import com.example.ui.components.CharacterAvatarView
import com.example.ui.components.KidTopBar
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow
import com.example.viewmodel.CharacterExpression

@Composable
fun WhatIfScreen(
    mission: Mission,
    growthPoints: Int,
    spokenNarration: String,
    characterExpression: CharacterExpression,
    selectedChoice: WhatIfChoice?,
    onChoiceSelected: (WhatIfChoice) -> Unit,
    onSpeechClick: () -> Unit,
    onBackClick: () -> Unit,
    onFinishWhatIf: () -> Unit,
    isVoiceEnabled: Boolean,
    onToggleVoice: () -> Unit,
    onParentGateClick: () -> Unit,
    isHindi: Boolean,
    modifier: Modifier = Modifier
) {
    val character = CharacterBible.getCharacterForProfession(mission.profession)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KidCreamBackground)
            .verticalScroll(rememberScrollState())
    ) {
        KidTopBar(
            title = if (isHindi) "सोचो तो क्या होगा? 🤔" else "What If? Discovery 🤔",
            onBackClick = onBackClick,
            growthPoints = growthPoints,
            isVoiceEnabled = isVoiceEnabled,
            onToggleVoice = onToggleVoice,
            onParentGateClick = onParentGateClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Animated Character Pondering
        CharacterAvatarView(
            characterName = character.name,
            characterEmoji = character.emojiAvatar,
            speechText = spokenNarration,
            expression = CharacterExpression.THINKING,
            onSpeechClick = onSpeechClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Big "What If?" Question Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(6.dp, RoundedCornerShape(26.dp)),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
            border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFB39DDB))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🔍", fontSize = 36.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (isHindi) mission.whatIfQuestionHi else mission.whatIfQuestionEn,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF4527A0),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // What If Hypothesis Options
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            mission.whatIfChoices.forEach { choice ->
                val isSelected = selectedChoice?.id == choice.id
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(if (isSelected) 6.dp else 2.dp, RoundedCornerShape(20.dp))
                        .clickable { onChoiceSelected(choice) }
                        .testTag("what_if_choice_${choice.id}"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFFFFF9C4) else Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = if (isSelected) 2.5.dp else 1.dp,
                        color = if (isSelected) Color(0xFFF57F17) else Color(0xFFFFCC80)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = choice.emoji, fontSize = 36.sp)
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = if (isHindi) choice.textHi else choice.textEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF37474F)
                            )
                        }
                    }
                }
            }
        }

        // Outcome Reveal Box
        AnimatedVisibility(
            visible = selectedChoice != null,
            enter = fadeIn() + scaleIn()
        ) {
            selectedChoice?.let { choice ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .shadow(4.dp, RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF81C784))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "✨", fontSize = 30.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isHindi) choice.outcomeHi else choice.outcomeEn,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1B5E20),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Back to Town Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onFinishWhatIf,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("what_if_finish_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = if (isHindi) "वापस शहर चलें 🏡" else "Back to Little Town 🏡",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
