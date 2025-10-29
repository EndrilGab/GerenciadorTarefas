import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Tarefa, TarefaRequest } from '../models/tarefa.model';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  private apiUrl = 'http://localhost:8080/api/v1/tarefas';

  constructor(private http: HttpClient) {}

  listarTodas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  buscarPorId(id: number): Observable<Tarefa> {
    return this.http.get<Tarefa>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  criar(tarefa: TarefaRequest): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.apiUrl, tarefa)
      .pipe(catchError(this.handleError));
  }

  atualizar(id: number, tarefa: TarefaRequest): Observable<Tarefa> {
    return this.http.put<Tarefa>(`${this.apiUrl}/${id}`, tarefa)
      .pipe(catchError(this.handleError));
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  alternarStatus(id: number): Observable<Tarefa> {
    return this.http.patch<Tarefa>(`${this.apiUrl}/${id}/toggle`, {})
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Ocorreu um erro desconhecido!';
    
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Erro: ${error.error.message}`;
    } else {
      errorMessage = `Código: ${error.status}\nMensagem: ${error.message}`;
    }
    
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
