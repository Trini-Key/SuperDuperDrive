package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;

public class FileService {
    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void postConstruct() {System.out.println("Creating FileService Bean"); }

    public String[] getUserFiles(Integer userId){
        return fileMapper.getUserFiles(userId);
    }

    public File getFile(String filename) { return fileMapper.getFile(filename); }

    public int insertFile(File file) { return fileMapper.insertFile(file); }

    public void deleteFile(String filename) { fileMapper.deleteFile(filename); }
}
