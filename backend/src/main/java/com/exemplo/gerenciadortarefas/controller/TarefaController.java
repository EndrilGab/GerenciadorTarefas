package com.exemplo.gerenciadortarefas.controller;

import com.exemplo.gerenciadortarefas.dto.TarefaRequestDTO;
import com.exemplo.gerenciadortarefas.dto.TarefaResponseDTO;
import com.exemplo.gerenciadortarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista com todas as tarefas cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodas() {
        List<TarefaResponseDTO> tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        TarefaResponseDTO tarefa = tarefaService.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @Operation(summary = "Criar nova tarefa", description = "Cria uma nova tarefa no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@Valid @RequestBody TarefaRequestDTO dto) {
        TarefaResponseDTO tarefa = tarefaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza uma tarefa existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TarefaRequestDTO dto) {
        TarefaResponseDTO tarefa = tarefaService.atualizar(id, dto);
        return ResponseEntity.ok(tarefa);
    }

    @Operation(summary = "Deletar tarefa", description = "Remove uma tarefa do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Alternar status da tarefa", description = "Marca uma tarefa como concluída ou pendente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status alterado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TarefaResponseDTO> alternarStatus(@PathVariable Long id) {
        TarefaResponseDTO tarefa = tarefaService.alternarStatus(id);
        return ResponseEntity.ok(tarefa);
    }
}
