import { Injectable } from '@angular/core';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { environment } from '../../environments/environment';
import { Task } from '../domains/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  getAllTasks() {
    return this.httpClient.get<Array<Task>>(environment.taskUrl);
  }

  constructor(private httpClient: HttpClient) { }
}
