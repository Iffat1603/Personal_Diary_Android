package com.example.ca3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {

    @Insert
    suspend fun insertEntry(entry: DiaryEntry)

    @Query("SELECT * FROM diary")
    suspend fun getAllEntries(): List<DiaryEntry>

    @Query("SELECT COUNT(*) FROM diary")
    suspend fun getCount(): Int
}
