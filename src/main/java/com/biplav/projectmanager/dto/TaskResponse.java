package com.biplav.projectmanager.dto;

import java.time.LocalDate;

public class TaskResponse {
    private int taskid;
    private String title;
    private String description;
    private String state;
    private int hoursAssigned;
    private int hoursLeft;
    private String priority;
    private LocalDate dueDate;
    private String progress;
    private int userid;
    private String name;
    private String username;

    public TaskResponse(int taskid, String title, String description, String state, int hoursAssigned, int hoursLeft, String priority, LocalDate dueDate, String progress, int userid, String name, String username) {
        this.taskid = taskid;
        this.title = title;
        this.description = description;
        this.state = state;
        this.hoursAssigned = hoursAssigned;
        this.hoursLeft = hoursLeft;
        this.priority = priority;
        this.dueDate = dueDate;
        this.progress = progress;
        this.userid = userid;
        this.name = name;
        this.username = username;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getHoursAssigned() {
        return hoursAssigned;
    }

    public void setHoursAssigned(int hoursAssigned) {
        this.hoursAssigned = hoursAssigned;
    }

    public int getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(int hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
