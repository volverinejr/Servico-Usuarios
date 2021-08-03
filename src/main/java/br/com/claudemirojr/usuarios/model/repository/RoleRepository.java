package br.com.claudemirojr.usuarios.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.claudemirojr.usuarios.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Page<Role> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Role> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
