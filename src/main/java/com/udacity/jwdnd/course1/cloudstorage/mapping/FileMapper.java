package com.udacity.jwdnd.course1.cloudstorage.mapping;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Delete("DELETE FROM FILES WHERE fileId = #{id} AND userid = #{userId}")
    void delete(File file);

    @Select("SELECT fileId, filename, contenttype, filesize, filedata, userid FROM FILES WHERE fileId = #{id} AND userid = #{userId}")
    @Results({
        @Result(property = "contentType", column = "contenttype")
    })
    File get(File file);

    @Select("SELECT fileId, filename FROM FILES WHERE userid = #{userId} AND filename = #{name}")
    File find(File file);

    @Select("SELECT fileId, filename FROM FILES WHERE userid = #{UID} ORDER BY fileId DESC")
    List<File> allFrom(String UID);

    @Insert("INSERT INTO FILES (fileId, filename, contenttype, filesize, filedata, userid) " +
                        "VALUES(#{id}, #{name}, #{contentType}, #{size}, #{data}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(File file);

}
