package com.tomfevang.tictactoe

class TicTacToe {

    val p1 = "X"
    val p2 = "O"

    val positions = arrayOf("1","2","3","4","5","6","7","8","9")

    private val winningCombinations = arrayOf(
        arrayOf(1,2,3),
        arrayOf(1,4,7),
        arrayOf(1,5,9),
        arrayOf(2,5,8),
        arrayOf(3,5,7),
        arrayOf(3,6,9),
        arrayOf(4,5,6),
        arrayOf(7,8,9)
    )

    var gameWon = false
    var winner = ""

    fun chooseOne(player: String, position: Int) {
        if (positions[position-1] != "X" && positions[position-1] != "O") {
            positions[position-1] = player
        }
    }

    fun checkIfWon(player: String) {
        var result = false
        for (combination in winningCombinations) {
            for (position in combination) {
                result = positions[position-1] == player
                if (!result) break
            }
            if (result) {
                gameWon = true
                winner = player
                break
            }
        }
    }

}