package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileId;
    private String filename;
    private byte[] filedata;
    private long filesize;
    private Integer userid;
    private String contenttype;

    public File() {}

    public File(Integer fileId, Integer userId) {
        this.fileId = fileId;
        this.userid = userId;
    }

    public File(String name, long size, String contentType, byte[] data, Integer userId) {
        this.filename = name;
        this.filedata = data;
        this.filesize = size;
        this.userid = userId;
        this.contenttype = contentType;
    }

    public long getSize() {
        return filesize;
    }

    public void setSize(long size) {
        this.filesize = size;
    }

    public byte[] getData() {
        return filedata;
    }

    public void setData(byte[] data) {
        this.filedata = data;
    }

    public String getContentType() {
        return contenttype;
    }

    public void setContentType(String contentType) {
        this.contenttype = contentType;
    }

    public Integer getId() {
        return fileId;
    }

    public void setId(Integer id) {
        this.fileId = id;
    }

    public String getName() {
        return filename;
    }

    public void setName(String name) {
        this.filename = name;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

}
