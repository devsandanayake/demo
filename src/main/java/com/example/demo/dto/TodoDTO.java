package com.example.demo.dto;

public class TodoDTO {
    private String des;
    private boolean completed;

    // Getters and Setters
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}