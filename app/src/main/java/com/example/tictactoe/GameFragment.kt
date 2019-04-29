package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tictactoe.data.HighScore


private const val ARG_AI_MODE = "aiMode"
private const val ARG_PLAYER_X_NAME = "playerX"
private const val ARG_PLAYER_O_NAME = "playerO"

class GameFragment : Fragment() {

    private var aiMode: Boolean = false
    private var playerX: String = ""
    private var playerO: String? = null

    private lateinit var highScoreViewModel: HighScoreViewModel

    private lateinit var buttons: Array<Button>

    private lateinit var bottomText: TextView

    private lateinit var btnNewGame: Button

    private lateinit var game: TicTacToe

    private lateinit var highScores: List<HighScore>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            aiMode = it.getBoolean(ARG_AI_MODE)
            playerX = it.getString(ARG_PLAYER_X_NAME)?: throw Exception("No player name")
            playerO = it.getString(ARG_PLAYER_O_NAME)
        }

        game = TicTacToe(aiMode, playerX, playerO)

        highScoreViewModel = ViewModelProviders.of(this).get(HighScoreViewModel::class.java)

        highScoreViewModel.highScores.observe(this, Observer {
            highScores = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        view.run {
            bottomText = findViewById(R.id.txtWinner)
            btnNewGame = findViewById(R.id.btnNewGame)

            btnNewGame.setOnClickListener {
                newGame()
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

        return view
    }

    private fun btnOnClickListener(button: Button) {
        button.setOnClickListener {
            if (!game.gameOver) {
                if (game.moveCount % 2 == 0) game.move(game.p1, buttons.indexOf(button))
                else game.move(game.p2, buttons.indexOf(button))
                updateView()
            }
        }
    }

    private fun updateView() {
        for ((index, text) in game.positions.withIndex()) {
            if (text == "X" || text == "O") {
                buttons[index].text = text
            }
        }
        if (game.gameOver) {
            if (game.winner.isNotEmpty()) {
                bottomText.text = game.winner + " won"
                highScoreViewModel.insert(newHighscore(game.winner))
            }
            else bottomText.text = "It's a tie"
            btnNewGame.visibility = View.VISIBLE
        }
    }

    private fun newHighscore(name: String): HighScore {
        highScores.forEach {
            if (it.name == name) {
                return HighScore(name, it.score + 10)
            }
        }
        return HighScore(name, 10)
    }

    private fun newGame() {
        for (i in 0 until game.positions.size) {
            buttons[i].text = ""
        }
        bottomText.text = ""
        btnNewGame.visibility = View.INVISIBLE
        game = TicTacToe(aiMode, playerX, playerO)
    }

    companion object {
        @JvmStatic
        fun newInstance(aiMode: Boolean, playerX: String, playerO: String?) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_AI_MODE, aiMode)
                    putString(ARG_PLAYER_X_NAME, playerX)
                    putString(ARG_PLAYER_O_NAME, playerO)
                }
            }
    }
}
