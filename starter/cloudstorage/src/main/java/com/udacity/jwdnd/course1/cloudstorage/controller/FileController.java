package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    private File file;

    public FileController(FileService fileService, NoteService noteService, UserService userService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String handleFileUploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {
        Integer userid = userService.getUserId(authentication.getName());
        file = new File();

        InputStream fis = fileUpload.getInputStream();
        file.setFilename(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        file.setFiledata(fileUpload.getBytes());
        file.setFilesize(String.valueOf(fileUpload.getSize()));
        file.setUserId(userid);

        String[] files = fileService.getUserFiles(userid);
        for (String ufile: files) {
            if (file.getFilename().equals(ufile)) {
                model.addAttribute("otherError", "that file already exists. Your changes were not saved.");
                return "result";
            }
        }

        if ( file.getFilename() != null || !file.getFilename().equals(" ") || !file.getFilename().equals("")) {
            fileService.insertFile(file);
            model.addAttribute("files", files);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else {
            model.addAttribute("otherError", "No file Chosen. Select a file and try again.");
        }
        return "result";

    }

    @GetMapping(value = {"/download/{filename}"})
    public ResponseEntity<byte[]> downloadFile(@PathVariable(name = "filename") String filename,
                                           HttpServletResponse response, HttpServletRequest request) {
        File file = fileService.getFile(filename);
        byte[] fileContents = file.getFiledata();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
        String fileName = file.getFilename();
        httpHeaders.setContentDispositionFormData(fileName, fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> serverResponse = new ResponseEntity<byte[]>(fileContents, httpHeaders, HttpStatus.OK);
        return serverResponse;
    }

    @GetMapping(value = {"/delete/{filename}"})
    private String deleteFile(@PathVariable(name = "filename") String filename, RedirectAttributes redirectAttributes){
        fileService.deleteFile(filename);
        redirectAttributes.addFlashAttribute("tab", "nav-files-tab");
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/home";
    }
}
