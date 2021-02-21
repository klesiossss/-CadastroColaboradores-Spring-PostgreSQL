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

import br.com.cadastro.model.Colaborador;
import br.com.cadastro.service.ColaboradorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/colaborador")
@AllArgsConstructor
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping
	public ResponseEntity<Page<Colaborador>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var colaborador = colaboradorService.obterTodos(pageable);
		return ResponseEntity.ok(colaborador);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> obterPorId(@PathVariable Long id) {
		var aluno = colaboradorService.obterPorId(id);
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Colaborador> obterPorCpf(@PathVariable String cpf) {
		var colaborador = colaboradorService.obterPorCpf(cpf);
		return ResponseEntity.ok(colaborador);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Colaborador>> obterPorNome(@PathVariable String nome) {
		var colaborador = colaboradorService.obterPorNome(nome);
		return ResponseEntity.ok(colaborador);
	}	
	
	@PostMapping
	public ResponseEntity<Colaborador> save(@RequestBody @Valid Colaborador colaborador) {
		var colaboradorSalvo = colaboradorService.salvar(colaborador);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(colaboradorSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(colaboradorSalvo);
	}
	
	
	//@PostMapping
	//public ResponseEntity<Colaborador> salvar(@RequestBody @Valid Colaborador colaborador) {
		//var colaboradorSalvo = colaboradorService.salvar(colaborador);
		//var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(colaboradorSalvo.getId()).toUri();
		//return ResponseEntity.created(uri).body(colaboradorSalvo);
	//}
	
	@PutMapping
	public ResponseEntity<Colaborador> editar(@RequestBody @Valid Colaborador colaborador) {
		var colaboradorSalvo = colaboradorService.editar(colaborador);
		return ResponseEntity.ok(colaboradorSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<Colaborador> remover(@RequestBody @Valid Colaborador colaborador) {
		colaboradorService.remover(colaborador);
		return ResponseEntity.ok().build();
	}

}