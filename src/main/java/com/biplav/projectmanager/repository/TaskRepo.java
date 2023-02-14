package com.biplav.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biplav.projectmanager.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> 
{

}
