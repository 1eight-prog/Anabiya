package com.example.data

import kotlinx.coroutines.flow.Flow

class ChildRepository(private val childDao: ChildDao) {

    val childProfile: Flow<ChildProfile?> = childDao.getChildProfile()
    val completedMissions: Flow<List<CompletedMission>> = childDao.getAllCompletedMissions()
    val townProgress: Flow<List<TownProgression>> = childDao.getAllTownProgress()
    val parentSettings: Flow<ParentSettings?> = childDao.getParentSettings()

    suspend fun saveProfile(profile: ChildProfile) {
        childDao.insertOrUpdateProfile(profile)
    }

    suspend fun getProfileSync(): ChildProfile? {
        return childDao.getChildProfileSync()
    }

    suspend fun completeMission(
        professionId: String,
        missionId: String,
        stage: String,
        skill: String,
        growthReward: Int = 1
    ) {
        childDao.recordCompletedMission(
            CompletedMission(
                professionId = professionId,
                missionId = missionId,
                developmentalStage = stage,
                skillLearned = skill
            )
        )
        childDao.addTownGrowthPoints(growthReward)
    }

    suspend fun unlockTownItem(itemId: String, locationId: String, note: String = "") {
        childDao.insertTownItem(
            TownProgression(
                itemId = itemId,
                locationId = locationId,
                isUnlocked = true,
                note = note
            )
        )
    }

    suspend fun saveParentSettings(settings: ParentSettings) {
        childDao.updateParentSettings(settings)
    }

    suspend fun addPlayTime(minutes: Int) {
        childDao.addPlayTime(minutes)
    }

    suspend fun initializeDefaultDataIfEmpty() {
        val existing = childDao.getChildProfileSync()
        if (existing == null) {
            childDao.insertOrUpdateProfile(
                ChildProfile(
                    id = 1,
                    name = "Aarav",
                    avatarId = "avatar_bunny",
                    developmentalStage = "A",
                    favoriteProfession = "DOCTOR",
                    townGrowthPoints = 3,
                    totalPlayMinutes = 5
                )
            )
            childDao.updateParentSettings(ParentSettings())

            // Default starter town items
            val starterItems = listOf(
                TownProgression(itemId = "sunflower_patch", locationId = "FARM", isUnlocked = true, note = "First planted sunflower"),
                TownProgression(itemId = "clinic_puppy_bed", locationId = "CLINIC", isUnlocked = true, note = "Cozy puppy bed"),
                TownProgression(itemId = "fire_bell", locationId = "FIRESTATION", isUnlocked = true, note = "Golden fire bell"),
                TownProgression(itemId = "park_swings", locationId = "PARK", isUnlocked = false, note = "Park wooden swing"),
                TownProgression(itemId = "town_fountain", locationId = "TOWN", isUnlocked = false, note = "Sparkling water fountain")
            )
            childDao.insertTownItems(starterItems)
        }
    }
}
