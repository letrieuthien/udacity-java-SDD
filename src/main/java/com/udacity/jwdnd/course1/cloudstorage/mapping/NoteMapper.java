package com.udacity.jwdnd.course1.cloudstorage.mapping;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Delete("DELETE FROM NOTES WHERE noteid = #{id} AND userid = #{userId}")
    void delete(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{UID} ORDER BY noteid DESC")
    List<Note> allFrom(String UID);

    @Update("UPDATE NOTES SET notetitle=#{title}, notedescription=#{description} WHERE noteid = #{id} AND userid = #{userId}")
    void update(Note note);

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES(#{id}, #{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Note note);

}
