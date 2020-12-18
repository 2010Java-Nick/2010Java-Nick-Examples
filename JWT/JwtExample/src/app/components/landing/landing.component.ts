import { Component, OnInit } from '@angular/core';
import { Task } from '../../domains/Task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
  providers: [ TaskService ]
})
export class LandingComponent implements OnInit {

  tasks: Array<Task> = new Array<Task>();
  errorMessage: string;

  constructor(private taskService: TaskService) { }

  getTasks() {
    this.taskService.getAllTasks().subscribe(
      (tasks) => {
        this.tasks = tasks;
        console.log(this.tasks);
      },
      (error) => {
        console.error(error);
        this.errorMessage = `failed to get tasks`;
      }
    );
  }

  ngOnInit() {
  }

}
