package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private NoteService noteService;
    private UserService userService;
    private EncryptionService encryptionService;
    private HashService hashService;

    public HomeController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHome(){
        return "home";
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload")
                             MultipartFile fileUpload, Model model) throws IOException {
        InputStream fis = fileUpload.getInputStream();
//        this.fileService.addFile(homeForm);
//        homeForm.setFilename("");
        model.addAttribute("files", this.fileService.getAllFiles());
        return "home";
    }
}
