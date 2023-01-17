package com.yusufcancakmak.firebasewithmvvm.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import com.yusufcancakmak.firebasewithmvvm.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository:NoteRepository):ViewModel() {

    private val _notes=MutableLiveData<List<Note>>()
    val note:LiveData<List<Note>> = _notes

    fun getNotes(){
        _notes.value=repository.getNotes()
    }


}