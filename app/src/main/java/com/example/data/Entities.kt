package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child_profile")
data class ChildProfile(
    @PrimaryKey val id: Int = 1,
    val name: String = "Chintu",
    val avatarId: String = "avatar_bunny",
    val developmentalStage: String = "A", // A: 2-3 yrs, B: 3-4 yrs, C: 4-5 yrs
    val favoriteProfession: String = "DOCTOR",
    val townGrowthPoints: Int = 5,
    val totalPlayMinutes: Int = 12,
    val lastActiveDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "completed_missions")
data class CompletedMission(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val professionId: String,
    val missionId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val developmentalStage: String,
    val skillLearned: String,
    val attemptsCount: Int = 1
)

@Entity(tableName = "town_progression")
data class TownProgression(
    @PrimaryKey val itemId: String,
    val locationId: String,
    val isUnlocked: Boolean = false,
    val level: Int = 1,
    val note: String = ""
)

@Entity(tableName = "parent_settings")
data class ParentSettings(
    @PrimaryKey val id: Int = 1,
    val voiceNarrationEnabled: Boolean = true,
    val soundEffectsEnabled: Boolean = true,
    val speechPitch: Float = 1.25f, // Cheerful warm kid-friendly pitch
    val speechRate: Float = 0.90f,  // Deliberate, clear rate for toddlers
    val languageCode: String = "en", // "en" for English, "hi" for Hindi, "hinglish"
    val sessionTimerMinutes: Int = 20, // 0 for unlimited
    val highContrastMode: Boolean = false
)
