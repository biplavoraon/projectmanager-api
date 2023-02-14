package com.biplav.projectmanager.dto;

import com.biplav.projectmanager.validator.ValidPriority;
import com.biplav.projectmanager.validator.ValidState;
import jakarta.validation.constraints.*;

import java.time.ZonedDateTime;

public class TaskRequest {
    private int taskid;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @ValidState(message = "State must be NEW, REQ_ANALYSIS, DESIGN, CODING, TESTING or CLOSED")
    private String state;
    @Positive
    private int hoursAssigned;
    @PositiveOrZero
    private int hoursLeft;
    @AssertTrue(message = "Hours left cannot be greater than the Hours assigned")
    private boolean isValid()
    {
        return hoursAssigned >= hoursLeft;
    }
    @ValidPriority(message = "Priority must be LOW, MEDIUM, HIGH, CRITICAL")
    private String priority;
    private ZonedDateTime dueDate;
    private int userid;

    public TaskRequest(int taskid, String title, String description, String state, int hoursAssigned, int hoursLeft, String priority, ZonedDateTime dueDate, int userid) {
        this.taskid = taskid;
        this.title = title;
        this.description = description;
        this.state = state;
        this.hoursAssigned = hoursAssigned;
        this.hoursLeft = hoursLeft;
        this.priority = priority;
        this.dueDate = dueDate;
        this.userid = userid;
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

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
