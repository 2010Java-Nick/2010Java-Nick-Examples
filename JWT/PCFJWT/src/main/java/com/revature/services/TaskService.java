package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Task;
import com.revature.repos.TaskRepo;

@Service
public class TaskService {
	@Autowired
	public TaskRepo taskRepository;
	
	@Transactional(readOnly=true)
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}

}
