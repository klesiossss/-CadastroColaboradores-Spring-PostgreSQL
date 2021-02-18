package br.com.cadastro.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cadastro.exceptions.ResourceNotFoundException;
import br.com.cadastro.model.EntityBase;
import br.com.cadastro.repository.RepositoryBase;


  
public abstract class ServiceBase<E extends EntityBase, R extends RepositoryBase<E>> {
	
	@Autowired
	protected R repository;
	
	public E obterPorId(Long id) {
		return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<E> obterTodos() {
		return repository.findByAtivoTrue();
	}
	
	public List<E> obterTodos(Example<E> example) {
		return repository.findAll(example);
	}
	
	public Page<E> obterTodos(Pageable pageable) {
		return repository.findByAtivoTrue(pageable);
	}	
	
	public E salvar(E entity) {
		entity.setAtivo(true);
		entity.setCriadoEm(LocalDateTime.now());
		return repository.save(entity);
	}
	
	public E editar(E entity) {
		entity.setAtualizadoEm(LocalDateTime.now());
		return repository.save(entity);
	}
	
	public void remover(E entity) {
		var e = repository.findById(entity.getId()).orElseThrow(ResourceNotFoundException::new);
		repository.deleteById(e.getId());
	}

}