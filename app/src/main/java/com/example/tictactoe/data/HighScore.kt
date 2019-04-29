package com.example.tictactoe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HighScore (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "score") val score: Int
)