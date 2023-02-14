package com.biplav.projectmanager.controller;

import com.biplav.projectmanager.dto.StatsUser;
import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/stats/task")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<TaskResponse>> getTasksUsername(@RequestBody StatsUser user)
    {
        return ResponseEntity.ok(statsService.getTasksByUsername(user.username()));
    }

    @PostMapping("/stats/week")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<TaskResponse>> getTasksWeek(@RequestBody StatsUser user)
    {
        return ResponseEntity.ok(statsService.getTasksThisWeek(user.username()));
    }

    @PostMapping("/stats/due")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<TaskResponse>> getOverdue(@RequestBody StatsUser user)
    {
        return ResponseEntity.ok(statsService.getOverdueTasks(user.username()));
    }
}
