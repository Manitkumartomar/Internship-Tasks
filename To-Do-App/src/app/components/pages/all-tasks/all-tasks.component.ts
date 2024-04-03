import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpService } from '../../../services/http.service';
import { PageTitleComponent } from '../../page-title/page-title.component';
import { TaskListComponent } from '../../task-list/task-list.component';
import { StateService } from '../../../services/state.service';

@Component({
  selector: 'app-all-tasks',
  standalone: true,
  imports: [
    FormsModule,
    PageTitleComponent,
    TaskListComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './all-tasks.component.html',
  styleUrl: './all-tasks.component.css',
})
export class AllTasksComponent {
  newTask = '';
  httpService = inject(HttpService);
  stateService = inject(StateService);
  initialtaskList: any[] = [];
  taskList: any[] = [];
  task = new FormControl('', [Validators.required]);
  submitBtn = new FormGroup({
    task: this.task,
  });

  ngOnInit() {
    this.stateService.searchSubject.subscribe((value) => {
      if (value) {
        this.taskList = this.initialtaskList.filter((x) =>
          x.title.toLowerCase().includes(value.toLowerCase())
        );
      } else {
        this.taskList = this.initialtaskList;
      }
    });
    this.getAllTasks();
  }

  addTask() {
    console.log('Task Added', this.newTask);
    this.httpService.addTask(this.newTask).subscribe(() => {
      this.newTask = '';
      this.getAllTasks();
    });
  }

  getAllTasks() {
    this.httpService.getAllTasks().subscribe((result: any) => {
      this.initialtaskList = this.taskList = result;
    });
  }

  onEdit(task: any){
    const newTitle = prompt("Do changes: ", task.title);
    if(newTitle!==null){
      task.title = newTitle;
      this.httpService.updateTask(task).subscribe(() => {
        this.getAllTasks();
      });
    }
  }

  onComplete(task: any) {
    task.completed = !task.completed;
    this.httpService.updateTask(task).subscribe(() => {
      this.getAllTasks();
    });
  }

  onImportant(task: any) {
    task.important = !task.important;
    this.httpService.updateTask(task).subscribe(() => {
      this.getAllTasks();
    });
  }

  onDeleted(task: any) {
    this.httpService.deleteTask(task).subscribe(() => {
      this.getAllTasks();
    });
  }
}
