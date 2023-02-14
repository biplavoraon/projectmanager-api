package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.model.Task;

public class TaskToDto {
    private final Task task;

    public TaskToDto(Task task) {
        this.task = task;
    }
    public static TaskResponse taskToDto(Task task)
    {
        int taskProgress = (100 * (task.getHoursAssigned() - task.getHoursLeft())) / task.getHoursAssigned();
        String progress = Integer.toString(taskProgress) + "%";
        TaskResponse taskResponse = new TaskResponse(
                task.getTaskid(),
                task.getTitle(),
                task.getDescription(),
                task.getState(),
                task.getHoursAssigned(),
                task.getHoursLeft(),
                task.getPriority(),
                task.getDueDate(),
                progress,
                task.getUser().getUserid(),
                task.getUser().getName(),
                task.getUser().getUsername()
        );
        return taskResponse;
    }
}
