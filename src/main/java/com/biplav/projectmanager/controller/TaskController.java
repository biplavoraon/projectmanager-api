package com.biplav.projectmanager.controller;

import com.biplav.projectmanager.dto.TaskRequest;
import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.exception.TaskNotFoundException;
import com.biplav.projectmanager.exception.UserNotFoundException;
import com.biplav.projectmanager.model.Task;
import com.biplav.projectmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController
{
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping("/task")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<TaskResponse> addTask(@RequestBody @Valid TaskRequest task) throws UserNotFoundException
	{
		return new ResponseEntity<>(taskService.saveTask(task), HttpStatus.CREATED);
	}

	@GetMapping("/task/{pageSize}/{offset}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<Page<TaskResponse>> getTasks(@PathVariable("pageSize") int pageSize, @PathVariable("offset") int offset)
	{
		return ResponseEntity.ok(taskService.getTasksWithPagination(offset, pageSize));
	}

	@GetMapping("/task/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") int id) throws TaskNotFoundException
	{
		return ResponseEntity.ok(taskService.getTaskById(id));
	}

	@GetMapping("/task/priority")
	public ResponseEntity<List<String>> getPriority()
	{
		return ResponseEntity.ok(taskService.getPriorityList());
	}

	@GetMapping("/task/states")
	public ResponseEntity<List<String>> getState()
	{
		return ResponseEntity.ok(taskService.getStateList());
	}

	@DeleteMapping("task/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteTask(@PathVariable("id") int id)
	{
		taskService.deleteTask(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/task/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") int id,
						   @RequestBody @Valid TaskRequest taskRequest) throws TaskNotFoundException, UserNotFoundException
	{
		Task task = taskService.findTaskById(id);
		taskRequest.setTaskid(task.getTaskid());
		return ResponseEntity.ok(taskService.saveTask(taskRequest));
	}
	@PutMapping("/hour/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<TaskResponse> updateHourOrState(@PathVariable("id") int id,
						   @RequestBody @Valid TaskRequest taskRequest) throws TaskNotFoundException, UserNotFoundException
	{
		return ResponseEntity.ok(taskService.updateHourOrState(taskRequest, id));
	}
}
