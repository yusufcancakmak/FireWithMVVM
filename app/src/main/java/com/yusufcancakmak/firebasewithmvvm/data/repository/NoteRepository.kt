package com.yusufcancakmak.firebasewithmvvm.data.repository

import com.yusufcancakmak.firebasewithmvvm.data.model.Note

interface NoteRepository {

    fun getNotes(): List<Note>
}