import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa } from '../../models/tarefa.model';
import { FormularioTarefaComponent } from '../formulario-tarefa/formulario-tarefa.component';

@Component({
  selector: 'app-lista-tarefas',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatTooltipModule,
    FormularioTarefaComponent
  ],
  templateUrl: './lista-tarefas.component.html',
  styleUrls: ['./lista-tarefas.component.scss']
})
export class ListaTarefasComponent implements OnInit {
  tarefas: Tarefa[] = [];
  loading = false;
  mostrarFormulario = false;
  tarefaParaEditar?: Tarefa;

  constructor(
    private tarefaService: TarefaService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.carregarTarefas();
  }

  carregarTarefas(): void {
    this.loading = true;
    this.tarefaService.listarTodas().subscribe({
      next: (tarefas) => {
        this.tarefas = tarefas;
        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao carregar tarefas:', error);
        this.loading = false;
        
        let mensagemErro = '❌ Erro ao carregar tarefas!';
        if (error.status === 0) {
          mensagemErro = '🔌 Backend não está respondendo. Verifique se está rodando na porta 8080.';
        } else if (error.status === 404) {
          mensagemErro = '🔍 Endpoint não encontrado. Verifique a URL da API.';
        } else if (error.status === 500) {
          mensagemErro = '⚠️ Erro no servidor. Verifique os logs do backend.';
        }
        
        this.mostrarMensagem(mensagemErro, 'error');
      }
    });
  }

  abrirFormulario(tarefa?: Tarefa): void {
    this.tarefaParaEditar = tarefa;
    this.mostrarFormulario = true;
  }

  fecharFormulario(): void {
    this.mostrarFormulario = false;
    this.tarefaParaEditar = undefined;
  }

  alternarStatus(tarefa: Tarefa): void {
    if (!tarefa.id) {
      this.mostrarMensagem('⚠️ Tarefa inválida. ID não encontrado.', 'error');
      return;
    }

    this.tarefaService.alternarStatus(tarefa.id).subscribe({
      next: () => {
        this.carregarTarefas();
        const novoStatus = !tarefa.concluida;
        const mensagem = novoStatus 
          ? '✅ Tarefa marcada como concluída!' 
          : '🔄 Tarefa marcada como pendente!';
        this.mostrarMensagem(mensagem, 'success');
      },
      error: (error) => {
        console.error('Erro ao alterar status:', error);
        
        let mensagemErro = '❌ Erro ao alterar status da tarefa!';
        if (error.status === 404) {
          mensagemErro = '🔍 Tarefa não encontrada. Pode ter sido deletada.';
          this.carregarTarefas(); // Recarrega a lista
        } else if (error.status === 0) {
          mensagemErro = '🔌 Sem conexão com o servidor.';
        }
        
        this.mostrarMensagem(mensagemErro, 'error');
      }
    });
  }

  deletar(id?: number): void {
    if (!id) {
      this.mostrarMensagem('⚠️ Tarefa inválida. Não é possível deletar.', 'error');
      return;
    }

    if (!confirm('🗑️ Tem certeza que deseja deletar esta tarefa?\n\nEsta ação não pode ser desfeita!')) {
      return;
    }

    this.tarefaService.deletar(id).subscribe({
      next: () => {
        this.carregarTarefas();
        this.mostrarMensagem('🗑️ Tarefa deletada com sucesso!', 'success');
      },
      error: (error) => {
        console.error('Erro ao deletar tarefa:', error);
        
        let mensagemErro = '❌ Erro ao deletar tarefa!';
        if (error.status === 404) {
          mensagemErro = '🔍 Tarefa não encontrada. Pode já ter sido deletada.';
          this.carregarTarefas(); // Recarrega a lista
        } else if (error.status === 403) {
          mensagemErro = '🚫 Você não tem permissão para deletar esta tarefa.';
        } else if (error.status === 0) {
          mensagemErro = '🔌 Sem conexão com o servidor.';
        } else if (error.status === 500) {
          mensagemErro = '⚠️ Erro no servidor ao tentar deletar.';
        }
        
        this.mostrarMensagem(mensagemErro, 'error');
      }
    });
  }

  onTarefaSalva(): void {
    this.fecharFormulario();
    this.carregarTarefas();
    this.mostrarMensagem('Tarefa salva com sucesso!', 'success');
  }

  private mostrarMensagem(mensagem: string, tipo: 'success' | 'error'): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: tipo === 'error' ? 5000 : 3000, // Erros ficam mais tempo na tela
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: tipo === 'success' ? 'snackbar-success' : 'snackbar-error'
    });
  }
}
