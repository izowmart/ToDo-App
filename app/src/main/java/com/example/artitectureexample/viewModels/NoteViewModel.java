package com.example.artitectureexample.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.artitectureexample.models.Note;
import com.example.artitectureexample.repositories.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private static String TAG = NoteViewModel.class.getSimpleName();
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "Retrieving data from the database");
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }
    public void update(Note note){
        repository.update(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
