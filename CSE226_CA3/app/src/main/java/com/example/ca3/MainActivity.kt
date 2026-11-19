package com.example.ca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiaryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    viewModel: DiaryViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val entries = viewModel.entries
    val message = viewModel.message

    LaunchedEffect(Unit) {
        viewModel.loadEntries()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Personal Diary",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Diary Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Diary Content") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    viewModel.addEntry(title, content)
                    title = ""
                    content = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Entry")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Saved Entries: ${entries.size}/3",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(entries) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = entry.title,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = entry.content,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
