package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.TaskResponse;
import java.util.List;

public interface StatsService {
    List<TaskResponse> getTasksByUsername(String username);

    List<TaskResponse> getTasksThisWeek(String username);

    List<TaskResponse> getOverdueTasks(String username);
}
