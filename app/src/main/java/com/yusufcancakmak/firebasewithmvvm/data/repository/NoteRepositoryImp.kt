package com.yusufcancakmak.firebasewithmvvm.data.repository

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import com.yusufcancakmak.firebasewithmvvm.util.Constants
import com.yusufcancakmak.firebasewithmvvm.util.UiState



class NoteRepositoryImp(
    val database:FirebaseFirestore
) :NoteRepository {


    override fun getNotes(result: (UiState<List<Note>>) -> Unit) {

        database.collection(Constants.NOTE)
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for (document in it){
                    val note =document.toObject(Note::class.java)
                    notes.add(note)

                }
                result.invoke(
                    UiState.Success(notes)
                )

            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(it.localizedMessage
                    )
                )
            }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun addNote(note: Note, result: (UiState<String>) -> Unit) {
       val document= database.collection(Constants.NOTE).document()
               note.id = document.id
                       document
            .set(note)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Note has been cread suscces")
                )
            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(it.localizedMessage)
                )

            }
    }

}