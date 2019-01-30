package com.tomfevang.tictactoe

import org.junit.Test
import org.junit.Assert.*

class TicTacToeTest {

    private val game = TicTacToe()

    @Test
    fun playerChoiceIsRegistered() {
        game.chooseOne(game.p1, 4)
        game.chooseOne(game.p2, 3)
        for (spot in game.positions) {
            print(spot)
        }
        assertEquals(game.p1, game.positions[3])
        assertEquals(game.p2, game.positions[2])
    }

    @Test
    fun spotIsTaken() {
        game.chooseOne(game.p1, 9)
        game.chooseOne(game.p2, 9)
        assertEquals(game.p1, game.positions[8])
    }

    @Test
    fun playerXWon() {
        game.chooseOne(game.p1, 1)
        game.chooseOne(game.p1, 2)
        game.chooseOne(game.p1, 3)
        game.checkIfWon(game.p1)
        assertEquals(true, game.gameWon)
        assertEquals("X", game.winner)
    }

    @Test
    fun playerOWon() {
        game.chooseOne(game.p2, 8)
        game.chooseOne(game.p2, 5)
        game.chooseOne(game.p2, 2)
        game.checkIfWon(game.p2)
        assertEquals(true, game.gameWon)
        assertEquals("O", game.winner)
    }
}