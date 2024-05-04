package com.example.androidptit_th2.model;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String name;
    private String content;
    private String date;
    private String status;
    private boolean collaborate;

    public Song() {
    }

    public Song(int id, String name, String content, String date, String status, boolean collaborate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public Song(String name, String content, String date, String status, boolean collaborate) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCollaborate() {
        return collaborate;
    }

    public void setCollaborate(boolean collaborate) {
        this.collaborate = collaborate;
    }
}
