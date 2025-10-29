package com.example.gerenciadortarefas;

import com.exemplo.gerenciadortarefas.dto.TarefaRequestDTO;
import com.exemplo.gerenciadortarefas.dto.TarefaResponseDTO;
import com.exemplo.gerenciadortarefas.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GerenciadortarefasApplicationTests {

	@Autowired
	private TarefaService tarefaService;

	@Test
	void contextLoads() {
		assertNotNull(tarefaService);
	}

	@Test
	void deveCriarTarefa() {
		TarefaRequestDTO dto = new TarefaRequestDTO();
		dto.setTitulo("Tarefa de Teste");
		dto.setDescricao("Descrição de teste");
		dto.setConcluida(false);

		TarefaResponseDTO tarefa = tarefaService.criar(dto);

		assertNotNull(tarefa);
		assertNotNull(tarefa.getId());
		assertEquals("Tarefa de Teste", tarefa.getTitulo());
		assertEquals("Descrição de teste", tarefa.getDescricao());
		assertFalse(tarefa.isConcluida());
	}

	@Test
	void deveListarTarefas() {
		var tarefas = tarefaService.listarTodas();
		assertNotNull(tarefas);
	}

	@Test
	void deveAtualizarTarefa() {
		// Criar tarefa
		TarefaRequestDTO dtoCreate = new TarefaRequestDTO();
		dtoCreate.setTitulo("Tarefa Original");
		dtoCreate.setDescricao("Descrição original");
		dtoCreate.setConcluida(false);

		TarefaResponseDTO criada = tarefaService.criar(dtoCreate);

		// Atualizar tarefa
		TarefaRequestDTO dtoUpdate = new TarefaRequestDTO();
		dtoUpdate.setTitulo("Tarefa Atualizada");
		dtoUpdate.setDescricao("Descrição atualizada");
		dtoUpdate.setConcluida(true);

		TarefaResponseDTO atualizada = tarefaService.atualizar(criada.getId(), dtoUpdate);

		assertEquals("Tarefa Atualizada", atualizada.getTitulo());
		assertEquals("Descrição atualizada", atualizada.getDescricao());
		assertTrue(atualizada.isConcluida());
	}

	@Test
	void deveAlternarStatusTarefa() {
		// Criar tarefa
		TarefaRequestDTO dto = new TarefaRequestDTO();
		dto.setTitulo("Tarefa para Toggle");
		dto.setDescricao("Teste de toggle");
		dto.setConcluida(false);

		TarefaResponseDTO criada = tarefaService.criar(dto);
		assertFalse(criada.isConcluida());

		// Alternar status
		TarefaResponseDTO alternada = tarefaService.alternarStatus(criada.getId());
		assertTrue(alternada.isConcluida());

		// Alternar novamente
		TarefaResponseDTO alternada2 = tarefaService.alternarStatus(criada.getId());
		assertFalse(alternada2.isConcluida());
	}

	@Test
	void deveDeletarTarefa() {
		// Criar tarefa
		TarefaRequestDTO dto = new TarefaRequestDTO();
		dto.setTitulo("Tarefa para Deletar");
		dto.setDescricao("Será deletada");
		dto.setConcluida(false);

		TarefaResponseDTO criada = tarefaService.criar(dto);
		Long id = criada.getId();

		// Deletar
		tarefaService.deletar(id);

		// Verificar que não existe mais
		assertThrows(Exception.class, () -> tarefaService.buscarPorId(id));
	}
}

