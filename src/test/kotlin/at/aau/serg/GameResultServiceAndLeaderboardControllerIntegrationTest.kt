package at.aau.serg

import at.aau.serg.controllers.LeaderboardController
import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class GameResultServiceAndLeaderboardControllerIntegrationTest {

    private lateinit var controller: LeaderboardController
    private lateinit var service: GameResultService

    @BeforeEach
    fun setup() {
        service = GameResultService();
        controller = LeaderboardController(service)
    }

    @Test
    fun test_getLeaderboard_correctScoreSorting() {
        val first = GameResult(1, "first", 20, 20.0)
        val second = GameResult(2, "second", 15, 10.0)
        val third = GameResult(3, "third", 10, 15.0)
        service.addGameResult(first)
        service.addGameResult(second)
        service.addGameResult(third)

        val res: List<GameResult> = controller.getLeaderboard()

        assertEquals(3, res.size)
        assertEquals(first, res[0])
        assertEquals(second, res[1])
        assertEquals(third, res[2])
    }

    @Test
    fun test_getLeaderboard_sameScore_CorrectIdSorting() {
        val resultA = GameResult(1, "a", 20, 20.0)
        val resultB = GameResult(2, "b", 20, 10.0)
        val resultC = GameResult(3, "c", 20, 15.0)

        service.addGameResult(resultA)
        service.addGameResult(resultB)
        service.addGameResult(resultC)

        val res: List<GameResult> = controller.getLeaderboard()

        assertEquals(3, res.size)
        assertEquals(resultB, res[0])
        assertEquals(resultC, res[1])
        assertEquals(resultA, res[2])
    }

}