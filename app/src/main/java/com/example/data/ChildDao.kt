package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildDao {
    @Query("SELECT * FROM child_profile WHERE id = 1 LIMIT 1")
    fun getChildProfile(): Flow<ChildProfile?>

    @Query("SELECT * FROM child_profile WHERE id = 1 LIMIT 1")
    suspend fun getChildProfileSync(): ChildProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: ChildProfile)

    @Query("SELECT * FROM completed_missions ORDER BY timestamp DESC")
    fun getAllCompletedMissions(): Flow<List<CompletedMission>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun recordCompletedMission(mission: CompletedMission)

    @Query("SELECT * FROM town_progression")
    fun getAllTownProgress(): Flow<List<TownProgression>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTownItem(item: TownProgression)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTownItems(items: List<TownProgression>)

    @Query("SELECT * FROM parent_settings WHERE id = 1 LIMIT 1")
    fun getParentSettings(): Flow<ParentSettings?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateParentSettings(settings: ParentSettings)

    @Query("UPDATE child_profile SET townGrowthPoints = townGrowthPoints + :points WHERE id = 1")
    suspend fun addTownGrowthPoints(points: Int)

    @Query("UPDATE child_profile SET totalPlayMinutes = totalPlayMinutes + :minutes WHERE id = 1")
    suspend fun addPlayTime(minutes: Int)
}
