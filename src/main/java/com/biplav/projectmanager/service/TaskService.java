package com.biplav.projectmanager.service;

import java.util.List;

import com.biplav.projectmanager.dto.TaskRequest;
import com.biplav.projectmanager.dto.TaskResponse;
import com.biplav.projectmanager.exception.TaskNotFoundException;
import com.biplav.projectmanager.exception.UserNotFoundException;
import com.biplav.projectmanager.model.Task;
import org.springframework.data.domain.Page;

public interface TaskService 
{
	TaskResponse saveTask(TaskRequest task) throws UserNotFoundException;
	Page<TaskResponse> getTasksWithPagination(int offset, int pageSize);
	TaskResponse getTaskById(int id) throws TaskNotFoundException;
	Task findTaskById(int id) throws TaskNotFoundException;
	String deleteTask(int id);
	List<String> getPriorityList();
	List<String> getStateList();

	TaskResponse updateHourOrState(TaskRequest taskRequest, int id) throws TaskNotFoundException;
}
