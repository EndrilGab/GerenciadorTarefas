package com.exemplo.gerenciadortarefas.service;

import com.exemplo.gerenciadortarefas.dto.TarefaRequestDTO;
import com.exemplo.gerenciadortarefas.dto.TarefaResponseDTO;
import com.exemplo.gerenciadortarefas.exception.ResourceNotFoundException;
import com.exemplo.gerenciadortarefas.model.Tarefa;
import com.exemplo.gerenciadortarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public List<TarefaResponseDTO> listarTodas() {
        log.info("Listando todas as tarefas");
        return tarefaRepository.findAll()
                .stream()
                .map(TarefaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TarefaResponseDTO buscarPorId(Long id) {
        log.info("Buscando tarefa com ID: {}", id);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com ID: " + id));
        return new TarefaResponseDTO(tarefa);
    }

    @Transactional
    public TarefaResponseDTO criar(TarefaRequestDTO dto) {
        log.info("Criando nova tarefa: {}", dto.getTitulo());
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setConcluida(dto.getConcluida() != null ? dto.getConcluida() : false);
        
        Tarefa salva = tarefaRepository.save(tarefa);
        log.info("Tarefa criada com sucesso. ID: {}", salva.getId());
        return new TarefaResponseDTO(salva);
    }

    @Transactional
    public TarefaResponseDTO atualizar(Long id, TarefaRequestDTO dto) {
        log.info("Atualizando tarefa com ID: {}", id);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com ID: " + id));

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        if (dto.getConcluida() != null) {
            tarefa.setConcluida(dto.getConcluida());
        }

        Tarefa atualizada = tarefaRepository.save(tarefa);
        log.info("Tarefa atualizada com sucesso. ID: {}", atualizada.getId());
        return new TarefaResponseDTO(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        log.info("Deletando tarefa com ID: {}", id);
        if (!tarefaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa não encontrada com ID: " + id);
        }
        tarefaRepository.deleteById(id);
        log.info("Tarefa deletada com sucesso. ID: {}", id);
    }

    @Transactional
    public TarefaResponseDTO alternarStatus(Long id) {
        log.info("Alternando status da tarefa com ID: {}", id);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com ID: " + id));

        tarefa.setConcluida(!tarefa.isConcluida());
        Tarefa atualizada = tarefaRepository.save(tarefa);
        log.info("Status alterado. Tarefa ID: {} - Concluída: {}", atualizada.getId(), atualizada.isConcluida());
        return new TarefaResponseDTO(atualizada);
    }
}
