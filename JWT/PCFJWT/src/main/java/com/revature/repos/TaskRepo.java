package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.entities.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{

}
