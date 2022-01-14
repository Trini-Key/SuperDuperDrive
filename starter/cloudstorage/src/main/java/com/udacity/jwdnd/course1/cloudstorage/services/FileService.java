package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;

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
    public void postConstruct(){System.out.println("Creating FileService Bean");}

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public File getFile(String filename) { return fileMapper.getFile(filename); }
}
