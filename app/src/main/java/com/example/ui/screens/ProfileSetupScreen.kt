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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow

data class AvatarOption(val id: String, val emoji: String, val name: String)

@Composable
fun ProfileSetupScreen(
    currentProfile: ChildProfile?,
    onSaveProfile: (name: String, avatarId: String, stage: String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val avatars = listOf(
        AvatarOption("avatar_bunny", "🐰", "Bunny"),
        AvatarOption("avatar_bear", "🧸", "Teddy"),
        AvatarOption("avatar_tiger", "🐯", "Tiger"),
        AvatarOption("avatar_panda", "🐼", "Panda"),
        AvatarOption("avatar_kitten", "🐱", "Kitty"),
        AvatarOption("avatar_puppy", "🐶", "Puppy")
    )

    var nameInput by remember { mutableStateOf(currentProfile?.name ?: "Aarav") }
    var selectedAvatarId by remember { mutableStateOf(currentProfile?.avatarId ?: "avatar_bunny") }
    var selectedStage by remember { mutableStateOf(currentProfile?.developmentalStage ?: "A") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KidCreamBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🌟 My Child Profile 🌟",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFE65100)
        )
        Text(
            text = "Safe & Private • Stored only on your device",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Selected Avatar Preview
        val currentAvatar = avatars.find { it.id == selectedAvatarId } ?: avatars.first()
        Box(
            modifier = Modifier
                .size(100.dp)
                .shadow(6.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, KidSunYellow, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = currentAvatar.emoji, fontSize = 54.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Avatar selector row
        Text(
            text = "Choose Your Favorite Friend:",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(0xFF4E342E)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(avatars) { avatar ->
                val isSelected = avatar.id == selectedAvatarId
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color(0xFFFFF3E0) else Color.White)
                        .border(
                            width = if (isSelected) 3.dp else 1.dp,
                            color = if (isSelected) Color(0xFFFB8C00) else Color(0xFFFFE0B2),
                            shape = CircleShape
                        )
                        .clickable { selectedAvatarId = avatar.id }
                        .testTag("avatar_choice_${avatar.id}"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = avatar.emoji, fontSize = 32.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Name input (Nickname only)
        Text(
            text = "Child's First Name / Nickname:",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(0xFF4E342E),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it.take(15) },
            placeholder = { Text("e.g. Aarav, Ananya, Kabir") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("child_name_input"),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFFFB8C00),
                unfocusedBorderColor = Color(0xFFFFCC80)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Developmental Stage selector
        Text(
            text = "Experience Stage (Adjusts Difficulty):",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(0xFF4E342E),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        val stages = listOf(
            Triple("A", "Stage A (Ages 2–3)", "Large 2 choices • Tap & gentle observation"),
            Triple("B", "Stage B (Ages 3–4)", "3 choices • Sorting & simple sequencing"),
            Triple("C", "Stage C (Ages 4–5)", "Multi-step • Cause & effect, 'What If?'")
        )

        stages.forEach { (code, title, desc) ->
            val isSelected = selectedStage == code
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) Color(0xFFE8F5E9) else Color.White)
                    .border(
                        width = if (isSelected) 2.5.dp else 1.dp,
                        color = if (isSelected) Color(0xFF43A047) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedStage = code }
                    .padding(14.dp)
                    .testTag("stage_choice_$code")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isSelected) "🟢" else "⚪",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1B5E20)
                        )
                        Text(
                            text = desc,
                            fontSize = 12.sp,
                            color = Color(0xFF558B2F)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Save Button
        Button(
            onClick = {
                onSaveProfile(nameInput, selectedAvatarId, selectedStage)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("profile_save_btn"),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFB8C00),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Save & Enter Little Town 🚀",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
        }
    }
}
