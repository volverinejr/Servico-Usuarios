package br.com.claudemirojr.usuarios.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.claudemirojr.usuarios.model.entity.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {

	Page<Sistema> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Sistema> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
