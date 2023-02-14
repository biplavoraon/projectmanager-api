package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.model.AppUser;
import com.biplav.projectmanager.model.Task;
import com.biplav.projectmanager.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService{
    private final UserRepo userRepo;

    public StatsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<TaskResponse> getTasksByUsername(String username) {
        AppUser user = userRepo.findByUsername(username);
        List<Task> tasks = user.getTasks();
        return tasks
                .stream()
                .map(TaskToDto::taskToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksThisWeek(String username) {
        int weekNumber = WeekNumber(LocalDate.now());
        return getTasksByUsername(username)
                .stream()
                .filter(task -> WeekNumber(task.getDueDate()) == weekNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getOverdueTasks(String username) {
        return getTasksByUsername(username)
                .stream()
                .filter(task -> task.getDueDate().compareTo(LocalDate.now()) < 0)
                .collect(Collectors.toList());
    }

    private int WeekNumber(LocalDate date)
    {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(woy);
    }
}
