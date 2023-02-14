package com.biplav.projectmanager.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskid;
    private String title;
    private String description;
    private String state;
    private int hoursAssigned;
    private int hoursLeft;
    private String priority;
    private LocalDate dueDate;
    @ManyToOne
    private AppUser user;

    public Task() {
    }

    public Task(int taskid, String title, String description, String state, int hoursAssigned, int hoursLeft, String priority, LocalDate dueDate, AppUser user) {
        this.taskid = taskid;
        this.title = title;
        this.description = description;
        this.state = state;
        this.hoursAssigned = hoursAssigned;
        this.hoursLeft = hoursLeft;
        this.priority = priority;
        this.dueDate = dueDate;
        this.user = user;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", hoursAssigned=" + hoursAssigned +
                ", hoursLeft=" + hoursLeft +
                ", priority='" + priority + '\'' +
                ", dueDate=" + dueDate +
                ", user=" + user +
                '}';
    }
}
