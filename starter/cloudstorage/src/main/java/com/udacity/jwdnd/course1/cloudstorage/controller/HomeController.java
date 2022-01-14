package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final EncryptionService encryptionService;
    private HashService hashService;
    private File file;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public HomeController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping
    public String handleFileUploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        InputStream fis = fileUpload.getInputStream();
        file.setFilename(fileUpload.getName());
        file.setContentype(fileUpload.getContentType());
        file.setFiledata(fileUpload.getBytes());
        file.setFilesize(String.valueOf(fileUpload.getSize()));
        Integer userID = userService.getUserId((String) auth.getPrincipal());
        file.setUserId(userID);
        fileService.insertFile(file);
        return "home";
    }
}
