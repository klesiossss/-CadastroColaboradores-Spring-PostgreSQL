package br.com.cadastro.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cadastro.exceptions.BusinessException;
import br.com.cadastro.exceptions.DuplicatedResourceException;
import br.com.cadastro.exceptions.ElementFoundInBlackListException;
import br.com.cadastro.exceptions.ResourceNotFoundException;
import br.com.cadastro.model.Colaborador;
import br.com.cadastro.repository.ColaboradorRepository;


@Service
public class ColaboradorService extends ServiceBase<Colaborador, ColaboradorRepository> {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	RestTemplate restTemplate = new RestTemplate();
	
	public Colaborador obterPorCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<Colaborador> obterPorNome(String nome) {
		return repository.findByNameContainingIgnoreCase(nome);
	}
	
	
	@Override
	public Colaborador editar(Colaborador colaborador) {
		var podeEditar = repository.findByCpf(colaborador.getCpf()).isPresent();
		if(podeEditar) return super.editar(colaborador);
		throw new BusinessException("Não é possível fazer alteração!");		
	}

	
	public Colaborador salvar(Colaborador colaborador) {
			
			if(colaboradorRepository.findByCpf(colaborador.getCpf()).isPresent()) throw new DuplicatedResourceException();
			
			String url = "https://5e74cb4b9dff120016353b04.mockapi.io/api/v1/blacklist";
			var resposta = Arrays.asList( restTemplate.getForObject(url, Colaborador[].class));			
			if(resposta.stream().filter(cpf ->cpf.getCpf().equals(colaborador.getCpf())).count() > 0) 
				throw new ElementFoundInBlackListException();
				
			var colaboradores = colaboradorRepository.findBySetorId(colaborador.getSetor().getId());
			double numTotalColaboradores = colaboradores.size();
			if(numTotalColaboradores == 0) return super.salvar(colaborador);
				
			double  numMenor18Anos = colaboradores.stream().filter(c -> c.getIdade() < 18).count();
		    double numMaior65Anos = colaboradores.stream().filter(c -> c.getIdade() > 65).count();	
		    double percentualMenor18Anos = (numMenor18Anos / numTotalColaboradores) * 100.0;
			double percentualMaior65Anos = (numMaior65Anos / numTotalColaboradores) * 100.0;
			
			if(colaborador.getIdade() > 18 && colaborador.getIdade() < 65) return super.salvar(colaborador);	
			else if (colaborador.getIdade() < 18 && percentualMenor18Anos < 20.0) return super.salvar(colaborador);
			else if(colaborador.getIdade() > 65 && percentualMaior65Anos < 20.0 ) return super.salvar(colaborador);
			else throw new BusinessException("There is no more place, the percentage allowed for you got exceded");	
			
			
							
	}
	
}