package com.example.tictactoe

import org.junit.Test
import org.junit.Assert.*

class TicTacToeTest {

    private val game = TicTacToe()

    @Test
    fun playerChoiceIsRegistered() {
        game.move(game.p1, 0)
        assertEquals(game.p1, game.positions[0])
    }

    @Test
    fun spotIsTaken() {
        game.move(game.p1, 0)
        game.move(game.p2, 0)
        assertEquals(game.p1, game.positions[0])
    }

    @Test
    fun playerXWon() {
        game.move(game.p1, 0)
        game.move(game.p1, 1)
        game.move(game.p1, 2)
        game.checkIfWon(game.p1)
        assertEquals(true, game.gameOver)
        assertEquals(game.p1, game.winner)
    }

    @Test
    fun playerOWon() {
        game.move(game.p2, 2)
        game.move(game.p2, 5)
        game.move(game.p2, 8)
        game.checkIfWon(game.p2)
        assertEquals(true, game.gameOver)
        assertEquals(game.p2, game.winner)
    }

    @Test
    fun aiModeWorks() {
        game.aiMode = true
        game.move(game.p1, 0)
        assertTrue(game.positions.contains("O"))
    }
}