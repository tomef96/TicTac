package com.tomfevang.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>

    private lateinit var bottomText: TextView

    private lateinit var btnNewGame: Button

    private val game = TicTacToe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game.aiMode = intent.getBooleanExtra("aiMode", false)

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


        for (button in buttons) {
            btnOnClickListener(button)
        }
    }

    private fun btnOnClickListener(button: Button) {
        button.setOnClickListener {
            if (!game.gameOver) {
                game.move(game.p1, buttons.indexOf(button))
                updateButtons()
            }
            if (game.gameOver) {
                if (game.winner.isNotEmpty()) bottomText.text = game.winner + " won"
                else bottomText.text = "It's a tie"
                btnNewGame.visibility = View.VISIBLE
            }
        }
    }

    private fun updateButtons() {
        for ((i, index) in game.positions.withIndex()) {
            if (index == "X" || index == "O") {
                buttons[i].text = index
            }
        }
    }

}
