package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getHome(Authentication authentication, Model model) {
        Integer userid = userService.getUserId(authentication.getName());
        model.addAttribute("files", fileService.getUserFiles(userid));
        model.addAttribute("notes", noteService.getNotes(userid));
        //TO DO: add model attribute for note and credential

        return "home";
    }
}
