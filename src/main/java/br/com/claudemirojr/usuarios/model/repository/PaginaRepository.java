package br.com.claudemirojr.usuarios.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.claudemirojr.usuarios.model.entity.Pagina;

public interface PaginaRepository extends JpaRepository<Pagina, Long> {

	Page<Pagina> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Pagina> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
