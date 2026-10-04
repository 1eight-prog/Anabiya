package com.example.model

data class TownLocation(
    val profession: ProfessionType,
    val nameEn: String,
    val nameHi: String,
    val descriptionEn: String,
    val descriptionHi: String,
    val buildingEmoji: String,
    val badgeColor: Long,
    val isAvailableInMvp: Boolean = true,
    val ambientSoundHint: String = "gentle_town"
)

object TownWorld {

    val locations: List<TownLocation> = listOf(
        TownLocation(
            profession = ProfessionType.DOCTOR,
            nameEn = "Little Clinic",
            nameHi = "छोटा अस्पताल",
            descriptionEn = "Caring checkups, listening to heartbeats & cozy healing blankets.",
            descriptionHi = "प्यारी देखभाल, दिल की धड़कन सुनना और आराम पहुँचाना।",
            buildingEmoji = "🏥",
            badgeColor = 0xFF4FC3F7
        ),
        TownLocation(
            profession = ProfessionType.FARMER,
            nameEn = "Sunny Farm",
            nameHi = "धूप वाला खेत",
            descriptionEn = "Sprouting sunflowers, sweet fresh grass & Daisy the friendly cow.",
            descriptionHi = "सूरजमुखी के फूल, हरी घास और प्यारी डेज़ी गाय।",
            buildingEmoji = "🌾",
            badgeColor = 0xFF81C784
        ),
        TownLocation(
            profession = ProfessionType.FIREFIGHTER,
            nameEn = "Fire Station",
            nameHi = "फायर स्टेशन",
            descriptionEn = "Blaze the red fire truck, tall gentle ladders & friendly rescues.",
            descriptionHi = "लाल दमकल गाड़ी, लंबी सीढ़ी और सुरक्षित मदद।",
            buildingEmoji = "🚒",
            badgeColor = 0xFFFF8A65
        ),
        TownLocation(
            profession = ProfessionType.CHEF,
            nameEn = "Happy Kitchen",
            nameHi = "रसोई घर",
            descriptionEn = "Rainbow fruit bowls, healthy snacks & cheerful stirring.",
            descriptionHi = "रंग-बिरंगे फल, सेहतमंद नाश्ता और स्वादिष्ट पकवान।",
            buildingEmoji = "🍳",
            badgeColor = 0xFFFFD54F
        ),
        TownLocation(
            profession = ProfessionType.MECHANIC,
            nameEn = "Town Garage",
            nameHi = "टाउन गैराज",
            descriptionEn = "Fixing tricycle wheels, turning shiny bolts & making things roll smoothly.",
            descriptionHi = "साइकिल के पहिए ठीक करना और पहियों को तेज़ घुमाना।",
            buildingEmoji = "🔧",
            badgeColor = 0xFF90CAF9
        ),
        TownLocation(
            profession = ProfessionType.POLICE,
            nameEn = "Crossroads Safety",
            nameHi = "सड़क सुरक्षा",
            descriptionEn = "Zebra crossings, friendly red/yellow/green signals & helping ducklings cross.",
            descriptionHi = "ज़ेबरा क्रॉसिंग, लाल-पीली-हरी बत्ती और सुरक्षित चलना।",
            buildingEmoji = "🚦",
            badgeColor = 0xFF64B5F6
        ),
        TownLocation(
            profession = ProfessionType.ANIMAL_CARE,
            nameEn = "Animal Care Centre",
            nameHi = "पशु देखभाल",
            descriptionEn = "Grooming fluffy puppies, caring for bunnies & warm gentle pats.",
            descriptionHi = "पिल्लों और खरगोशों की देखभाल और प्यार।",
            buildingEmoji = "🐾",
            badgeColor = 0xFFAED581
        ),
        TownLocation(
            profession = ProfessionType.GARDENER,
            nameEn = "Park & Meadow",
            nameHi = "सुंदर पार्क",
            descriptionEn = "Fluttering butterflies, blooming rose bushes & wooden swings.",
            descriptionHi = "उड़ती तितलियाँ, महकते फूल और झूले।",
            buildingEmoji = "🌳",
            badgeColor = 0xFFA5D6A7
        ),
        TownLocation(
            profession = ProfessionType.BUILDER,
            nameEn = "Craft Workshop",
            nameHi = "कारीगर वर्कशॉप",
            descriptionEn = "Building birdhouses, matching colorful blocks & wooden bridges.",
            descriptionHi = "चिड़ियों का घोंसला बनाना और रंगीन ब्लॉक जोड़ना।",
            buildingEmoji = "🏗️",
            badgeColor = 0xFFFFB74D
        ),
        TownLocation(
            profession = ProfessionType.SHOPKEEPER,
            nameEn = "Friendly Market",
            nameHi = "छोटा बाज़ार",
            descriptionEn = "Sorting fresh apples, counting colorful ribbons & welcoming neighbors.",
            descriptionHi = "ताज़े सेब चुनना, रिबन गिनना और मुस्कुराते ग्राहक।",
            buildingEmoji = "🛒",
            badgeColor = 0xFFCE93D8
        )
    )
}
