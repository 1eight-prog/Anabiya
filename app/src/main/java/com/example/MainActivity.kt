package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ProfessionType
import com.example.ui.screens.FreeExploreScreen
import com.example.ui.screens.LocationOverviewScreen
import com.example.ui.screens.MissionPlayScreen
import com.example.ui.screens.ParentDashboardScreen
import com.example.ui.screens.ParentGateScreen
import com.example.ui.screens.ProfileSetupScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.TownMapScreen
import com.example.ui.screens.WhatIfScreen
import com.example.ui.theme.KidCreamBackground
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppScreen
import com.example.viewmodel.MainGameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MujheKaunBanegaApp()
            }
        }
    }
}

@Composable
fun MujheKaunBanegaApp(
    viewModel: MainGameViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val childProfile by viewModel.childProfile.collectAsStateWithLifecycle()
    val currentWeather by viewModel.currentWeather.collectAsStateWithLifecycle()
    val selectedLocation by viewModel.selectedLocation.collectAsStateWithLifecycle()
    val currentMission by viewModel.currentMission.collectAsStateWithLifecycle()
    val missionPhase by viewModel.missionPhase.collectAsStateWithLifecycle()
    val spokenNarration by viewModel.spokenNarration.collectAsStateWithLifecycle()
    val characterExpression by viewModel.characterExpression.collectAsStateWithLifecycle()
    val wrongAttemptCount by viewModel.wrongAttemptCount.collectAsStateWithLifecycle()
    val actionAnimationActive by viewModel.actionAnimationActive.collectAsStateWithLifecycle()
    val selectedWhatIf by viewModel.selectedWhatIf.collectAsStateWithLifecycle()
    val townProgress by viewModel.townProgress.collectAsStateWithLifecycle()
    val completedMissions by viewModel.completedMissions.collectAsStateWithLifecycle()
    val parentSettings by viewModel.parentSettings.collectAsStateWithLifecycle()

    val isVoiceEnabled = parentSettings?.voiceNarrationEnabled ?: true
    val isHindi = viewModel.isHindi()

    // Gentle BackHandler to keep toddlers safe in Little Town
    BackHandler(enabled = currentScreen != AppScreen.SPLASH) {
        if (!viewModel.navigateBack()) {
            if (currentScreen != AppScreen.TOWN_MAP) {
                viewModel.navigateTo(AppScreen.TOWN_MAP)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = KidCreamBackground
    ) {
        when (currentScreen) {
            AppScreen.SPLASH -> {
                SplashScreen(
                    onStartAdventureClick = {
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    },
                    onChooseProfileClick = {
                        viewModel.navigateTo(AppScreen.PROFILE_SETUP)
                    }
                )
            }

            AppScreen.PROFILE_SETUP -> {
                ProfileSetupScreen(
                    currentProfile = childProfile,
                    onSaveProfile = { name, avatarId, stage ->
                        viewModel.updateChildProfile(name, avatarId, stage)
                    },
                    onCancel = {
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    }
                )
            }

            AppScreen.TOWN_MAP -> {
                TownMapScreen(
                    childProfile = childProfile,
                    currentWeather = currentWeather,
                    onSelectWeather = { weather ->
                        viewModel.selectWeather(weather)
                    },
                    onLocationClick = { location ->
                        viewModel.enterLocation(location)
                    },
                    onEasterEggClick = { msg ->
                        viewModel.speakText(msg)
                    },
                    isVoiceEnabled = isVoiceEnabled,
                    onToggleVoice = {
                        val current = parentSettings?.voiceNarrationEnabled ?: true
                        viewModel.updateParentSettings(
                            voiceEnabled = !current,
                            soundEffectsEnabled = parentSettings?.soundEffectsEnabled ?: true,
                            languageCode = parentSettings?.languageCode ?: "en",
                            sessionTimerMinutes = parentSettings?.sessionTimerMinutes ?: 20
                        )
                    },
                    onParentGateClick = {
                        viewModel.navigateTo(AppScreen.PARENT_GATE)
                    },
                    townItems = townProgress,
                    isHindi = isHindi
                )
            }

            AppScreen.LOCATION_OVERVIEW -> {
                LocationOverviewScreen(
                    location = selectedLocation,
                    growthPoints = childProfile?.townGrowthPoints ?: 5,
                    spokenNarration = spokenNarration,
                    characterExpression = characterExpression,
                    onSpeechClick = {
                        viewModel.speakText(spokenNarration)
                    },
                    onBackClick = {
                        viewModel.navigateBack()
                    },
                    onStartMission = { mission ->
                        viewModel.startMission(mission)
                    },
                    onFreeExploreClick = {
                        viewModel.startFreeExplore(selectedLocation)
                    },
                    isVoiceEnabled = isVoiceEnabled,
                    onToggleVoice = {
                        val current = parentSettings?.voiceNarrationEnabled ?: true
                        viewModel.updateParentSettings(
                            voiceEnabled = !current,
                            soundEffectsEnabled = parentSettings?.soundEffectsEnabled ?: true,
                            languageCode = parentSettings?.languageCode ?: "en",
                            sessionTimerMinutes = parentSettings?.sessionTimerMinutes ?: 20
                        )
                    },
                    onParentGateClick = {
                        viewModel.navigateTo(AppScreen.PARENT_GATE)
                    },
                    isHindi = isHindi,
                    stage = childProfile?.developmentalStage
                )
            }

            AppScreen.MISSION_PLAY -> {
                MissionPlayScreen(
                    mission = currentMission,
                    growthPoints = childProfile?.townGrowthPoints ?: 5,
                    missionPhase = missionPhase,
                    spokenNarration = spokenNarration,
                    characterExpression = characterExpression,
                    wrongAttemptCount = wrongAttemptCount,
                    actionAnimationActive = actionAnimationActive,
                    onSpeechClick = {
                        viewModel.speakText(spokenNarration)
                    },
                    onBackClick = {
                        viewModel.navigateBack()
                    },
                    onProceedToSelection = {
                        viewModel.proceedToToolSelection()
                    },
                    onToolSelected = { tool ->
                        viewModel.onToolSelected(tool)
                    },
                    onCompleteAction = {
                        viewModel.completeActionAndShowConsequence()
                    },
                    onTriggerCelebration = {
                        viewModel.triggerCelebration()
                    },
                    onOpenWhatIf = {
                        viewModel.openWhatIfMode()
                    },
                    onContinueAfterCelebration = {
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    },
                    isVoiceEnabled = isVoiceEnabled,
                    onToggleVoice = {
                        val current = parentSettings?.voiceNarrationEnabled ?: true
                        viewModel.updateParentSettings(
                            voiceEnabled = !current,
                            soundEffectsEnabled = parentSettings?.soundEffectsEnabled ?: true,
                            languageCode = parentSettings?.languageCode ?: "en",
                            sessionTimerMinutes = parentSettings?.sessionTimerMinutes ?: 20
                        )
                    },
                    onParentGateClick = {
                        viewModel.navigateTo(AppScreen.PARENT_GATE)
                    },
                    isHindi = isHindi
                )
            }

            AppScreen.WHAT_IF_PLAY -> {
                WhatIfScreen(
                    mission = currentMission,
                    growthPoints = childProfile?.townGrowthPoints ?: 5,
                    spokenNarration = spokenNarration,
                    characterExpression = characterExpression,
                    selectedChoice = selectedWhatIf,
                    onChoiceSelected = { choice ->
                        viewModel.onWhatIfChoiceSelected(choice)
                    },
                    onSpeechClick = {
                        viewModel.speakText(spokenNarration)
                    },
                    onBackClick = {
                        viewModel.navigateBack()
                    },
                    onFinishWhatIf = {
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    },
                    isVoiceEnabled = isVoiceEnabled,
                    onToggleVoice = {
                        val current = parentSettings?.voiceNarrationEnabled ?: true
                        viewModel.updateParentSettings(
                            voiceEnabled = !current,
                            soundEffectsEnabled = parentSettings?.soundEffectsEnabled ?: true,
                            languageCode = parentSettings?.languageCode ?: "en",
                            sessionTimerMinutes = parentSettings?.sessionTimerMinutes ?: 20
                        )
                    },
                    onParentGateClick = {
                        viewModel.navigateTo(AppScreen.PARENT_GATE)
                    },
                    isHindi = isHindi
                )
            }

            AppScreen.FREE_EXPLORE -> {
                FreeExploreScreen(
                    location = selectedLocation,
                    growthPoints = childProfile?.townGrowthPoints ?: 5,
                    spokenNarration = spokenNarration,
                    characterExpression = characterExpression,
                    onSpeechClick = {
                        viewModel.speakText(spokenNarration)
                    },
                    onBackClick = {
                        viewModel.navigateBack()
                    },
                    onPropTapped = { prop ->
                        val response = if (isHindi) prop.soundReactionHi else prop.soundReactionEn
                        when (prop.soundType) {
                            "heartbeat" -> viewModel.audioEngine.playHeartbeat()
                            "water" -> viewModel.audioEngine.playWaterDrop()
                            "siren" -> viewModel.audioEngine.playSirenChirp()
                            "bell" -> viewModel.audioEngine.playBell()
                            else -> viewModel.audioEngine.playSparkle()
                        }
                        viewModel.speakText(response)
                    },
                    isVoiceEnabled = isVoiceEnabled,
                    onToggleVoice = {
                        val current = parentSettings?.voiceNarrationEnabled ?: true
                        viewModel.updateParentSettings(
                            voiceEnabled = !current,
                            soundEffectsEnabled = parentSettings?.soundEffectsEnabled ?: true,
                            languageCode = parentSettings?.languageCode ?: "en",
                            sessionTimerMinutes = parentSettings?.sessionTimerMinutes ?: 20
                        )
                    },
                    onParentGateClick = {
                        viewModel.navigateTo(AppScreen.PARENT_GATE)
                    },
                    isHindi = isHindi
                )
            }

            AppScreen.PARENT_GATE -> {
                ParentGateScreen(
                    onUnlockSuccess = {
                        viewModel.navigateTo(AppScreen.PARENT_DASHBOARD)
                    },
                    onBackClick = {
                        viewModel.navigateBack()
                    }
                )
            }

            AppScreen.PARENT_DASHBOARD -> {
                ParentDashboardScreen(
                    childProfile = childProfile,
                    completedMissions = completedMissions,
                    parentSettings = parentSettings,
                    onSaveSettings = { voice, sfx, lang, sessionMin ->
                        viewModel.updateParentSettings(voice, sfx, lang, sessionMin)
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    },
                    onEditProfileClick = {
                        viewModel.navigateTo(AppScreen.PROFILE_SETUP)
                    },
                    onBackClick = {
                        viewModel.navigateTo(AppScreen.TOWN_MAP)
                    }
                )
            }
        }
    }
}
