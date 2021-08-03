package br.com.claudemirojr.usuarios.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.claudemirojr.usuarios.model.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	Page<Menu> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Menu> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
