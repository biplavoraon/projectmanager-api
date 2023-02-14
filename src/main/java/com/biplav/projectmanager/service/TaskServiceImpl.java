package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.TaskRequest;
import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.exception.TaskNotFoundException;
import com.biplav.projectmanager.exception.UserNotFoundException;
import com.biplav.projectmanager.model.AppUser;
import com.biplav.projectmanager.model.Task;
import com.biplav.projectmanager.repository.TaskRepo;
import com.biplav.projectmanager.repository.UserRepo;
import com.biplav.projectmanager.utility.TaskPriority;
import com.biplav.projectmanager.utility.TaskStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.biplav.projectmanager.service.TaskToDto.taskToDto;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskServiceImpl(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) throws UserNotFoundException {
        AppUser user = userRepo.findById(taskRequest.getUserid())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + taskRequest.getUserid() + " does not exist"));
        ZonedDateTime date = taskRequest.getDueDate().withZoneSameInstant(ZoneId.systemDefault());
        LocalDate localDate = date.toLocalDate();

        Task task = new Task(
                taskRequest.getTaskid(),
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                taskRequest.getState(),
                taskRequest.getHoursAssigned(),
                taskRequest.getHoursLeft(),
                taskRequest.getPriority(),
                localDate,
                user
        );
        return taskToDto(taskRepo.save(task));
    }

    @Override
    public Page<TaskResponse> getTasksWithPagination(int offset, int pageSize) {
        Page<Task> tasks = taskRepo.findAll(PageRequest.of(offset, pageSize));
        Page<TaskResponse> taskResponses = tasks.map(TaskToDto::taskToDto);
        return taskResponses;
    }


    @Override
    public TaskResponse getTaskById(int id) throws TaskNotFoundException {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task with id: " + id + " not found"
                ));
        return taskToDto(task);
    }

    public Task findTaskById(int id) throws TaskNotFoundException {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task with id: " + id + " not found"
                ));
        return task;
    }

    @Override
    public String deleteTask(int id) {
        taskRepo.deleteById(id);
        return ("Task deleted");
    }

    @Override
    public List<String> getPriorityList() {
        List<String> priorityList = Stream
                .of(TaskPriority.values())
                .map(TaskPriority::name)
                .collect(Collectors.toList());
        return priorityList;
    }

    @Override
    public List<String> getStateList() {
        List<String> stateList = Stream
                .of(TaskStates.values())
                .map(TaskStates::name)
                .collect(Collectors.toList());
        return stateList;
    }

    @Override
    public TaskResponse updateHourOrState(TaskRequest taskRequest, int id) throws TaskNotFoundException {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task with id: " + id + " not found"
                ));
        task.setState(taskRequest.getState());
        task.setHoursLeft(taskRequest.getHoursLeft());
        TaskResponse taskResponse = taskToDto(taskRepo.save(task));
        return taskResponse;
    }

}
