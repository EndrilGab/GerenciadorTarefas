export interface Tarefa {
  id?: number;
  titulo: string;
  descricao?: string;
  concluida: boolean;
  criadoEm?: Date;
  atualizadoEm?: Date;
}

export interface TarefaRequest {
  titulo: string;
  descricao?: string;
  concluida?: boolean;
}
