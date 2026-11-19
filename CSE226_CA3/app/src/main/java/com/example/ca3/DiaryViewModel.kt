package com.example.ca3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val dao = db.diaryDao()

    var entries by mutableStateOf<List<DiaryEntry>>(emptyList())
        private set

    var message by mutableStateOf("")
        private set

    fun loadEntries() {
        viewModelScope.launch {
            entries = dao.getAllEntries()
        }
    }

    fun addEntry(title: String, content: String) {
        viewModelScope.launch {
            val count = dao.getCount()

            if (count < 3) {
                dao.insertEntry(
                    DiaryEntry(title = title, content = content)
                )
                message = "Diary entry saved!"
                loadEntries()
            } else {
                message = "Maximum 3 entries reached!"
            }
        }
    }
}
