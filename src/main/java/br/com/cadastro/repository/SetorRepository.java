package br.com.cadastro.repository;

import java.util.List;
import java.util.Optional;

import br.com.cadastro.model.Setor;



public interface SetorRepository extends RepositoryBase<Setor> {
	Optional<Setor> findById(Long id);
	Optional<Setor>findByNameIgnoreCase(String name);
	List<Setor> findByNameContainingIgnoreCase(String name);


}
