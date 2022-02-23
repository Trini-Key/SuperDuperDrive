package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    private Note note;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String addNewNote(@RequestParam(value = "noteId", required = false)Integer noteId, @RequestParam("noteTitle")String noteTitle, @RequestParam("noteDescription") String noteDescription, Model model, Authentication authentication){
        Integer userid = userService.getUserId(authentication.getName());
        note = new Note();
        note.setNoteTitle(noteTitle.toString());
        note.setNoteDescription(noteDescription.toString());
        note.setUserId(userid);

        if (noteId != null) {
            note.setNoteId(noteId);
            noteService.updateNote(note);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else if (note.getNoteTitle() != null || !note.getNoteTitle().equals(" ") || !note.getNoteTitle().equals("")) {
            noteService.addNote(note);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else {
            model.addAttribute("otherError", "No note Chosen. Select a note and try again.");
        }
        return "result";
    }

    @GetMapping(value = {"/delete/{noteTitle}"})
    private String deleteNote(@PathVariable(name = "noteTitle") String noteTitle, RedirectAttributes redirectAttributes) {
        noteService.deleteNote(noteTitle);
        redirectAttributes.addAttribute("tab", "nav-notes-tab");
        return "redirect:/home";
    }

}
