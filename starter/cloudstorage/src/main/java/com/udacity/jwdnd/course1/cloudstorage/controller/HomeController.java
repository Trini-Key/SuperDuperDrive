package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping
    public String getHome(@ModelAttribute("credForm") CredentialForm credForm, Authentication authentication, Model model) {
        Integer userid = userService.getUserId(authentication.getName());
        model.addAttribute("files", fileService.getUserFiles(userid));
        model.addAttribute("notes", noteService.getNotes(userid));
        model.addAttribute("credentials", credentialService.getCredentials(userid));
        model.addAttribute("es", encryptionService);

        return "home";
    }
}
