package com.example.tictactoe.data

import androidx.lifecycle.LiveData

class HighScoreRepository(private val dao: HighScoreDao) {

    val allHighScores: LiveData<List<HighScore>> = dao.getAll()

    fun insert(highScore: HighScore) = dao.insert(highScore)

}