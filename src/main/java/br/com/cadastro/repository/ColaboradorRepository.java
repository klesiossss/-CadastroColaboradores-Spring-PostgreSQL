package br.com.cadastro.repository;

import java.util.List;
import java.util.Optional;

import br.com.cadastro.model.Colaborador;




public interface ColaboradorRepository extends RepositoryBase<Colaborador> {
	Optional<Colaborador> findById(Long id);
	Optional<Colaborador> findByCpf(String cpf);
	List<Colaborador> findByNameContainingIgnoreCase(String name);
	List<Colaborador> findBySetorId(Long setorId);

}
