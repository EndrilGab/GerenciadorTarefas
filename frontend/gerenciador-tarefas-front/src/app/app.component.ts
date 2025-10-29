import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule],
  template: `
    <mat-toolbar color="primary">
      <span>📝 Gerenciador de Tarefas</span>
    </mat-toolbar>
    <router-outlet></router-outlet>
  `,
  styles: [`
    mat-toolbar {
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
  `]
})
export class AppComponent {
  title = 'gerenciador-tarefas-front';
}
