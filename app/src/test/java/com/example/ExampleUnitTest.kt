package com.example

import com.example.model.CharacterBible
import com.example.model.MissionCatalog
import com.example.model.ProfessionType
import com.example.model.TownWorld
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MujheKaunBanegaUnitTest {

    @Test
    fun testTownLocationsCount() {
        val locations = TownWorld.locations
        assertEquals("Should have 10 locations in Little Town", 10, locations.size)
        assertTrue(locations.any { it.profession == ProfessionType.DOCTOR })
        assertTrue(locations.any { it.profession == ProfessionType.FARMER })
        assertTrue(locations.any { it.profession == ProfessionType.FIREFIGHTER })
    }

    @Test
    fun testMissionCatalogValidation() {
        val missions = MissionCatalog.allMissions
        assertTrue("Missions catalog should not be empty", missions.isNotEmpty())
        for (mission in missions) {
            assertTrue("Mission must have title", mission.titleEn.isNotBlank())
            assertTrue("Mission must have tools to choose", mission.toolChoices.isNotEmpty())
            assertTrue("Mission must have at least one correct tool", mission.toolChoices.any { it.isCorrect })
            assertTrue("Mission must have celebration narration", mission.celebrationNarrationEn.isNotBlank())
            assertTrue("Mission must have what-if choices", mission.whatIfChoices.isNotEmpty())
        }
    }

    @Test
    fun testCharacterBibleValidation() {
        for (profession in listOf(ProfessionType.DOCTOR, ProfessionType.FARMER, ProfessionType.FIREFIGHTER)) {
            val char = CharacterBible.getCharacterForProfession(profession)
            assertNotNull(char)
            assertTrue("Character must have greeting in English", char.greetingEn.isNotBlank())
            assertTrue("Character must have greeting in Hindi", char.greetingHi.isNotBlank())
            assertTrue("Character must have encouraging phrases", char.encouragingPhrasesEn.isNotEmpty())
            assertTrue("Character must have gentle guidance on wrong choices", char.wrongChoiceGuidanceEn.isNotEmpty())
        }
    }
}
