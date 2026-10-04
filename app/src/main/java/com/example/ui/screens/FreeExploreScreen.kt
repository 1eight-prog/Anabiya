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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.model.ProfessionType
import com.example.model.TownLocation
import com.example.ui.components.CharacterAvatarView
import com.example.ui.components.KidTopBar
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.KidSunYellow
import com.example.viewmodel.CharacterExpression

data class ExploreProp(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val emoji: String,
    val soundReactionEn: String,
    val soundReactionHi: String,
    val soundType: String // "heartbeat", "water", "siren", "sparkle", "bell"
)

@Composable
fun FreeExploreScreen(
    location: TownLocation,
    growthPoints: Int,
    spokenNarration: String,
    characterExpression: CharacterExpression,
    onSpeechClick: () -> Unit,
    onBackClick: () -> Unit,
    onPropTapped: (ExploreProp) -> Unit,
    isVoiceEnabled: Boolean,
    onToggleVoice: () -> Unit,
    onParentGateClick: () -> Unit,
    isHindi: Boolean,
    modifier: Modifier = Modifier
) {
    val character = CharacterBible.getCharacterForProfession(location.profession)

    // Props customized for the specific location!
    val props = remember(location.profession) {
        when (location.profession) {
            ProfessionType.DOCTOR -> listOf(
                ExploreProp("stethoscope", "Stethoscope", "स्टेथोस्कोप", "🩺", "Lub-dub, lub-dub! A healthy heartbeat!", "धक-धक! स्वस्थ दिल की धड़कन!", "heartbeat"),
                ExploreProp("teddy", "Teddy Bear", "टेडी बेयर", "🧸", "Teddy says: A big warm hug!", "टेडी बोला: प्यार से गले लगाओ!", "sparkle"),
                ExploreProp("puppy", "Clinic Puppy", "छोटा पिल्ला", "🐶", "Puppy wags tail: Woof! Feeling happy!", "पिल्ले ने पूंछ हिलाई: भौं भौं!", "sparkle"),
                ExploreProp("blanket", "Soft Blanket", "मुलायम कंबल", "🛏️", "So cozy and warm for resting!", "आराम के लिए बिल्कुल तैयार!", "sparkle"),
                ExploreProp("water", "Herbal Water", "साफ़ पानी", "🥛", "Gulp! Cool and refreshing drink!", "गट-गट! ताज़ा पानी!", "water"),
                ExploreProp("bandage", "Star Bandages", "तारे वाली पट्टी", "🩹", "Sticky, soft, and protects little bumps!", "प्यारी पट्टी चोट को सुरक्षित रखेगी!", "sparkle")
            )
            ProfessionType.FARMER -> listOf(
                ExploreProp("cow", "Daisy the Cow", "डेज़ी गाय", "🐮", "Mooo! Daisy loves eating green grass!", "भाँऽऽ! डेज़ी को हरी घास पसंद है!", "sparkle"),
                ExploreProp("watering_can", "Water Can", "पानी का फव्वारा", "💧", "Splish, splash! Giving life to flowers!", "छम-छम! पौधों को पानी मिल रहा है!", "water"),
                ExploreProp("sunflower", "Big Sunflower", "सूरजमुखी", "🌻", "Turning happily towards the bright sun!", "सूरज की तरफ मुस्कुराता हुआ!", "sparkle"),
                ExploreProp("tractor", "Green Tractor", "ट्रैक्टर", "🚜", "Chug-chug-chug! Ready for the soil!", "धुक-धुक! खेत जोतने के लिए तैयार!", "bell"),
                ExploreProp("chicks", "Little Chicks", "चूज़े", "🐥", "Peep-peep! Hungry for yummy seeds!", "चीं-चीं! नन्हे चूज़े दाना चुग रहे हैं!", "sparkle"),
                ExploreProp("carrot", "Crunchy Carrots", "गाजर", "🥕", "Sweet and crunchy straight from the earth!", "मीठी और ताज़ा गाजर!", "sparkle")
            )
            ProfessionType.FIREFIGHTER -> listOf(
                ExploreProp("fire_truck", "Blaze Fire Engine", "दमकल गाड़ी", "🚒", "Honk-honk! Siren sounds: Wee-woo!", "पों-पों! गाड़ी तैयार है!", "siren"),
                ExploreProp("fire_bell", "Golden Fire Bell", "घंटी", "🔔", "Ding-dong! The station bell rings clearly!", "टन-टन! स्टेशन की घंटी बजी!", "bell"),
                ExploreProp("ladder", "Rescue Ladder", "लंबी सीढ़ी", "🪜", "Reaching gently up towards the treetops!", "पेड़ की डालियों तक पहुँचती सीढ़ी!", "sparkle"),
                ExploreProp("water_hose", "Water Hose", "पानी का होज़", "🚿", "Whooosh! Spraying cool mist!", "सररर! ठंडा पानी का फव्वारा!", "water"),
                ExploreProp("helmet", "Fire Helmet", "सुरक्षा हेलमेट", "🪖", "Strong, shiny, and keeps our head safe!", "सिर को सुरक्षित रखने वाला मजबूत हेलमेट!", "sparkle"),
                ExploreProp("kitten", "Tree Kitten", "प्यारी बिल्ली", "🐱", "Meow! Safely cuddled in loving arms!", "म्याऊँ! सुरक्षित गोद में प्यार!", "sparkle")
            )
            else -> listOf(
                ExploreProp("prop_star", "Magic Sparkle", "जादुई तारा", "✨", "Sparkle sparkle! What a pretty light!", "चम-चम चमकता हुआ तारा!", "sparkle"),
                ExploreProp("prop_balloon", "Red Balloon", "लाल गुब्बारा", "🎈", "Float high up in the blue sky!", "नीले आसमान में उड़ता गुब्बारा!", "sparkle"),
                ExploreProp("prop_bell", "Tinkle Bell", "घंटी", "🔔", "Tinkle tinkle! A sweet cheerful chime!", "टन-टन बजती प्यारी घंटी!", "bell"),
                ExploreProp("prop_flower", "Garden Blossom", "फूल", "🌸", "Smells so fresh and lovely!", "महकता हुआ सुंदर फूल!", "sparkle")
            )
        }
    }

    var lastTappedPropId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KidCreamBackground)
    ) {
        KidTopBar(
            title = if (isHindi) "${location.nameHi} - खेलो" else "${location.nameEn} - Explore",
            onBackClick = onBackClick,
            growthPoints = growthPoints,
            isVoiceEnabled = isVoiceEnabled,
            onToggleVoice = onToggleVoice,
            onParentGateClick = onParentGateClick
        )

        // Character feedback
        CharacterAvatarView(
            characterName = character.name,
            characterEmoji = character.emojiAvatar,
            speechText = spokenNarration,
            expression = characterExpression,
            onSpeechClick = onSpeechClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (isHindi) "👉 किसी भी चीज़ को छुओ और आवाज़ सुनो!" else "👉 Tap any object to play with it!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )

        // Props Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(props) { prop ->
                val isJustTapped = lastTappedPropId == prop.id
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .shadow(if (isJustTapped) 8.dp else 3.dp, RoundedCornerShape(22.dp))
                        .clickable {
                            lastTappedPropId = prop.id
                            onPropTapped(prop)
                        }
                        .testTag("explore_prop_${prop.id}"),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isJustTapped) Color(0xFFFFF9C4) else Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = if (isJustTapped) 2.5.dp else 1.dp,
                        color = if (isJustTapped) Color(0xFFFFA000) else Color(0xFFFFCC80)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = prop.emoji, fontSize = 42.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isHindi) prop.nameHi else prop.nameEn,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFF37474F),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
