package br.com.cadastro.repository.colaborador;

import java.util.List;
import java.util.Optional;

import br.com.cadastro.model.colaborador.Colaborador;
import br.com.cadastro.repository.RepositoryBase;



public interface ColaboradorRepository extends RepositoryBase<Colaborador> {
	Optional<Colaborador> findById(Long id);
	Optional<Colaborador> findByCpf(String cpf);
	List<Colaborador> findByNomeContainingIgnoreCase(String nome);
	List<Colaborador> findBySetorId(String name, Long cursoId);

}
