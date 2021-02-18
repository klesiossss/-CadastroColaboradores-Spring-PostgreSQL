package br.com.cadastro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryBase<T> extends JpaRepository<T, Long> {
	List<T> findByAtivoTrue();
	List<T> findByAtivoTrue(Sort sort);
	Page<T> findByAtivoTrue(Pageable pageable);

}