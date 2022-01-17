package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating MessageService Bean");
    }

    public void addNote(FileForm fileForm){
        Note newNote = new Note();
//        newNote.setNoteTitle(fileForm.getNoteTitle()); create a noteForm model0
        noteMapper.insertNote(newNote);
    }

    public List<Note> getNoteHistory(){
        return noteMapper.getAllNotes();
    }

}

