package com.yusufcancakmak.firebasewithmvvm.note

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import com.yusufcancakmak.firebasewithmvvm.data.repository.NoteRepository
import com.yusufcancakmak.firebasewithmvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository:NoteRepository):ViewModel() {
    //list note
    private val _notes=MutableLiveData<UiState<List<Note>>>()
    val note:LiveData<UiState<List<Note>>> = _notes

    //add note
    private val _addnotes=MutableLiveData<UiState<String>>()
    val addnote:LiveData<UiState<String>> = _addnotes

    fun getNotes(){
        _notes.value=UiState.Loading
        repository.getNotes {
            _notes.value=it
        }
    }

    fun addNote(note: Note){
        _addnotes.value=UiState.Loading
        repository.addNote(note){
            _addnotes.value=it
        }
    }


}