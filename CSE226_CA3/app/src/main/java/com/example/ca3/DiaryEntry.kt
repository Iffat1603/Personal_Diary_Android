package com.example.ca3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String
)
