package br.com.cadastro.service.colaborador;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cadastro.model.colaborador.Colaborador;
import br.com.cadastro.repository.colaborador.ColaboradorRepository;
import br.com.cadastro.service.ServiceBase;
import br.com.cadastro.exceptions.*;

import br.com.cadastro.service.setor.*;



@Service
public class ColaboradorService extends ServiceBase<Colaborador, ColaboradorRepository> {
	
	public Colaborador obterPorCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<Colaborador> obterPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Override
	public Colaborador salvar(Colaborador colaborador) {
		var podeSalvar = colaborador.getId() == null && repository.findByCpf(colaborador.getCpf()).isEmpty();
		if(podeSalvar) return super.salvar(colaborador);
		throw new DuplicatedResourceException();
	}
	
	@Override
	public Colaborador editar(Colaborador colaborador) {
		var podeEditar = repository.findByCpf(colaborador.getCpf()).isPresent();
		if(podeEditar) return super.editar(colaborador);
		throw new BusinessException("Não é possível fazer alteração!");		
	}

		
}