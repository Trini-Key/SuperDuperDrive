package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNote(String noteTitle);

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) "
            + "VALUES(#{noteId}, #{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Update("UPDATE NOTES SET noteid = #{noteId}, notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userId}")
    @Options(keyProperty = "noteId")
    int updateNote(Note note);

    @Select("SELECT notetitle FROM NOTES WHERE userid = #{userId}")
    List<String> getUserNoteTitles(Integer userId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllNotes(Integer userId);

    @Delete("DELETE FROM NOTES WHERE notetitle = #{noteTitle}")
    void deleteNote(String noteTitle);
}
