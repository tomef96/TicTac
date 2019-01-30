package com.tomfevang.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class GameActivity : AppCompatActivity() {

    private var moveCount = 0

    private lateinit var buttons: Array<Button>

    private lateinit var winningCombinations: Array<Array<Button>>

    private lateinit var bottomText: TextView

    private lateinit var btnNewGame: Button

    private var gameOver = false

    private var aiMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        aiMode = intent.getBooleanExtra("aiMode", false)

        bottomText = findViewById(R.id.txtWinner)
        btnNewGame = findViewById(R.id.btnNewGame)

        btnNewGame.setOnClickListener {
            recreate()
        }

        val gameButton1: Button = findViewById(R.id.gameBtn1)
        val gameButton2: Button = findViewById(R.id.gameBtn2)
        val gameButton3: Button = findViewById(R.id.gameBtn3)
        val gameButton4: Button = findViewById(R.id.gameBtn4)
        val gameButton5: Button = findViewById(R.id.gameBtn5)
        val gameButton6: Button = findViewById(R.id.gameBtn6)
        val gameButton7: Button = findViewById(R.id.gameBtn7)
        val gameButton8: Button = findViewById(R.id.gameBtn8)
        val gameButton9: Button = findViewById(R.id.gameBtn9)

        buttons = arrayOf(gameButton1, gameButton2, gameButton3, gameButton4, gameButton5,
            gameButton6, gameButton7, gameButton8, gameButton9)

        winningCombinations = arrayOf(
            arrayOf(gameButton1, gameButton2, gameButton3),
            arrayOf(gameButton1, gameButton4, gameButton7),
            arrayOf(gameButton1, gameButton5, gameButton9),
            arrayOf(gameButton2, gameButton5, gameButton8),
            arrayOf(gameButton3, gameButton5, gameButton7),
            arrayOf(gameButton3, gameButton6, gameButton9),
            arrayOf(gameButton4, gameButton5, gameButton6),
            arrayOf(gameButton7, gameButton8, gameButton9))

        for (button in buttons) {
            btnOnClickListener(button)
        }
    }

    private fun btnOnClickListener(button: Button) {
        button.setOnClickListener {
            if (!gameOver && button.text == "") {
                if (moveCount % 2 == 0) {
                    button.text = "X"
                } else {
                    button.text = "O"
                }
                moveCount++

                checkIfWon(button.text.toString())

                if (moveCount > 8 && !gameOver) {
                    bottomText.text = getString(R.string.tie_message)
                    btnNewGame.visibility = View.VISIBLE
                    gameOver = true
                }

                if (aiMode && !gameOver) {
                    while (true) {
                        val thisButton = buttons.random()
                        if (thisButton.text == "") {
                            thisButton.text = "O"
                            moveCount++
                            checkIfWon("O")
                            break
                        }
                    }
                }
            }
        }
    }

    private fun checkIfWon(player: String) {
        var result = false
        for (combination in winningCombinations) {
            for (button in combination) {
                result = button.text == player
                if (!result) break
            }
            if (result) {
                gameOver = true
                bottomText.text = "$player won"
                btnNewGame.visibility = View.VISIBLE
                break
            }
        }
    }

}
