package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    private Credential creds;

    private String salt;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping
    public String addNewCredentials(@ModelAttribute("credForm") CredentialForm credForm,
                                    @RequestParam(value = "credentialId", required = false) Integer credentialId,
                                    Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userid = userService.getUserId(username);

        creds = new Credential();
        creds.setUrl(credForm.getUrl());
        creds.setUsername(credForm.getUsername());
        creds.setUserId(userid);
        creds.setPassword(credForm.getPassword());
        Credential updateCred = credentialService.getCredential(creds.getUrl());

        if (credentialId != null) {
            creds.setCredentialId(credentialId);
            credentialService.updateCredential(creds);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else if (creds.getUrl() != null || !creds.getUrl().equals("")) {
            creds.newKey();
            creds.setPassword(encryptionService.encryptValue(credForm.getPassword(), creds.getKey()));
            credentialService.addCredential(creds);
            model.addAttribute("success", "Your changes were successfully saved.");
        } else {
            model.addAttribute("otherError", "No Credential Chosen. Select a credential and try again.");
        }
        model.addAttribute("decryptPass", encryptionService.decryptValue(creds.getPassword(), creds.getKey()));
        return "result";
    }

    @GetMapping(value = {"/delete/{url}"})
    private String deleteCred(@PathVariable(name = "url") String url, RedirectAttributes redirectAttributes) {
        credentialService.deleteCredential(url);
        redirectAttributes.addAttribute("tab", "nav-credentials-tab");
        return "redirect:/home";
    }

}
