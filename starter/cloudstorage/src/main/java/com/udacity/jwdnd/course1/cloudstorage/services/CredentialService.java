package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    @Autowired
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService Bean");
    }

    public int addCredential(Credential credential) {
        return credentialMapper.insertCredential(credential);
    }

    public int updateCredential(Credential credential) {
        return credentialMapper.updateCredential(credential);
    }

    public List<String> getCredentialURLs(Integer userId) {
        return credentialMapper.getUserCredentialURLs(userId);
    }

    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.getAllCredentials(userId);
    }

    public void deleteCredential(String url) {
        credentialMapper.deleteCredential(url);
    }

    public Credential getCredential(String url, String username) {
        return credentialMapper.getCredential(url, username);
    }

    public Credential getCredentialById(Integer credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }
}
