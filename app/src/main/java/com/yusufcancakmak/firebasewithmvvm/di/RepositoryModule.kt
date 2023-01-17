package com.yusufcancakmak.firebasewithmvvm.di

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcancakmak.firebasewithmvvm.data.repository.NoteRepository
import com.yusufcancakmak.firebasewithmvvm.data.repository.NoteRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteReposiory(database: FirebaseFirestore):NoteRepository{
        return NoteRepositoryImp(database)
    }
}