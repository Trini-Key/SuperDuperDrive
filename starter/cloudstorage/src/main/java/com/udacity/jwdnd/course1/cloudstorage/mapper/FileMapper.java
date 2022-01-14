package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFile(String filename);

    @Insert("INSERT INTO FILES (fileid, filename, contenttype, filesize, userid, filedata) "
            + "VALUES(#{fileId}, #{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getUserFiles(Integer userId);

    @Delete("DELETE FROM FILES WHERE filename = #{filename}")
    void deleteFile(String filename);
}
