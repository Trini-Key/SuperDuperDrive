package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    private Note note;

    public NoteController(FileService fileService, UserService userService, NoteService noteService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String addNewNote(@RequestParam("noteId")Integer noteId, @RequestParam("noteTitle")String noteTitle, @RequestParam("noteDescription") String noteDescription, Model model, Authentication authentication){
        Integer userid = userService.getUserId(authentication.getName());
        note = new Note();
        System.out.println(noteId);
        note.setNoteTitle(noteTitle.toString());
        note.setNoteDescription(noteDescription.toString());
        note.setUserId(userid);

        if ( note.getNoteTitle() != null || !note.getNoteTitle().equals(" ") || !note.getNoteTitle().equals("")) {
            noteService.addNote(note);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else {
            model.addAttribute("otherError", "No note Chosen. Select a note and try again.");
        }
        return "result";
    }
}
