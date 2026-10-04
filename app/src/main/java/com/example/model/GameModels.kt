package com.example.model

import androidx.compose.ui.graphics.Color

enum class ProfessionType(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val characterName: String,
    val locationNameEn: String,
    val locationNameHi: String,
    val emoji: String,
    val themeColor: Long,
    val iconName: String
) {
    DOCTOR(
        id = "DOCTOR",
        titleEn = "Doctor",
        titleHi = "डॉक्टर (Doctor)",
        characterName = "Dr. Anya",
        locationNameEn = "Little Clinic",
        locationNameHi = "छोटा अस्पताल (Clinic)",
        emoji = "🩺",
        themeColor = 0xFF4FC3F7,
        iconName = "LocalHospital"
    ),
    FARMER(
        id = "FARMER",
        titleEn = "Farmer",
        titleHi = "किसान (Farmer)",
        characterName = "Farmer Kabir",
        locationNameEn = "Sunny Farm",
        locationNameHi = "धूप वाला खेत (Farm)",
        emoji = "🌾",
        themeColor = 0xFF81C784,
        iconName = "Agriculture"
    ),
    FIREFIGHTER(
        id = "FIREFIGHTER",
        titleEn = "Firefighter",
        titleHi = "दमकल कर्मी (Firefighter)",
        characterName = "Firefighter Tara",
        locationNameEn = "Fire Station",
        locationNameHi = "फायर स्टेशन (Fire Station)",
        emoji = "🚒",
        themeColor = 0xFFFF8A65,
        iconName = "FireTruck"
    ),
    CHEF(
        id = "CHEF",
        titleEn = "Chef",
        titleHi = "शेफ (Chef)",
        characterName = "Chef Rohan",
        locationNameEn = "Happy Kitchen",
        locationNameHi = "रसोई घर (Kitchen)",
        emoji = "🍳",
        themeColor = 0xFFFFD54F,
        iconName = "Restaurant"
    ),
    MECHANIC(
        id = "MECHANIC",
        titleEn = "Mechanic",
        titleHi = "मैकेनिक (Mechanic)",
        characterName = "Mechanic Meera",
        locationNameEn = "Town Garage",
        locationNameHi = "गैराज (Garage)",
        emoji = "🔧",
        themeColor = 0xFF90CAF9,
        iconName = "Build"
    ),
    POLICE(
        id = "POLICE",
        titleEn = "Traffic Safety Helper",
        titleHi = "ट्रैफिक मित्र (Traffic Helper)",
        characterName = "Officer Vikram",
        locationNameEn = "Crossroads Safety",
        locationNameHi = "सड़क सुरक्षा (Road Safety)",
        emoji = "🚦",
        themeColor = 0xFF64B5F6,
        iconName = "Traffic"
    ),
    ANIMAL_CARE(
        id = "ANIMAL_CARE",
        titleEn = "Animal Care Worker",
        titleHi = "पशु मित्र (Animal Doctor)",
        characterName = "Dr. Leo",
        locationNameEn = "Pet Care Centre",
        locationNameHi = "पशु देखभाल केंद्र",
        emoji = "🐾",
        themeColor = 0xFFAED581,
        iconName = "Pets"
    ),
    GARDENER(
        id = "GARDENER",
        titleEn = "Gardener",
        titleHi = "माली (Gardener)",
        characterName = "Gardener Shanti",
        locationNameEn = "Town Park & Meadow",
        locationNameHi = "सुंदर पार्क (Park)",
        emoji = "🌳",
        themeColor = 0xFFA5D6A7,
        iconName = "Park"
    ),
    BUILDER(
        id = "BUILDER",
        titleEn = "Builder",
        titleHi = "कारीगर (Builder)",
        characterName = "Builder Bunty",
        locationNameEn = "Craft Workshop",
        locationNameHi = "वर्कशॉप (Workshop)",
        emoji = "🏗️",
        themeColor = 0xFFFFB74D,
        iconName = "Construction"
    ),
    SHOPKEEPER(
        id = "SHOPKEEPER",
        titleEn = "Market Helper",
        titleHi = "दुकानदार (Shopkeeper)",
        characterName = "Shopkeeper Amit",
        locationNameEn = "Friendly Market",
        locationNameHi = "छोटा बाज़ार (Market)",
        emoji = "🛒",
        themeColor = 0xFFCE93D8,
        iconName = "ShoppingCart"
    )
}

data class ToolItem(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val iconEmoji: String,
    val isCorrect: Boolean,
    val gentleFeedbackEn: String,
    val gentleFeedbackHi: String,
    val color: Long = 0xFFFFF9C4
)

data class WhatIfChoice(
    val id: String,
    val textEn: String,
    val textHi: String,
    val emoji: String,
    val outcomeEn: String,
    val outcomeHi: String
)

data class Mission(
    val id: String,
    val profession: ProfessionType,
    val stage: String, // "A", "B", "C"
    val titleEn: String,
    val titleHi: String,
    val problemNarrationEn: String,
    val problemNarrationHi: String,
    val characterEmoji: String,
    val subjectEmoji: String,
    val toolChoices: List<ToolItem>,
    val actionVerbEn: String,
    val actionVerbHi: String,
    val consequenceNarrationEn: String,
    val consequenceNarrationHi: String,
    val celebrationNarrationEn: String,
    val celebrationNarrationHi: String,
    val whatIfQuestionEn: String,
    val whatIfQuestionHi: String,
    val whatIfChoices: List<WhatIfChoice>,
    val learningSkill: String
)

enum class TownWeather(val title: String, val icon: String, val skyColorTop: Long, val skyColorBottom: Long) {
    SUNNY("Sunny Morning", "☀️", 0xFF81D4FA, 0xFFE1F5FE),
    RAINY("Gentle Rain", "🌧️", 0xFF90A4AE, 0xFFCFD8DC),
    EVENING("Golden Sunset", "🌅", 0xFFFFAB91, 0xFFFFE0B2),
    NIGHT("Starlit Night", "🌙", 0xFF283593, 0xFF4527A0)
}
