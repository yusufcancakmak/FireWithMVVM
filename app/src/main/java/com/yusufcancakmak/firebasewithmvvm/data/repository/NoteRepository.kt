package com.yusufcancakmak.firebasewithmvvm.data.repository

import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import com.yusufcancakmak.firebasewithmvvm.util.UiState

interface NoteRepository {
    fun getNotes(result: (UiState<List<Note>>) -> Unit)
    fun addNote(note: Note, result: (UiState<String>) -> Unit)
    fun updateNote(note: Note, result: (UiState<String>) -> Unit)
    fun deleteNote(note: Note, result: (UiState<String>) -> Unit)
}