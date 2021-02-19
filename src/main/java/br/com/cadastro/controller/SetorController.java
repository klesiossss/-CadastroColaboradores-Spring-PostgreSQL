package br.com.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastro.model.setor.Setor;
import br.com.cadastro.service.setor.SetorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/setor")
@AllArgsConstructor
public class SetorController {
	
	
	private SetorService setorService;
	
	@GetMapping
	public ResponseEntity<Page<Setor>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var colaborador = setorService.obterTodos(pageable);
		return ResponseEntity.ok(colaborador);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Setor> obterPorId(@PathVariable Long id) {
		var aluno = setorService.obterPorId(id);
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Setor>> obterPorNome(@PathVariable String nome) {
		var colaborador = setorService.obterPorNome(nome);
		return ResponseEntity.ok(colaborador);
	}	
	
	
	@PostMapping
	public ResponseEntity<Setor> salvar(@RequestBody @Valid Setor setor) {
		var setorSalvo = setorService.salvar(setor);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(setorSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(setorSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Setor> editar(@RequestBody @Valid Setor setor) {
		var setorSalvo = setorService.editar(setor);
		return ResponseEntity.ok(setorSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<Setor> remover(@RequestBody @Valid Setor setor) {
		setorService.remover(setor);
		return ResponseEntity.ok().build();
	}
	

	


}