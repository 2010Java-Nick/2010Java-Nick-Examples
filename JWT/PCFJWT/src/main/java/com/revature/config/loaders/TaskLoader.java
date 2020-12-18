package com.revature.config.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.revature.entities.Task;
import com.revature.repos.TaskRepo;

@Component
public class TaskLoader implements ApplicationListener<ContextRefreshedEvent>{
	private boolean isSetUp = false;
	
	@Autowired
	private TaskRepo taskRepo;
	
	Logger logger = LoggerFactory.getLogger(TaskLoader.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		if(isSetUp){
			return;
		}
		isSetUp = true;
		Task paint = new Task();
		paint.setDescription("paint cabinets");
		Task mow = new Task();
		mow.setDescription("mow lawn");
		
		taskRepo.save(paint);
		taskRepo.save(mow);
		logger.info("added task with id: "+ mow.getId());
		logger.info("added task with id: "+ paint.getId());
		
	}
	
}
