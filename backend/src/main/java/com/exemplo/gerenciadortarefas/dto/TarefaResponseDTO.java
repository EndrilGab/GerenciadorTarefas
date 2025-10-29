package com.exemplo.gerenciadortarefas.dto;

import com.exemplo.gerenciadortarefas.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public TarefaResponseDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.concluida = tarefa.isConcluida();
        this.criadoEm = tarefa.getCriadoEm();
        this.atualizadoEm = tarefa.getAtualizadoEm();
    }
}
