package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer credentialid;
    private String url;
    private String key;
    private Integer userId;
    private String username;
    private String password;

    public Credential() {}

    public Credential(Integer credentialid, Integer userId) {
        this.credentialid = credentialid;
        this.userId = userId;
    }

    public Credential(String url, String username, String password, Integer userId) {
        this.url = url;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Credential(Integer credentialid, String url, String username, String password, Integer userId) {
        this.credentialid = credentialid;
        this.url = url;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Credential(Integer credentialid, String key, String url, String username, String password, Integer userId) {
        this.credentialid = credentialid;
        this.key = key;
        this.url = url;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return credentialid;
    }

    public void setId(Integer id) {
        this.credentialid = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
