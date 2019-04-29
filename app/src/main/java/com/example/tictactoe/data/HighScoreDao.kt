package com.example.tictactoe.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HighScoreDao {
    @Query("SELECT * FROM highscore")
    fun getAll(): LiveData<List<HighScore>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(highScore: HighScore)

    @Delete
    fun delete(highScore: HighScore)
}