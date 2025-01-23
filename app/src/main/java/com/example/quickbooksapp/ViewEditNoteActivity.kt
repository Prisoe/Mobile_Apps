package com.example.quickbooksapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ViewEditNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteIndex = intent.getIntExtra("noteIndex", -1)
        setContent {
            if (noteIndex != -1) {
                ViewEditNoteScreen(noteIndex)
            }
        }
    }

    @Composable
    fun ViewEditNoteScreen(noteIndex: Int) {
        var title by remember { mutableStateOf(HomeActivity.notes[noteIndex].title) }
        var content by remember { mutableStateOf(HomeActivity.notes[noteIndex].content) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        HomeActivity.notes[noteIndex].title = title
                        HomeActivity.notes[noteIndex].content = content
                        startActivity(Intent(this@ViewEditNoteActivity, HomeActivity::class.java))
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}
