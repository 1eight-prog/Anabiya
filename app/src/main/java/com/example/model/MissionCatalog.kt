package com.example.model

object MissionCatalog {

    val allMissions: List<Mission> = listOf(
        // --- 1. DOCTOR MISSIONS ---
        Mission(
            id = "doc_m1_heartbeat",
            profession = ProfessionType.DOCTOR,
            stage = "A",
            titleEn = "Listen to Teddy's Heartbeat",
            titleHi = "टेडी की धड़कन सुनो",
            problemNarrationEn = "Teddy has a little sniffle today. Dr. Anya asks: 'Can you help me listen to Teddy's chest?'",
            problemNarrationHi = "टेडी को हल्की छींक आ रही है। डॉक्टर अन्या पूछती हैं: 'क्या हम टेडी की छाती सुन सकते हैं?'",
            characterEmoji = "👩‍⚕️",
            subjectEmoji = "🧸",
            toolChoices = listOf(
                ToolItem(
                    id = "stethoscope",
                    nameEn = "Stethoscope",
                    nameHi = "स्टेथोस्कोप",
                    iconEmoji = "🩺",
                    isCorrect = true,
                    gentleFeedbackEn = "Yes! That listens to heartbeats! Lub-dub, lub-dub!",
                    gentleFeedbackHi = "शाबाश! इससे दिल की धड़कन सुनते हैं!",
                    color = 0xFFB3E5FC
                ),
                ToolItem(
                    id = "flower",
                    nameEn = "Pretty Flower",
                    nameHi = "फूल",
                    iconEmoji = "🌸",
                    isCorrect = false,
                    gentleFeedbackEn = "A flower smells lovely! But let's find the listening tool with the soft round ear-tips.",
                    gentleFeedbackHi = "फूल बहुत सुंदर है, पर सुनने वाला औज़ार कौन सा है?",
                    color = 0xFFF8BBD0
                )
            ),
            actionVerbEn = "Tap Stethoscope on Teddy's chest",
            actionVerbHi = "स्टेथोस्कोप से टेडी की छाती छुओ",
            consequenceNarrationEn = "Lub-dub, lub-dub! Teddy's heart is beating steady and strong! Teddy giggles happily!",
            consequenceNarrationHi = "धक-धक! टेडी का दिल बिल्कुल स्वस्थ है! टेडी मुस्कुराने लगा!",
            celebrationNarrationEn = "Wonderful job, Little Doctor! Teddy feels cared for and happy!",
            celebrationNarrationHi = "बहुत बढ़िया छोटे डॉक्टर! टेडी बहुत खुश है!",
            whatIfQuestionEn = "What if Teddy wants to feel extra cozy and rest now?",
            whatIfQuestionHi = "अगर टेडी आराम करना चाहे, तो उसे क्या दें?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "blanket",
                    textEn = "Soft Warm Blanket",
                    textHi = "मुलायम कंबल",
                    emoji = "🛏️",
                    outcomeEn = "Teddy snuggles under the warm blanket and smiles peacefully!",
                    outcomeHi = "टेडी गरम कंबल ओढ़कर प्यार से सो गया!"
                ),
                WhatIfChoice(
                    id = "water",
                    textEn = "Warm Sip of Water",
                    textHi = "गुनगुना पानी",
                    emoji = "🥛",
                    outcomeEn = "Teddy takes a tiny sip and says thank you!",
                    outcomeHi = "टेडी ने पानी पिया और धन्यवाद कहा!"
                )
            ),
            learningSkill = "Empathy & Body Awareness"
        ),
        Mission(
            id = "doc_m2_bandage",
            profession = ProfessionType.DOCTOR,
            stage = "B",
            titleEn = "Gentle Bandage for Bunny's Knee",
            titleHi = "खरगोश के घुटने पर पट्टी",
            problemNarrationEn = "Bunny was hopping in the meadow and got a tiny scrape. 'It tingles a little,' Bunny whispers.",
            problemNarrationHi = "बन्नी घास में कूद रहा था और घुटने पर हल्की खरोंच आ गई। 'हल्का सा दर्द है,' बन्नी ने कहा।",
            characterEmoji = "👩‍⚕️",
            subjectEmoji = "🐰",
            toolChoices = listOf(
                ToolItem(
                    id = "bandage",
                    nameEn = "Cute Bandage",
                    nameHi = "प्यारी पट्टी",
                    iconEmoji = "🩹",
                    isCorrect = true,
                    gentleFeedbackEn = "A clean colorful bandage protects the tiny scrape!",
                    gentleFeedbackHi = "हाँ! यह पट्टी खरोंच को सुरक्षित रखेगी!",
                    color = 0xFFFFCCBC
                ),
                ToolItem(
                    id = "hammer",
                    nameEn = "Toy Hammer",
                    nameHi = "खिलौना हथौड़ा",
                    iconEmoji = "🔨",
                    isCorrect = false,
                    gentleFeedbackEn = "A hammer is for building blocks! For a little knee scrape, we need something soft to cover it.",
                    gentleFeedbackHi = "हथौड़ा तो खिलौनों के लिए है! घुटने के लिए हमें मुलायम पट्टी चाहिए।",
                    color = 0xFFFFE082
                ),
                ToolItem(
                    id = "apple",
                    nameEn = "Juicy Apple",
                    nameHi = "सेब",
                    iconEmoji = "🍎",
                    isCorrect = false,
                    gentleFeedbackEn = "An apple is yummy to eat! But what can we place softly on Bunny's knee?",
                    gentleFeedbackHi = "सेब तो खाने के लिए है! बन्नी के घुटने पर क्या चिपकाएँ?",
                    color = 0xFFFFCDD2
                )
            ),
            actionVerbEn = "Gently apply the bandage",
            actionVerbHi = "प्यार से पट्टी लगाओ",
            consequenceNarrationEn = "The cute star bandage covers Bunny's knee. 'It feels all better!' says Bunny with a little hop!",
            consequenceNarrationHi = "तारे वाली पट्टी लग गई! बन्नी खुशी से कूदने लगा!",
            celebrationNarrationEn = "You are so gentle and kind! Bunny gave you a high five!",
            celebrationNarrationHi = "आप बहुत दयालु हैं! बन्नी ने आपको नमस्ते किया!",
            whatIfQuestionEn = "What helps tiny scrapes heal naturally?",
            whatIfQuestionHi = "चोट जल्दी ठीक होने में क्या मदद करता है?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "rest",
                    textEn = "Gentle Rest & Hug",
                    textHi = "आराम और प्यार",
                    emoji = "🤗",
                    outcomeEn = "Bunny's mom gives a gentle hug. Resting helps bodies heal!",
                    outcomeHi = "प्यार और आराम से बन्नी जल्दी ठीक हो गया!"
                ),
                WhatIfChoice(
                    id = "clean",
                    textEn = "Washing with Clean Water",
                    textHi = "साफ़ पानी से धोना",
                    emoji = "💧",
                    outcomeEn = "Keeping it clean keeps dirt away! Great thinking!",
                    outcomeHi = "साफ़ रखने से कीटाणु दूर रहते हैं!"
                )
            ),
            learningSkill = "Caregiving & Hygiene"
        ),

        // --- 2. FARMER MISSIONS ---
        Mission(
            id = "farm_m1_thirsty_sunflower",
            profession = ProfessionType.FARMER,
            stage = "A",
            titleEn = "Help Thirsty Sunflower",
            titleHi = "प्यासे सूरजमुखी की मदद",
            problemNarrationEn = "The sun is shining bright! Farmer Kabir looks at the little green sprout: 'Our sunflower is thirsty!'",
            problemNarrationHi = "सूरज चमक रहा है! किसान कबीर कहते हैं: 'हमारा सूरजमुखी का पौधा प्यासा है!'",
            characterEmoji = "👨‍🌾",
            subjectEmoji = "🌱",
            toolChoices = listOf(
                ToolItem(
                    id = "watering_can",
                    nameEn = "Blue Watering Can",
                    nameHi = "पानी का फव्वारा",
                    iconEmoji = "💧",
                    isCorrect = true,
                    gentleFeedbackEn = "Yes! Plants love cool refreshing water droplets!",
                    gentleFeedbackHi = "शाबाश! पौधों को पानी बहुत पसंद है!",
                    color = 0xFF81D4FA
                ),
                ToolItem(
                    id = "whistle",
                    nameEn = "Loud Whistle",
                    nameHi = "सीटी",
                    iconEmoji = "📢",
                    isCorrect = false,
                    gentleFeedbackEn = "A whistle makes noise, but plants need a gentle drink. Which tool pours water?",
                    gentleFeedbackHi = "सीटी तो आवाज़ करती है, पौधे को तो पानी पीना है!",
                    color = 0xFFFFD54F
                )
            ),
            actionVerbEn = "Sprinkle water on the sprout",
            actionVerbHi = "पौधे पर पानी छिड़को",
            consequenceNarrationEn = "Pitter-patter water drops! The little sprout stretches up, buds open, and a bright yellow sunflower blooms smiling!",
            consequenceNarrationHi = "छम-छम पानी की बूँदें! नन्हा पौधा खिलकर बड़ा पीला सूरजमुखी बन गया!",
            celebrationNarrationEn = "Look at that golden flower! Farmer Kabir claps his hands: 'You helped it grow!'",
            celebrationNarrationHi = "देखो कितना सुंदर फूल खिला! किसान कबीर बोले: 'कमाल कर दिया!'",
            whatIfQuestionEn = "What if the sunflower also gets gentle morning sunlight?",
            whatIfQuestionHi = "सूरजमुखी को धूप मिले तो क्या होगा?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "bees",
                    textEn = "Friendly Bees & Butterflies Visit",
                    textHi = "तितलियाँ और भौंरे आएंगे",
                    emoji = "🦋",
                    outcomeEn = "Golden bees and colorful butterflies flutter around to drink sweet nectar!",
                    outcomeHi = "रंग-बिरंगी तितलियाँ मीठा रस पीने आ गईं!"
                ),
                WhatIfChoice(
                    id = "seeds",
                    textEn = "Produces Healthy Seeds",
                    textHi = "नए बीज बनेंगे",
                    emoji = "🌻",
                    outcomeEn = "The sunflower center fills with crunchy seeds for birds to snack on!",
                    outcomeHi = "चिड़ियों के लिए ढेर सारे बीज बन गए!"
                )
            ),
            learningSkill = "Nature & Cause-Effect"
        ),
        Mission(
            id = "farm_m2_cow_snack",
            profession = ProfessionType.FARMER,
            stage = "B",
            titleEn = "Fresh Green Grass for Daisy",
            titleHi = "गाय डेज़ी का ताज़ा नाश्ता",
            problemNarrationEn = "Daisy the friendly cow is in the barn. 'Mooo!' says Daisy. 'Daisy loves a crisp healthy breakfast,' says Farmer Kabir.",
            problemNarrationHi = "डेज़ी गाय बाड़े में है। 'भाँऽऽ!' किसान कबीर कहते हैं: 'डेज़ी को नाश्ता चाहिए!'",
            characterEmoji = "👨‍🌾",
            subjectEmoji = "🐮",
            toolChoices = listOf(
                ToolItem(
                    id = "fresh_grass",
                    nameEn = "Green Clover Grass",
                    nameHi = "हरी ताज़ा घास",
                    iconEmoji = "🌾",
                    isCorrect = true,
                    gentleFeedbackEn = "Daisy's favorite! Sweet, crisp green grass!",
                    gentleFeedbackHi = "हाँ! गाय को हरी ताज़ा घास बहुत पसंद है!",
                    color = 0xFFC8E6C9
                ),
                ToolItem(
                    id = "candy",
                    nameEn = "Sugary Lollipop",
                    nameHi = "लॉलीपॉप",
                    iconEmoji = "🍭",
                    isCorrect = false,
                    gentleFeedbackEn = "Candy isn't good for cow tummies. Cows love natural plants grown in the field!",
                    gentleFeedbackHi = "टॉफी गाय के पेट के लिए अच्छी नहीं होती! गाय घास खाती है।",
                    color = 0xFFFFCDD2
                ),
                ToolItem(
                    id = "shoe",
                    nameEn = "Muddy Boot",
                    nameHi = "जूता",
                    iconEmoji = "👢",
                    isCorrect = false,
                    gentleFeedbackEn = "Boots are for our feet, not for eating! What does Daisy munch on?",
                    gentleFeedbackHi = "जूता तो पैरों के लिए है! खाने की चीज़ ढूँढो।",
                    color = 0xFFD7CCC8
                )
            ),
            actionVerbEn = "Feed Daisy the fresh grass",
            actionVerbHi = "डेज़ी को घास खिलाओ",
            consequenceNarrationEn = "Munch, crunch! Daisy wags her tail and gives a happy 'Mooo!' She feels full and contented.",
            consequenceNarrationHi = "डेज़ी ने मजे से हरी घास खाई और पूंछ हिलाकर प्यार जताया!",
            celebrationNarrationEn = "You are a wonderful friend to animals! Daisy rubs her soft nose against your hand.",
            celebrationNarrationHi = "आप जानवरों के बहुत अच्छे दोस्त हैं!",
            whatIfQuestionEn = "Where does healthy fresh milk come from?",
            whatIfQuestionHi = "ताज़ा दूध कहाँ से मिलता है?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "happy_cows",
                    textEn = "Well-fed, Happy Cows",
                    textHi = "खुश और स्वस्थ गायों से",
                    emoji = "🥛",
                    outcomeEn = "When cows are loved and eat fresh grass, they share delicious milk!",
                    outcomeHi = "हरी घास खाने से गाय अच्छा दूध देती है!"
                ),
                WhatIfChoice(
                    id = "water_trough",
                    textEn = "Drinking Plenty of Clean Water",
                    textHi = "साफ़ पानी पीने से",
                    emoji = "🥣",
                    outcomeEn = "Daisy drinks cool clean water from her trough to stay refreshed!",
                    outcomeHi = "पानी पीने से गाय तरोताज़ा रहती है!"
                )
            ),
            learningSkill = "Animal Care & Nutrition"
        ),

        // --- 3. FIREFIGHTER MISSIONS ---
        Mission(
            id = "fire_m1_kitten_rescue",
            profession = ProfessionType.FIREFIGHTER,
            stage = "A",
            titleEn = "Help Fluffy Kitten Down",
            titleHi = "पेड़ पर फँसी बिल्ली की मदद",
            problemNarrationEn = "Fluffy kitten climbed up the tall tree branch and is scared to jump down. 'Meow!' Firefighter Tara says: 'How can we reach up safely?'",
            problemNarrationHi = "नन्ही बिल्ली पेड़ की डाल पर चढ़ गई और उतर नहीं पा रही। 'म्याऊँ!' फायरफाइटर तारा कहती हैं: 'ऊपर कैसे पहुँचें?'",
            characterEmoji = "👩‍🚒",
            subjectEmoji = "🐱",
            toolChoices = listOf(
                ToolItem(
                    id = "ladder",
                    nameEn = "Extendable Fire Ladder",
                    nameHi = "लंबी सीढ़ी",
                    iconEmoji = "🪜",
                    isCorrect = true,
                    gentleFeedbackEn = "Yes! The fire ladder stretches high into the tree branches safely!",
                    gentleFeedbackHi = "बिल्कुल सही! सीढ़ी से हम सुरक्षित ऊपर पहुँच सकते हैं!",
                    color = 0xFFFFCCBC
                ),
                ToolItem(
                    id = "drum",
                    nameEn = "Loud Drum",
                    nameHi = "ढोलक",
                    iconEmoji = "🥁",
                    isCorrect = false,
                    gentleFeedbackEn = "A loud drum might startle our kitten. We need something tall to climb up gently.",
                    gentleFeedbackHi = "ढोलक की आवाज़ से बिल्ली डर सकती है। चढ़ने वाली चीज़ ढूँढो।",
                    color = 0xFFFFF59D
                )
            ),
            actionVerbEn = "Raise the ladder gently",
            actionVerbHi = "सीढ़ी को पेड़ तक लगाओ",
            consequenceNarrationEn = "Climb, climb, climb! Firefighter Tara safely scoops Fluffy kitten into a soft warm towel and carries her down!",
            consequenceNarrationHi = "तारा ने सीढ़ी पर चढ़कर बिल्ली को प्यार से गोद में ले लिया और नीचे ले आईं!",
            celebrationNarrationEn = "Purr, purr! Fluffy is safe and sound! Blaze the fire truck beeps its friendly horn: 'Beep-beep!'",
            celebrationNarrationHi = "बिल्ली खुश हो गई! दमकल गाड़ी ने खुशी से हॉर्न बजाया: 'पों-पों!'",
            whatIfQuestionEn = "What helps firefighters see in the dark at night?",
            whatIfQuestionHi = "रात के अंधेरे में फायरफाइटर कैसे देखते हैं?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "helmet_light",
                    textEn = "Bright Helmet Flashlight",
                    textHi = "हेलमेट की बत्ती",
                    emoji = "🔦",
                    outcomeEn = "Click! The bright beam shines ahead and shows every step clearly!",
                    outcomeHi = "टॉर्च की रोशनी से रास्ता साफ़ दिखने लगा!"
                ),
                WhatIfChoice(
                    id = "reflective_jacket",
                    textEn = "Shiny Reflective Strips",
                    textHi = "चमकदार जैकेट",
                    emoji = "🦺",
                    outcomeEn = "The glowing yellow strips sparkle so everyone can see each other!",
                    outcomeHi = "चमकती जैकेट से सब एक दूसरे को आसानी से देख लेते हैं!"
                )
            ),
            learningSkill = "Helping Others & Safety"
        ),
        Mission(
            id = "fire_m2_smoke_camp",
            profession = ProfessionType.FIREFIGHTER,
            stage = "B",
            titleEn = "Cool Down the Park Campfire",
            titleHi = "पार्क की आग पर पानी",
            problemNarrationEn = "Someone left a tiny campfire smoking in the park grass. Firefighter Tara says: 'Let's make sure it is completely cool and safe!'",
            problemNarrationHi = "पार्क में अलाव से धुआँ निकल रहा है। तारा कहती हैं: 'चलो इसे पूरी तरह ठंडा और सुरक्षित बनाएँ!'",
            characterEmoji = "👩‍🚒",
            subjectEmoji = "💨",
            toolChoices = listOf(
                ToolItem(
                    id = "fire_hose",
                    nameEn = "Water Fire Hose",
                    nameHi = "पानी का पाइप (होज़)",
                    iconEmoji = "🚿",
                    isCorrect = true,
                    gentleFeedbackEn = "Whoosh! Cool water quenches the heat safely!",
                    gentleFeedbackHi = "शाबाश! पानी से सब ठंडा और शांत हो जाएगा!",
                    color = 0xFF80DEEA
                ),
                ToolItem(
                    id = "fan",
                    nameEn = "Paper Fan",
                    nameHi = "हवा का पंखा",
                    iconEmoji = "🪭",
                    isCorrect = false,
                    gentleFeedbackEn = "Fanning air would blow sparks around! We want cool wet water to put it to sleep.",
                    gentleFeedbackHi = "हवा देने से तो चिंगारी उड़ सकती है! हमें ठंडा पानी चाहिए।",
                    color = 0xFFFFAB91
                ),
                ToolItem(
                    id = "leaves",
                    nameEn = "Dry Leaves",
                    nameHi = "सूखे पत्ते",
                    iconEmoji = "🍂",
                    isCorrect = false,
                    gentleFeedbackEn = "Dry leaves catch heat! We need cold water to make the ground cool.",
                    gentleFeedbackHi = "सूखे पत्ते नहीं, ठंडा पानी चाहिए!",
                    color = 0xFFFFE0B2
                )
            ),
            actionVerbEn = "Spray cool water mist",
            actionVerbHi = "ठंडे पानी का फव्वारा मारो",
            consequenceNarrationEn = "Ssssssh! White steam gently floats away. The embers are completely cool, dark, and safe for all park birds!",
            consequenceNarrationHi = "छन्न! धुआँ शांत हो गया और ज़मीन बिल्कुल ठंडी और सुरक्षित हो गई!",
            celebrationNarrationEn = "The park is peaceful and safe! The town birds chirp happily from the trees!",
            celebrationNarrationHi = "पार्क सुरक्षित हो गया! सभी चिड़ियाँ खुशी से चहकने लगीं!",
            whatIfQuestionEn = "What is the emergency number to call friendly helpers in India?",
            whatIfQuestionHi = "मदद के लिए कौन सा नंबर याद रखना चाहिए?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "call_112",
                    textEn = "Dial 1-1-2 / 1-0-1 with a Grown-up",
                    textHi = "बड़ों के साथ 112 या 101 मिलाओ",
                    emoji = "📞",
                    outcomeEn = "A kind operator answers: 'Hello, help is on the way!'",
                    outcomeHi = "ऑपरेटर ने कहा: 'मदद तुरंत पहुँच रही है!'"
                ),
                WhatIfChoice(
                    id = "tell_grownup",
                    textEn = "Immediately Tell a Trusted Grown-up",
                    textHi = "तुरंत मम्मी-पापा या बड़ों को बताओ",
                    emoji = "👨‍👩‍👧",
                    outcomeEn = "Smart choice! Always tell an adult nearby right away!",
                    outcomeHi = "शाबाश! बड़ों को बताना सबसे समझदारी है!"
                )
            ),
            learningSkill = "Fire Safety & Emergency Basics"
        ),

        // --- 4. CHEF MISSIONS ---
        Mission(
            id = "chef_m1_fruit_salad",
            profession = ProfessionType.CHEF,
            stage = "A",
            titleEn = "Mix a Rainbow Fruit Bowl",
            titleHi = "इंद्रधनुषी फलों की कटोरी",
            problemNarrationEn = "Little Bear is hungry for a healthy afternoon snack. Chef Rohan says: 'Let's prepare a sweet, colorful fruit bowl together!'",
            problemNarrationHi = "छोटे भालू को भूख लगी है। शेफ रोहन कहते हैं: 'चलो मीठे फलों की कटोरी तैयार करें!'",
            characterEmoji = "👨‍🍳",
            subjectEmoji = "🥣",
            toolChoices = listOf(
                ToolItem(
                    id = "banana_slices",
                    nameEn = "Sweet Yellow Bananas",
                    nameHi = "मीठे केले के टुकड़े",
                    iconEmoji = "🍌",
                    isCorrect = true,
                    gentleFeedbackEn = "Yum! Ripe bananas are soft, sweet, and give playful energy!",
                    gentleFeedbackHi = "अरे वाह! केला बहुत मीठा और सेहतमंद होता है!",
                    color = 0xFFFFF59D
                ),
                ToolItem(
                    id = "rock",
                    nameEn = "Garden Stone",
                    nameHi = "पत्थर",
                    iconEmoji = "🪨",
                    isCorrect = false,
                    gentleFeedbackEn = "Stones belong in the garden trail! Let's choose delicious fruit for our bowl.",
                    gentleFeedbackHi = "पत्थर तो बगीचे में रहता है! खाने का फल चुनो।",
                    color = 0xFFCFD8DC
                )
            ),
            actionVerbEn = "Add banana slices to bowl",
            actionVerbHi = "केले के टुकड़े कटोरी में डालो",
            consequenceNarrationEn = "Plop, plop! Fresh strawberries and banana slices make a bright, yummy smile in the wooden bowl!",
            consequenceNarrationHi = "कटोरी में स्ट्रॉबेरी और केले सज गए! बहुत सुंदर नाश्ता तैयार है!",
            celebrationNarrationEn = "Little Bear rubs his tummy: 'Mmm, delicious!' Chef Rohan gives you a chef's hat badge!",
            celebrationNarrationHi = "भालू ने मजे से खाया और बोला 'मज़ा आ गया!'",
            whatIfQuestionEn = "Why do we wash fruits with clean water before eating?",
            whatIfQuestionHi = "फल खाने से पहले पानी से क्यों धोते हैं?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "wash_germs",
                    textEn = "Washes away Dust & Keeps Tummy Healthy",
                    textHi = "धूल और कीटाणु साफ़ करने के लिए",
                    emoji = "✨",
                    outcomeEn = "Spotless and clean! Now the fruit is safe and crisp to bite!",
                    outcomeHi = "साफ़ पानी से फल चमक गया और खाने में सुरक्षित हो गया!"
                ),
                WhatIfChoice(
                    id = "fresh_crunch",
                    textEn = "Makes Fruit Cool and Crisp",
                    textHi = "फल को ठंडा और ताज़ा रखने के लिए",
                    emoji = "💦",
                    outcomeEn = "Splish splash! Cool fruit tastes so refreshing on a sunny day!",
                    outcomeHi = "ठंडे पानी से फल और भी रसीला हो गया!"
                )
            ),
            learningSkill = "Healthy Eating & Hygiene"
        ),

        // --- 5. MECHANIC MISSIONS ---
        Mission(
            id = "mech_m1_tricycle_wheel",
            profession = ProfessionType.MECHANIC,
            stage = "A",
            titleEn = "Fix the Wobbly Tricycle Wheel",
            titleHi = "तीन पहियों वाली साइकिल का पहिया",
            problemNarrationEn = "Meera is looking at a little red tricycle. 'The round front wheel is loose and squeaks: creak-creak!'",
            problemNarrationHi = "मैकेनिक मीरा लाल साइकिल देख रही हैं। 'आगे का गोल पहिया ढीला है और आवाज़ कर रहा है!'",
            characterEmoji = "👩‍🔧",
            subjectEmoji = "🚲",
            toolChoices = listOf(
                ToolItem(
                    id = "wrench",
                    nameEn = "Friendly Wrench",
                    nameHi = "रेंच (पाना)",
                    iconEmoji = "🔧",
                    isCorrect = true,
                    gentleFeedbackEn = "Twist, twist! The wrench tightens the bolt safe and snug!",
                    gentleFeedbackHi = "शाबाश! रेंच से नट बिल्कुल ठीक कस गया!",
                    color = 0xFFB0BEC5
                ),
                ToolItem(
                    id = "pillow",
                    nameEn = "Fluffy Pillow",
                    nameHi = "तकिया",
                    iconEmoji = "🛋️",
                    isCorrect = false,
                    gentleFeedbackEn = "A pillow is great for napping, but which metal tool turns nuts and bolts?",
                    gentleFeedbackHi = "तकिया तो सोने के लिए है! नट कसने वाला औज़ार कौन सा है?",
                    color = 0xFFFFCCBC
                )
            ),
            actionVerbEn = "Gently tighten the bolt",
            actionVerbHi = "पहिए का नट कसो",
            consequenceNarrationEn = "Spin, spin, whoosh! The round wheel rolls smoothly without any wobble or squeak!",
            consequenceNarrationHi = "सरररर! पहिया बिल्कुल सीधा और तेज़ घूमने लगा!",
            celebrationNarrationEn = "Ready to ride safely! Mechanic Meera gives you a high five with her work gloves!",
            celebrationNarrationHi = "साइकिल चलने के लिए तैयार है! बहुत अच्छे!",
            whatIfQuestionEn = "What shape must wheels be to roll smoothly on the road?",
            whatIfQuestionHi = "पहिया कैसा होना चाहिए ताकि वह आसानी से घूमे?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "circle",
                    textEn = "A Perfect Round Circle ⭕",
                    textHi = "गोल-गोल वृत्त ⭕",
                    emoji = "⭕",
                    outcomeEn = "Roll, roll, roll! Round shapes roll forever without bumping!",
                    outcomeHi = "गोल पहिया बिना अटके आगे बढ़ता है!"
                ),
                WhatIfChoice(
                    id = "square",
                    textEn = "A Bumpy Square ⬛",
                    textHi = "चौकोर डिब्बा ⬛",
                    emoji = "⬛",
                    outcomeEn = "Thump, bump! A square gets stuck on its corners! Circles are best!",
                    outcomeHi = "चौकोर तो टकराकर रुक जाएगा! गोल ही सबसे अच्छा है!"
                )
            ),
            learningSkill = "Shapes & Mechanics Basics"
        ),

        // --- 6. TRAFFIC SAFETY MISSIONS ---
        Mission(
            id = "police_m1_zebra_crossing",
            profession = ProfessionType.POLICE,
            stage = "B",
            titleEn = "Safe Walk at Zebra Crossing",
            titleHi = "ज़ेबरा क्रॉसिंग पर सुरक्षित सैर",
            problemNarrationEn = "A family of ducks wants to cross to the town pond. Officer Vikram looks at the signal: 'What color light tells cars to stop?'",
            problemNarrationHi = "बत्तखों को सड़क पार करके तालाब जाना है। विक्रम पूछते हैं: 'गाड़ियों को रोकने वाली कौन सी बत्ती है?'",
            characterEmoji = "👮‍♂️",
            subjectEmoji = "🦆",
            toolChoices = listOf(
                ToolItem(
                    id = "red_light",
                    nameEn = "Bright Red Light",
                    nameHi = "लाल बत्ती (Red Light)",
                    iconEmoji = "🛑",
                    isCorrect = true,
                    gentleFeedbackEn = "Red says STOP! Cars pause gently behind the white stripes!",
                    gentleFeedbackHi = "लाल बत्ती कहती है रुको! गाड़ियाँ रुक गईं!",
                    color = 0xFFFFCDD2
                ),
                ToolItem(
                    id = "balloon",
                    nameEn = "Purple Balloon",
                    nameHi = "गुब्बारा",
                    iconEmoji = "🎈",
                    isCorrect = false,
                    gentleFeedbackEn = "Balloons float in the wind! Look at the three traffic colors: Red, Yellow, Green.",
                    gentleFeedbackHi = "गुब्बारा तो उड़ने के लिए है! ट्रैफिक लाइट का रंग चुनो।",
                    color = 0xFFE1BEE7
                ),
                ToolItem(
                    id = "ice_cream",
                    nameEn = "Ice Cream Cone",
                    nameHi = "आइसक्रीम",
                    iconEmoji = "🍦",
                    isCorrect = false,
                    gentleFeedbackEn = "Ice cream is a cold treat, but which signal tells vehicles to halt?",
                    gentleFeedbackHi = "आइसक्रीम तो खाने की है! रुकने का इशारा कौन सा है?",
                    color = 0xFFFFF9C4
                )
            ),
            actionVerbEn = "Switch signal to Red for cars",
            actionVerbHi = "गाड़ियों के लिए लाल बत्ती करो",
            consequenceNarrationEn = "The red light glows. The town bus stops smoothly. Quack, quack! The mama duck and five little ducklings waddle safely across the white zebra stripes!",
            consequenceNarrationHi = "लाल बत्ती जलते ही बस रुक गई। बत्तखों का पूरा परिवार सुरक्षित सड़क पार कर गया!",
            celebrationNarrationEn = "Splendid road safety! Officer Vikram salutes you with a big smile!",
            celebrationNarrationHi = "शाबाश! आपने सबको सुरक्षित रखा!",
            whatIfQuestionEn = "What does the GREEN light mean on the road?",
            whatIfQuestionHi = "सड़क पर हरी बत्ती का क्या मतलब होता है?",
            whatIfChoices = listOf(
                WhatIfChoice(
                    id = "green_go",
                    textEn = "Green means GO safely!",
                    textHi = "हरी बत्ती कहे: चलो आगे बढ़ो!",
                    emoji = "🟢",
                    outcomeEn = "Vroom! Now vehicles can move along calmly on the road!",
                    outcomeHi = "गाड़ियाँ आराम से आगे बढ़ सकती हैं!"
                ),
                WhatIfChoice(
                    id = "yellow_wait",
                    textEn = "Yellow means Slow Down & Get Ready!",
                    textHi = "पीली बत्ती कहे: धीरे हो जाओ!",
                    emoji = "🟡",
                    outcomeEn = "Careful and slow! Drivers prepare to stop smoothly.",
                    outcomeHi = "सावधानी से धीरे होना बहुत ज़रूरी है!"
                )
            ),
            learningSkill = "Road Safety & Colors"
        )
    )

    fun getMissionsForProfession(type: ProfessionType, stage: String? = null): List<Mission> {
        return allMissions.filter { mission ->
            mission.profession == type && (stage == null || mission.stage == stage || stage == "ALL")
        }
    }

    fun getMissionById(id: String): Mission? {
        return allMissions.find { it.id == id } ?: allMissions.firstOrNull()
    }
}
