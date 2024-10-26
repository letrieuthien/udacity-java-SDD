package com.udacity.jwdnd.course1.cloudstorage.mapping;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id} AND userid = #{userId}")
    void delete(Credential credential);

    @Select("SELECT credentialid, url, password, username, userid FROM CREDENTIALS WHERE userid = #{UID} ORDER BY credentialid DESC")
    List<Credential> allFrom(String UID);

    @Select("SELECT credentialid, key, url, password, username, userid FROM CREDENTIALS WHERE credentialid = #{id} AND userid = #{userId}")
    Credential find(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE credentialid =#{id}")
    void update(Credential credential);

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, key, username, password, userid) VALUES(#{id}, #{url}, #{key}, #{username}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Credential credential);

}
