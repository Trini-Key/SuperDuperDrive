package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import javax.annotation.PostConstruct;
import java.util.List;

public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating MessageService Bean");
    }

    public void addNote(HomeForm homeForm){
        Note newNote = new Note();
        newNote.setNoteTitle(homeForm.getNoteTitle());
        noteMapper.insertNote(newNote);
    }

    public List<Note> getNoteHistory(){
        return noteMapper.getAllNotes();
    }

}

