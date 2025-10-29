import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa, TarefaRequest } from '../../models/tarefa.model';

@Component({
  selector: 'app-formulario-tarefa',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './formulario-tarefa.component.html',
  styleUrls: ['./formulario-tarefa.component.scss']
})
export class FormularioTarefaComponent implements OnInit {
  @Input() tarefa?: Tarefa;
  @Output() fechar = new EventEmitter<void>();
  @Output() salvar = new EventEmitter<void>();

  formulario: FormGroup;
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private tarefaService: TarefaService
  ) {
    this.formulario = this.fb.group({
      titulo: ['', [Validators.required, Validators.maxLength(100)]],
      descricao: ['', Validators.maxLength(500)],
      concluida: [false]
    });
  }

  ngOnInit(): void {
    if (this.tarefa) {
      this.formulario.patchValue({
        titulo: this.tarefa.titulo,
        descricao: this.tarefa.descricao || '',
        concluida: this.tarefa.concluida
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    this.carregando = true;
    const tarefaRequest: TarefaRequest = this.formulario.value;

    const operacao = this.tarefa?.id
      ? this.tarefaService.atualizar(this.tarefa.id, tarefaRequest)
      : this.tarefaService.criar(tarefaRequest);

    operacao.subscribe({
      next: () => {
        this.salvar.emit();
        this.fecharFormulario();
      },
      error: (erro) => {
        console.error('Erro ao salvar tarefa:', erro);
        this.carregando = false;
      }
    });
  }

  fecharFormulario(): void {
    this.fechar.emit();
  }

  get titulo() {
    return this.formulario.get('titulo');
  }

  get descricao() {
    return this.formulario.get('descricao');
  }

  getMensagemErro(campo: string): string {
    const control = this.formulario.get(campo);
    if (control?.hasError('required')) {
      return 'Este campo é obrigatório';
    }
    if (control?.hasError('maxlength')) {
      const maxLength = control.errors?.['maxlength'].requiredLength;
      return `Máximo de ${maxLength} caracteres`;
    }
    return '';
  }
}
