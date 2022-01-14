package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNote(String noteTitle);

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) "
            + "VALUES(#{noteId}, #{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Select(("SELECT * FROM NOTES"))
    List<Note> getAllNotes();
}