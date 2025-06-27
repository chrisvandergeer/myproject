import { bootstrapApplication } from '@angular/platform-browser-dynamic';
import { Component } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { Routes } from '@angular/router';
import { provideRouter, RouterOutlet, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'greeting-component',
  standalone: true,
  imports: [FormsModule, RouterLink],
  template: `
    <form (ngSubmit)="submit()">
      <label for="name">Name:</label>
      <input id="name" name="name" [(ngModel)]="name" required pattern="[A-Za-z\\- ]{2,}">
      <button type="submit">Greet</button>
    </form>
    <p *ngIf="message">{{message}}</p>
    <p *ngIf="error" style="color:#cc0000">{{error}}</p>
  `
})
class GreetingComponent {
  name = '';
  message = '';
  error = '';
  constructor(private http: import('@angular/common/http').HttpClient) {}
  submit() {
    this.http.post('/api/greeting', {name: this.name}).subscribe({
      next: (res) => { this.message = res.message; this.error = ''; },
      error: (err) => { this.error = err.error; this.message = ''; }
    });
  }
}

@Component({
  selector: 'tasks-component',
  standalone: true,
  imports: [FormsModule, RouterLink],
  template: `
    <form (ngSubmit)="addTask()">
      <label>Naam:</label><input [(ngModel)]="task.name" name="name" required>
      <label>Omschrijving:</label><input [(ngModel)]="task.description" name="description" required>
      <label>Einddatum:</label><input type="date" [(ngModel)]="task.endDate" name="endDate" required>
      <button type="submit">Voeg taak toe</button>
    </form>
    <table *ngIf="tasks.length > 0">
      <thead><tr><th>Naam</th><th>Omschrijving</th><th>Einddatum</th></tr></thead>
      <tbody>
        <tr *ngFor="let t of tasks">
          <td>{{t.name}}</td><td>{{t.description}}</td><td>{{t.endDate}}</td>
        </tr>
      </tbody>
    </table>
  `
})
class TasksComponent {
  tasks = [];
  task = {name:'', description:'', endDate:''};
  constructor(private http: import('@angular/common/http').HttpClient) { this.load(); }
  load() { this.http.get('/api/tasks').subscribe((res) => this.tasks = res); }
  addTask() {
    this.http.post('/api/tasks', this.task).subscribe(() => { this.load(); this.task = {name:'', description:'', endDate:''}; });
  }
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <nav>
      <a routerLink="/greeting">Greeting</a> |
      <a routerLink="/tasks">Taken</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
class AppComponent {}

const routes = [
  {path: '', redirectTo: 'greeting', pathMatch: 'full'},
  {path: 'greeting', component: GreetingComponent},
  {path: 'tasks', component: TasksComponent}
];

bootstrapApplication(AppComponent, {
  providers: [provideHttpClient(), provideRouter(routes)]
});
