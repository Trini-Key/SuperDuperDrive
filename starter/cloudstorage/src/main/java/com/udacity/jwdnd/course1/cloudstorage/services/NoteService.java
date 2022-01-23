package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating NoteService Bean");
    }

    public int addNote(Note note) {
        return noteMapper.insertNote(note);
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public List<String> getNoteHistory(Integer userId) {
        return noteMapper.getUserNoteTitles(userId);
    }

    public List<Note> getNotes(Integer userId) {
        return noteMapper.getAllNotes(userId);
    }

    public void deleteNote(String noteTitle){
        noteMapper.deleteNote(noteTitle);
    }

    public Note getNote(String noteTitle){
        return noteMapper.getNote(noteTitle);
    }

}

