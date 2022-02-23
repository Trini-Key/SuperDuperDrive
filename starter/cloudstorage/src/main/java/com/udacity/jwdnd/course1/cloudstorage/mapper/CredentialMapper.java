package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url}")
    Credential getCredential(String url);

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid)"
            + "VALUES(#{credentialId}, #{url}, #{username}, #{key}, #{password}, #{userId} )")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS WHERE url = #{url} SET credentialid = #{credentialId}, url = #{url}, credential = #{username}," +
            "key = #{key}, password = #{password}, userid = #{userId}")
    @Options(keyProperty = "credentialId")
    int updateCredential(Credential credential);

    @Select("SELECT url FROM CREDENTIALS WHERE userid = #{userId}")
    List<String> getUserCredentialURLs(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredentials(Integer userId);

    @Delete("DELETE FROM CREDENTIALS WHERE url = #{url}")
    void deleteCredential(String url);
}
