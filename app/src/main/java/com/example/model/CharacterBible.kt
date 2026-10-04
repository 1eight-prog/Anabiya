package com.example.model

data class KidCharacter(
    val id: String,
    val name: String,
    val profession: ProfessionType,
    val roleTitleEn: String,
    val roleTitleHi: String,
    val personality: String,
    val emojiAvatar: String,
    val greetingEn: String,
    val greetingHi: String,
    val encouragingPhrasesEn: List<String>,
    val encouragingPhrasesHi: List<String>,
    val wrongChoiceGuidanceEn: List<String>,
    val wrongChoiceGuidanceHi: List<String>,
    val celebrationPhrasesEn: List<String>,
    val celebrationPhrasesHi: List<String>,
    val whatIfPonderingEn: String,
    val whatIfPonderingHi: String
)

object CharacterBible {

    val characters: Map<String, KidCharacter> = mapOf(
        "dr_anya" to KidCharacter(
            id = "dr_anya",
            name = "Dr. Anya",
            profession = ProfessionType.DOCTOR,
            roleTitleEn = "Friendly Clinic Doctor",
            roleTitleHi = "प्यारी डॉक्टर अन्या",
            personality = "Gentle, calm, warm, speaks with a soft reassuring voice",
            emojiAvatar = "👩‍⚕️",
            greetingEn = "Hello, little friend! Welcome to our cozy clinic. Let's make everyone feel comfortable today!",
            greetingHi = "नमस्ते प्यारे दोस्त! हमारे अस्पताल में आपका स्वागत है। चलो सबको स्वस्थ बनाएँ!",
            encouragingPhrasesEn = listOf(
                "You have such a kind and gentle heart!",
                "You're observing so carefully!",
                "Let's see what helps our little patient feel better!"
            ),
            encouragingPhrasesHi = listOf(
                "आप बहुत प्यारे और दयालु हैं!",
                "शाबाश, कितनी अच्छी तरह देख रहे हो!",
                "चलो देखते हैं कौन सी चीज़ आराम पहुँचाएगी!"
            ),
            wrongChoiceGuidanceEn = listOf(
                "Hmm... that is a wonderful thing, but what does our friend need right now?",
                "That looks interesting! Can you spot the tool we use to listen or care for little bumps?",
                "You're getting closer, little helper! Take another peek!"
            ),
            wrongChoiceGuidanceHi = listOf(
                "हम्म... यह तो बहुत अच्छी चीज़ है, पर अभी हमारे दोस्त को क्या चाहिए?",
                "बहुत पास पहुँच गए! एक बार फिर से देखो।",
                "चलो साथ मिलकर ढूँढते हैं!"
            ),
            celebrationPhrasesEn = listOf(
                "Ahh, that's exactly what was needed! Look at that happy smile!",
                "You took such gentle care of our patient! High five!",
                "Doctor-approved! You saved the day with kindness!"
            ),
            celebrationPhrasesHi = listOf(
                "वाह! बिल्कुल यही चाहिए था! देखो कितनी प्यारी मुस्कान आई!",
                "आपने बहुत प्यार से देखभाल की! ताली बजाओ!",
                "कमाल कर दिया हमारे छोटे डॉक्टर ने!"
            ),
            whatIfPonderingEn = "I wonder... what happens when we give someone a warm cup of water and plenty of rest?",
            whatIfPonderingHi = "सोचो तो... आराम करने से हमारा शरीर कैसे ठीक होता है?"
        ),

        "farmer_kabir" to KidCharacter(
            id = "farmer_kabir",
            name = "Farmer Kabir",
            profession = ProfessionType.FARMER,
            roleTitleEn = "Sunny Farm Caretaker",
            roleTitleHi = "किसान कबीर",
            personality = "Cheerful, earthy, loves nature, chuckles warmly",
            emojiAvatar = "👨‍🌾",
            greetingEn = "Good morning, little sprout! The rooster is singing, and the soil is ready for sunshine!",
            greetingHi = "सुप्रभात नन्हें साथी! सूरज निकल आया है और खेत मुस्कुरा रहे हैं!",
            encouragingPhrasesEn = listOf(
                "Nature takes its time, and you're doing so wonderfully!",
                "Look at you caring for the earth!",
                "Every tiny seed has a big dream to grow!"
            ),
            encouragingPhrasesHi = listOf(
                "धरती माँ से प्यार करना बहुत अच्छी बात है!",
                "आप बहुत अच्छे से पौधों की देखभाल कर रहे हैं!",
                "छोटा बीज बड़ा पेड़ बनेगा!"
            ),
            wrongChoiceGuidanceEn = listOf(
                "Ho ho! That's fun, but plants can't drink that! Can you find what makes water sparkle?",
                "Almost there! Look for something green or filled with water droplets!",
                "Let's ask the sun and the clouds what our crops need!"
            ),
            wrongChoiceGuidanceHi = listOf(
                "अरे वाह! पर पौधे तो इसे नहीं पी सकते। पानी वाली चीज़ कहाँ है?",
                "बस थोड़ा सा और ध्यान से देखो!",
                "पौधों को तो ताज़ा पानी पसंद है!"
            ),
            celebrationPhrasesEn = listOf(
                "Look at it bloom! Green leaves and golden petals!",
                "The farm is dancing with joy because of your help!",
                "Daisy the cow gave a happy moo just for you!"
            ),
            celebrationPhrasesHi = listOf(
                "अरे वाह! कितना सुंदर फूल खिल गया!",
                "खेत खुशी से झूम उठा!",
                "डेज़ी गाय ने खुशी से नमस्ते कहा!"
            ),
            whatIfPonderingEn = "What if we plant a sunflower seed together and watch it every morning?",
            whatIfPonderingHi = "सोचो अगर हम रोज़ सुबह सूरजमुखी को पानी दें तो क्या होगा?"
        ),

        "firefighter_tara" to KidCharacter(
            id = "firefighter_tara",
            name = "Firefighter Tara",
            profession = ProfessionType.FIREFIGHTER,
            roleTitleEn = "Brave Fire Station Hero",
            roleTitleHi = "फायरफाइटर तारा",
            personality = "Energetic, courageous, protective, big warm sisterly vibes",
            emojiAvatar = "👩‍🚒",
            greetingEn = "Ready for safety patrol, partner? Blaze the fire engine has its lights polished and ready!",
            greetingHi = "नमस्ते साथी! हमारी दमकल गाड़ी बिल्कुल तैयार है मदद के लिए!",
            encouragingPhrasesEn = listOf(
                "Being safe and helping friends is the true superpower!",
                "You have quick eyes and a brave heart!",
                "Together we keep Little Town peaceful and safe!"
            ),
            encouragingPhrasesHi = listOf(
                "दूसरों की मदद करना ही सबसे बड़ी बहादुरी है!",
                "आपकी नज़रें बहुत तेज़ हैं!",
                "हम सब मिलकर शहर को सुरक्षित रखेंगे!"
            ),
            wrongChoiceGuidanceEn = listOf(
                "That's a neat item, but can it reach high in the air or spray cool water?",
                "Take a close look at our fire equipment on the red engine!",
                "Firefighters always think: what makes things safe and cool?"
            ),
            wrongChoiceGuidanceHi = listOf(
                "यह तो अच्छा है, पर आग बुझाने या ऊपर चढ़ने में क्या काम आएगा?",
                "गाड़ी पर लगे औज़ारों को ध्यान से देखो!",
                "सुरक्षा वाला औज़ार चुनो!"
            ),
            celebrationPhrasesEn = listOf(
                "Mission accomplished, brave helper! Little Town is safe and sound!",
                "Blaze the fire engine beeps for you: Beep-beep!",
                "You are an honorary firefighter hero today!"
            ),
            celebrationPhrasesHi = listOf(
                "शाबाश! आपने सबको सुरक्षित बचा लिया!",
                "गाड़ी ने खुशी से हॉर्न बजाया: पों-पों!",
                "आज आप हमारे बहादुर हीरो बन गए!"
            ),
            whatIfPonderingEn = "What if we hear a smoke alarm or fire bell? What should we do first?",
            whatIfPonderingHi = "सोचो अगर कभी अलार्म बजे तो सबसे पहले बड़ों के पास कैसे जाना चाहिए?"
        ),

        "chef_rohan" to KidCharacter(
            id = "chef_rohan",
            name = "Chef Rohan",
            profession = ProfessionType.CHEF,
            roleTitleEn = "Master of Delicious Treats",
            roleTitleHi = "शेफ रोहन",
            personality = "Passionate, jolly, sings little rhymes while stirring bowls",
            emojiAvatar = "👨‍🍳",
            greetingEn = "Welcome to the kitchen of smiles! Let's whip up something healthy, fresh, and colorful!",
            greetingHi = "खुशियों की रसोई में स्वागत है! चलो स्वादिष्ट और सेहतमंद खाना बनाएँ!",
            encouragingPhrasesEn = listOf(
                "A pinch of love makes every meal taste magical!",
                "Look at those beautiful rainbow fruit colors!",
                "Healthy food gives us strong wings to run and play!"
            ),
            encouragingPhrasesHi = listOf(
                "प्यार से बनाया खाना सबसे स्वादिष्ट होता है!",
                "देखो कितने सुंदर रंग हैं फलों के!",
                "अच्छा खाना हमें खेलने की ताकत देता है!"
            ),
            wrongChoiceGuidanceEn = listOf(
                "Ooh, that doesn't go in a salad bowl! What can we munch on?",
                "Look for something sweet, ripe, and grown on trees!",
                "Let's find the fruit that is bright and yummy!"
            ),
            wrongChoiceGuidanceHi = listOf(
                "अरे, इसे तो खाया नहीं जाता! मीठा फल ढूँढो।",
                "पेड़ पर उगने वाली ताज़ा चीज़ देखो!",
                "स्वादिष्ट और सेहतमंद फल चुनो!"
            ),
            celebrationPhrasesEn = listOf(
                "Magnifique! Smells heavenly and looks like art!",
                "You made the perfect rainbow snack! Yummy!",
                "Chef's kiss! You have the touch of a master chef!"
            ),
            celebrationPhrasesHi = listOf(
                "अरे वाह! कितना स्वादिष्ट और सुंदर नाश्ता तैयार किया!",
                "मज़ा आ गया! सबको बहुत पसंद आएगा!",
                "आप तो असली शेफ बन गए!"
            ),
            whatIfPonderingEn = "What if we wash all vegetables before cooking?",
            whatIfPonderingHi = "सब्जियाँ धोने से पेट कैसे सेहतमंद रहता है?"
        ),

        "mechanic_meera" to KidCharacter(
            id = "mechanic_meera",
            name = "Mechanic Meera",
            profession = ProfessionType.MECHANIC,
            roleTitleEn = "Clever Tinkerer & Fixer",
            roleTitleHi = "मैकेनिक मीरा",
            personality = "Curious, loves solving puzzles, observant, always smiling",
            emojiAvatar = "👩‍🔧",
            greetingEn = "Hey there, fix-it friend! Every little squeak has an answer. Let's find out how things move!",
            greetingHi = "नमस्ते साथी! हर पहेली का हल होता है, चलो पहियों को ठीक करें!",
            encouragingPhrasesEn = listOf(
                "You have a real tinkerer's eye!",
                "Turning bolts is just like solving a fun puzzle!",
                "Round and round things go!"
            ),
            encouragingPhrasesHi = listOf(
                "आप बहुत समझदारी से चीज़ें जोड़ते हैं!",
                "पहिया घुमाना कितना मजेदार है!",
                "शाबाश, बहुत अच्छा काम कर रहे हो!"
            ),
            wrongChoiceGuidanceEn = listOf(
                "That's a cozy item, but we need a sturdy metal tool to tighten nuts!",
                "Look for something that can grip and turn bolts!",
                "Which tool looks like a little shiny metal jaw?"
            ),
            wrongChoiceGuidanceHi = listOf(
                "यह तो मुलायम है, हमें नट कसने वाला मजबूत औज़ार चाहिए!",
                "घूमाने वाला औज़ार ढूँढो!",
                "रेंच कहाँ छुपा है?"
            ),
            celebrationPhrasesEn = listOf(
                "Listen to that smooth roll! Zero squeaks!",
                "Tight, secure, and ready for a ride!",
                "You're a brilliant little mechanic!"
            ),
            celebrationPhrasesHi = listOf(
                "देखो कितना सीधा और तेज़ चल रहा है!",
                "साइकिल बिल्कुल नई जैसी हो गई!",
                "आप बहुत अच्छे मैकेनिक हैं!"
            ),
            whatIfPonderingEn = "What if wheels were triangles instead of circles?",
            whatIfPonderingHi = "सोचो अगर पहिया गोल ना होकर तिकोना होता तो क्या होता?"
        )
    )

    fun getCharacterForProfession(profession: ProfessionType): KidCharacter {
        return when (profession) {
            ProfessionType.DOCTOR -> characters["dr_anya"]!!
            ProfessionType.FARMER -> characters["farmer_kabir"]!!
            ProfessionType.FIREFIGHTER -> characters["firefighter_tara"]!!
            ProfessionType.CHEF -> characters["chef_rohan"]!!
            ProfessionType.MECHANIC -> characters["mechanic_meera"]!!
            else -> characters["dr_anya"]!!
        }
    }
}
