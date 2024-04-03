import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css',
})
export class TaskListComponent {
  @Input() taskList: any[] = [];
  @Output() important = new EventEmitter<any>();
  @Output() edit = new EventEmitter<any>();
  @Output() completed = new EventEmitter<any>();
  @Output() deleted = new EventEmitter<any>();

  markImportant(task: any) {
    this.important.emit(task);
  }

  markCompleted(task: any) {
    this.completed.emit(task);
  }

  deleteTask(task: any) {
    this.deleted.emit(task);
  }

  editTask(task: any) {
    this.edit.emit(task);
  }

}
