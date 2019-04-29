package com.tomfevang.tictactoe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tomfevang.tictactoe.data.AppDatabase
import com.tomfevang.tictactoe.data.HighScore
import com.tomfevang.tictactoe.data.HighScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HighScoreViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val dao = AppDatabase.getInstance(application).highScoreDao()

    private val repo = HighScoreRepository(dao)

    val highScores = repo.allHighScores

    fun insert(highscore: HighScore) = scope.launch(Dispatchers.IO) {
        repo.insert(highscore)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}