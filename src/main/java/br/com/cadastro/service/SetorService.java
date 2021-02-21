package br.com.cadastro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cadastro.model.Setor;
import br.com.cadastro.repository.SetorRepository;
import br.com.cadastro.exceptions.DuplicatedResourceException;
import br.com.cadastro.exceptions.BusinessException;



@Service
public class SetorService extends ServiceBase<Setor, SetorRepository> {
	
	
	public List<Setor> obterPorNome(String nome) {
		return repository.findByNameContainingIgnoreCase(nome);
	}
	
	@Override
	public Setor salvar(Setor setor) {
		var podeSalvar = setor.getId() == null && repository.findById(setor.getId()).isEmpty();
		if(podeSalvar) return super.salvar(setor);
		throw new DuplicatedResourceException();
	}
	
	@Override
	public Setor editar(Setor setor) {
		var podeEditar = repository.findById(setor.getId()).isPresent();
		if(podeEditar) return super.editar(setor);
		throw new BusinessException("Não é possível fazer alteração!");		
	}

}
