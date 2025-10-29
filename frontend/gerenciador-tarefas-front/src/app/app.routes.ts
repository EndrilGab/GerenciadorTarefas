import { Routes } from '@angular/router';
import { ListaTarefasComponent } from './components/lista-tarefas/lista-tarefas.component';

export const routes: Routes = [
  { path: '', component: ListaTarefasComponent },
  { path: '**', redirectTo: '' }
];
