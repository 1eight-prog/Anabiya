package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.KidAudioEngine
import com.example.data.AppDatabase
import com.example.data.ChildProfile
import com.example.data.ChildRepository
import com.example.data.CompletedMission
import com.example.data.ParentSettings
import com.example.data.TownProgression
import com.example.model.CharacterBible
import com.example.model.KidCharacter
import com.example.model.Mission
import com.example.model.MissionCatalog
import com.example.model.ProfessionType
import com.example.model.ToolItem
import com.example.model.TownLocation
import com.example.model.TownWeather
import com.example.model.TownWorld
import com.example.model.WhatIfChoice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    SPLASH,
    PROFILE_SETUP,
    TOWN_MAP,
    LOCATION_OVERVIEW,
    MISSION_PLAY,
    WHAT_IF_PLAY,
    FREE_EXPLORE,
    PARENT_GATE,
    PARENT_DASHBOARD
}

enum class MissionPhase {
    PROBLEM_DISCOVERY,
    TOOL_SELECTION,
    ACTION_IN_PROGRESS,
    CONSEQUENCE,
    CELEBRATION
}

enum class CharacterExpression {
    HAPPY,
    THINKING,
    CHEERING,
    LOVING
}

class MainGameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChildRepository
    val audioEngine = KidAudioEngine(application)

    private val _currentScreen = MutableStateFlow(AppScreen.SPLASH)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedLocation = MutableStateFlow<TownLocation>(TownWorld.locations.first())
    val selectedLocation: StateFlow<TownLocation> = _selectedLocation.asStateFlow()

    private val _currentMission = MutableStateFlow<Mission>(MissionCatalog.allMissions.first())
    val currentMission: StateFlow<Mission> = _currentMission.asStateFlow()

    private val _missionPhase = MutableStateFlow(MissionPhase.PROBLEM_DISCOVERY)
    val missionPhase: StateFlow<MissionPhase> = _missionPhase.asStateFlow()

    private val _currentWeather = MutableStateFlow(TownWeather.SUNNY)
    val currentWeather: StateFlow<TownWeather> = _currentWeather.asStateFlow()

    private val _characterExpression = MutableStateFlow(CharacterExpression.HAPPY)
    val characterExpression: StateFlow<CharacterExpression> = _characterExpression.asStateFlow()

    private val _spokenNarration = MutableStateFlow("")
    val spokenNarration: StateFlow<String> = _spokenNarration.asStateFlow()

    private val _selectedWhatIf = MutableStateFlow<WhatIfChoice?>(null)
    val selectedWhatIf: StateFlow<WhatIfChoice?> = _selectedWhatIf.asStateFlow()

    private val _wrongAttemptCount = MutableStateFlow(0)
    val wrongAttemptCount: StateFlow<Int> = _wrongAttemptCount.asStateFlow()

    private val _actionAnimationActive = MutableStateFlow(false)
    val actionAnimationActive: StateFlow<Boolean> = _actionAnimationActive.asStateFlow()

    // Navigation back stack for safe toddler exploration
    private val screenStack = mutableListOf<AppScreen>()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = ChildRepository(database.childDao())

        viewModelScope.launch {
            repository.initializeDefaultDataIfEmpty()
        }
    }

    val childProfile: StateFlow<ChildProfile?> = repository.childProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val completedMissions: StateFlow<List<CompletedMission>> = repository.completedMissions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val townProgress: StateFlow<List<TownProgression>> = repository.townProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val parentSettings: StateFlow<ParentSettings?> = repository.parentSettings
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ParentSettings())

    fun navigateTo(screen: AppScreen, clearStack: Boolean = false) {
        if (clearStack) {
            screenStack.clear()
        } else {
            screenStack.add(_currentScreen.value)
        }
        _currentScreen.value = screen
        audioEngine.playTap()
    }

    fun navigateBack(): Boolean {
        if (screenStack.isNotEmpty()) {
            val prev = screenStack.removeAt(screenStack.size - 1)
            _currentScreen.value = prev
            audioEngine.playTap()
            return true
        }
        if (_currentScreen.value != AppScreen.TOWN_MAP && _currentScreen.value != AppScreen.SPLASH) {
            _currentScreen.value = AppScreen.TOWN_MAP
            return true
        }
        return false
    }

    fun selectWeather(weather: TownWeather) {
        _currentWeather.value = weather
        audioEngine.playSparkle()
        val speech = when (weather) {
            TownWeather.SUNNY -> if (isHindi()) "सूरज चमक रहा है! कितना सुंदर दिन है!" else "The sun is shining bright and warm!"
            TownWeather.RAINY -> if (isHindi()) "छम-छम बारिश की बूँदें गिर रही हैं!" else "Gentle rain is falling! Pitter-patter!"
            TownWeather.EVENING -> if (isHindi()) "शाम हो गई है! आसमान सुनहरा है!" else "Golden evening in Little Town!"
            TownWeather.NIGHT -> if (isHindi()) "तारे चमक रहे हैं! शांत रात!" else "Twinkling stars in the quiet night sky!"
        }
        speakText(speech)
    }

    fun enterLocation(location: TownLocation) {
        _selectedLocation.value = location
        val missions = MissionCatalog.getMissionsForProfession(location.profession, childProfile.value?.developmentalStage)
        if (missions.isNotEmpty()) {
            _currentMission.value = missions.first()
        }
        val char = CharacterBible.getCharacterForProfession(location.profession)
        val greeting = if (isHindi()) char.greetingHi else char.greetingEn
        _spokenNarration.value = greeting
        _characterExpression.value = CharacterExpression.HAPPY
        speakText(greeting)
        navigateTo(AppScreen.LOCATION_OVERVIEW)
    }

    fun startMission(mission: Mission) {
        _currentMission.value = mission
        _missionPhase.value = MissionPhase.PROBLEM_DISCOVERY
        _wrongAttemptCount.value = 0
        _actionAnimationActive.value = false
        _selectedWhatIf.value = null
        _characterExpression.value = CharacterExpression.THINKING

        val problemText = if (isHindi()) mission.problemNarrationHi else mission.problemNarrationEn
        _spokenNarration.value = problemText
        speakText(problemText)
        navigateTo(AppScreen.MISSION_PLAY)
    }

    fun proceedToToolSelection() {
        _missionPhase.value = MissionPhase.TOOL_SELECTION
        audioEngine.playTap()
        val char = CharacterBible.getCharacterForProfession(_currentMission.value.profession)
        val prompt = if (isHindi()) "कौन सा औज़ार इस्तेमाल करें?" else "Which helpful tool should we pick?"
        _spokenNarration.value = prompt
        speakText(prompt)
    }

    fun onToolSelected(tool: ToolItem) {
        val mission = _currentMission.value
        val char = CharacterBible.getCharacterForProfession(mission.profession)

        if (tool.isCorrect) {
            audioEngine.playSparkle()
            _characterExpression.value = CharacterExpression.CHEERING
            val feedback = if (isHindi()) tool.gentleFeedbackHi else tool.gentleFeedbackEn
            _spokenNarration.value = feedback
            speakText(feedback)

            // Trigger action animation
            _missionPhase.value = MissionPhase.ACTION_IN_PROGRESS
            _actionAnimationActive.value = true

            // Trigger special sound effect based on profession
            when (mission.profession) {
                ProfessionType.DOCTOR -> audioEngine.playHeartbeat()
                ProfessionType.FARMER -> audioEngine.playWaterDrop()
                ProfessionType.FIREFIGHTER -> audioEngine.playSirenChirp()
                else -> audioEngine.playSparkle()
            }
        } else {
            // Non-punitive, gentle mistake response
            audioEngine.playCuriousHmm()
            _wrongAttemptCount.value = _wrongAttemptCount.value + 1
            _characterExpression.value = CharacterExpression.THINKING
            val feedback = if (isHindi()) tool.gentleFeedbackHi else tool.gentleFeedbackEn
            _spokenNarration.value = feedback
            speakText(feedback)
        }
    }

    fun completeActionAndShowConsequence() {
        val mission = _currentMission.value
        _missionPhase.value = MissionPhase.CONSEQUENCE
        _actionAnimationActive.value = false
        _characterExpression.value = CharacterExpression.HAPPY

        val consequenceText = if (isHindi()) mission.consequenceNarrationHi else mission.consequenceNarrationEn
        _spokenNarration.value = consequenceText
        speakText(consequenceText)
    }

    fun triggerCelebration() {
        val mission = _currentMission.value
        _missionPhase.value = MissionPhase.CELEBRATION
        _characterExpression.value = CharacterExpression.CHEERING
        audioEngine.playCelebration()

        val celebrationText = if (isHindi()) mission.celebrationNarrationHi else mission.celebrationNarrationEn
        _spokenNarration.value = celebrationText
        speakText(celebrationText)

        viewModelScope.launch {
            repository.completeMission(
                professionId = mission.profession.id,
                missionId = mission.id,
                stage = mission.stage,
                skill = mission.learningSkill,
                growthReward = 1
            )
        }
    }

    fun openWhatIfMode() {
        val mission = _currentMission.value
        _selectedWhatIf.value = null
        val question = if (isHindi()) mission.whatIfQuestionHi else mission.whatIfQuestionEn
        _spokenNarration.value = question
        speakText(question)
        navigateTo(AppScreen.WHAT_IF_PLAY)
    }

    fun onWhatIfChoiceSelected(choice: WhatIfChoice) {
        _selectedWhatIf.value = choice
        audioEngine.playSparkle()
        val outcome = if (isHindi()) choice.outcomeHi else choice.outcomeEn
        _spokenNarration.value = outcome
        speakText(outcome)
    }

    fun startFreeExplore(location: TownLocation) {
        _selectedLocation.value = location
        _characterExpression.value = CharacterExpression.HAPPY
        val char = CharacterBible.getCharacterForProfession(location.profession)
        val welcome = if (isHindi()) "यहाँ आप जो चाहें छूकर देख सकते हैं!" else "Feel free to explore and tap anything you like!"
        _spokenNarration.value = welcome
        speakText(welcome)
        navigateTo(AppScreen.FREE_EXPLORE)
    }

    fun updateChildProfile(name: String, avatarId: String, stage: String) {
        viewModelScope.launch {
            val current = childProfile.value ?: ChildProfile()
            repository.saveProfile(
                current.copy(
                    name = name.ifBlank { "Aarav" },
                    avatarId = avatarId,
                    developmentalStage = stage
                )
            )
            audioEngine.playCelebration()
            val thankYou = if (isHindi()) "नमस्ते $name! चलो लिटिल टाउन चलें!" else "Welcome $name! Let's explore Little Town!"
            speakText(thankYou)
            navigateTo(AppScreen.TOWN_MAP)
        }
    }

    fun updateParentSettings(
        voiceEnabled: Boolean,
        soundEffectsEnabled: Boolean,
        languageCode: String,
        sessionTimerMinutes: Int
    ) {
        viewModelScope.launch {
            val current = parentSettings.value ?: ParentSettings()
            val updated = current.copy(
                voiceNarrationEnabled = voiceEnabled,
                soundEffectsEnabled = soundEffectsEnabled,
                languageCode = languageCode,
                sessionTimerMinutes = sessionTimerMinutes
            )
            repository.saveParentSettings(updated)
            audioEngine.voiceEnabled = voiceEnabled
            audioEngine.soundEffectsEnabled = soundEffectsEnabled
            audioEngine.setLanguage(languageCode)
            audioEngine.playSparkle()
        }
    }

    fun speakText(text: String) {
        val currentSettings = parentSettings.value ?: ParentSettings()
        if (currentSettings.voiceNarrationEnabled) {
            audioEngine.speak(text)
        }
    }

    fun isHindi(): Boolean {
        return (parentSettings.value?.languageCode ?: "en") == "hi"
    }

    override fun onCleared() {
        super.onCleared()
        audioEngine.release()
    }
}
