import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service'
import { Task } from '../datamodel/task';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  name = 'Task'
  tasks: Task[];
  constructor(private taskService: TaskService  ) { }

  ngOnInit() {
   this.getTasks();
  }

  getTasks(): void{
    this.taskService.getTasks('Tomasz').subscribe(tasks => this.tasks = tasks);
  }

}
