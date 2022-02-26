package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String salt;
    private String password;
    private Integer userId;

    @Autowired
    private EncryptionService encryptionService;

    public Credential() {}

    public Credential(Integer credentialId, String url, String username, String password, Integer userId) {
        byte[] array = new byte[8];
        new Random().nextBytes(array);
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.salt = new String(array, Charset.defaultCharset());
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String newKey() {
        byte[] key = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }
}
