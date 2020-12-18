package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Task;
import com.revature.services.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService ts;

	@GetMapping("/tasks")
	@PreAuthorize("hasAuthority('read')")
	public ResponseEntity<List<Task>> listAllTakes(){
		return new ResponseEntity<>((List<Task>) ts.getAllTasks(), HttpStatus.OK);
	}
	
}
