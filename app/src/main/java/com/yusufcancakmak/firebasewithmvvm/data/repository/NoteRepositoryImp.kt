package com.yusufcancakmak.firebasewithmvvm.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import java.util.*


class NoteRepositoryImp(
    val database:FirebaseFirestore
) :NoteRepository {
    override fun getNotes(): List<Note> {
        //we will get data from fiebase
        return arrayListOf(

            Note(
                id = "fadasd",
                text = "note1",
                date = Date()
            ),
            Note(
                id = "fadasd",
                text = "note2",
                date = Date()
            ),
            Note(
                id = "fadasd",
                text = "note3",
                date = Date()
            ),
        )
    }

}