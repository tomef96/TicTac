package com.tomfevang.tictactoe

class TicTacToe(val aiMode: Boolean, val playerX: String, val playerO: String?) {

    val p1 = "X"
    val p2 = "O"

    val positions = arrayOf(
        "0", "1", "2",
        "3", "4", "5",
        "6", "7", "8")

    private val winningCombinations = arrayOf(
        arrayOf(0,1,2),
        arrayOf(3,4,5),
        arrayOf(6,7,8),
        arrayOf(0,3,6),
        arrayOf(1,4,7),
        arrayOf(2,5,8),
        arrayOf(0,4,8),
        arrayOf(2,4,6)
    )

    var gameOver = false

    var winner = ""

    var moveCount = 0

    fun move(player: String, position: Int) {
        if (positions[position] != "X" && positions[position] != "O") {
            positions[position] = player
            checkIfWon(player)
            if (gameOver) return
            moveCount++
            if (moveCount > 8) {
                gameOver = true
            }
            else if (aiMode) {
                while (true) {
                    val index = positions.random()
                    if (index != "X" && index != "O") {
                        positions[index.toInt()] = "O"
                        checkIfWon(p2)
                        moveCount++
                        break
                    }
                }
            }
        }
    }

    fun checkIfWon(player: String) {
        var result = false
        for (combination in winningCombinations) {
            for (position in combination) {
                result = positions[position] == player
                if (!result) break
            }
            if (result) {
                gameOver = true
                if (player == "X") winner = playerX
                if (player == "O") winner = playerO?: "TTTBot"
                break
            }
        }
    }

    private fun isBoardFull() {
        for (index in positions) {
            gameOver = !(index != "X" && index != "O")
        }
    }

    private fun isPositionTaken(position: String): Boolean {
        return position != "X" && position != "O"
    }
}