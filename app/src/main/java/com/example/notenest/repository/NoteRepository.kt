package com.example.notenest.data

import com.example.notenest.model.Note
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NoteRepository {

    private val db = FirebaseFirestore.getInstance()
    private val notesCollection = db.collection("notes")

    suspend fun addNote(note: Note) {
        val newDoc = notesCollection.document()
        note.id = newDoc.id
        newDoc.set(note).await()
    }

    suspend fun updateNote(note: Note) {
        notesCollection.document(note.id).set(note).await()
    }

    suspend fun deleteNote(noteId: String) {
        notesCollection.document(noteId).delete().await()
    }

    suspend fun getAllNotes(): List<Note> {
        val snapshot = notesCollection
            .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get()
            .await()

        return snapshot.documents.mapNotNull { it.toObject(Note::class.java) }
    }
}
