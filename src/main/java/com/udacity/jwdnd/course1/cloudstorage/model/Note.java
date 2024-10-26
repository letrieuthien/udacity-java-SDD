package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private Integer noteId;
    private String notetitle;
    private Integer userId;
    private String notedescription;

    public Note() {}

    public Note(Integer id, Integer userId) {
        this.noteId = id;
        this.userId = userId;
    }

    public Note(Integer noteId, String notetitle, String notedescription, Integer userId) {
        this.noteId = noteId;
        this.notetitle = notetitle;
        this.userId = userId;
        this.notedescription = notedescription;
    }

    public Integer getId() {
        return noteId;
    }

    public void setId(Integer id) {
        this.noteId = id;
    }

    public String getTitle() {
        return notetitle;
    }

    public void setTitle(String title) {
        this.notetitle = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return notedescription;
    }

    public void setDescription(String description) {
        this.notedescription = description;
    }

}
