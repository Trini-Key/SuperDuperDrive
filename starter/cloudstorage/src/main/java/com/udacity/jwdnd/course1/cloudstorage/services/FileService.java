package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    @Autowired
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
