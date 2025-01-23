package com.example.quickbooksapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickbooksapp.Note

class HomeActivity : ComponentActivity() {

    companion object {
        var notes = mutableListOf<Note>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(notes = notes, onNoteClick = { noteIndex ->
                val intent = Intent(this, ViewEditNoteActivity::class.java).apply {
                    putExtra("noteIndex", noteIndex)
                }
                startActivity(intent)
            })
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun HomeScreen(notes: List<Note>, onNoteClick: (Int) -> Unit) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val intent = Intent(this@HomeActivity, CreateNoteActivity::class.java)
                    startActivity(intent)
                }) {
                    Text("+", fontSize = 24.sp)
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(notes) { note ->
                    val noteIndex = notes.indexOf(note)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onNoteClick(noteIndex) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = note.title, fontSize = 20.sp)
                            Text(text = note.content.take(50) + "...", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
