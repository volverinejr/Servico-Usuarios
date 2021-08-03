package br.com.claudemirojr.usuarios.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.claudemirojr.usuarios.dto.IQueryGeralComunDto;
import br.com.claudemirojr.usuarios.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findByIdGreaterThanEqual(Long id, Pageable pageable);

	Page<Usuario> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

	Optional<Usuario> findByUsername(String username);
	
	
	@Query(
			value = "\r\n"
					+ "SELECT	r.id,\r\n"
					+ "			r.nome,\r\n"
					+ "			case\r\n"
					+ "				when roles.role_id IS NULL then 0 \r\n"
					+ "				ELSE 1 \r\n"
					+ "			END AS cadastrado \r\n"
					+ "			\r\n"
					+ "FROM role AS r\r\n"
					+ "	LEFT JOIN (\r\n"
					+ "		SELECT ur.*\r\n"
					+ "		FROM usuario_role AS ur\r\n"
					+ "		WHERE ur.usuario_id = ?1 \r\n"
					+ "	) AS roles ON ( r.id = roles.role_id )"
					+ "",
			countQuery="SELECT COUNT(id) FROM role",
			nativeQuery = true)
	Page<IQueryGeralComunDto> findByRolesDoUsuario(Long idUsuario, Pageable pageable);
	
	
	@Query(
			value = "\r\n"
					+ "SELECT 	p.id,\r\n"
					+ "			p.nome,\r\n"
					+ "			case\r\n"
					+ "				when paginas.pagina_id IS NULL then 0 \r\n"
					+ "				ELSE 1 \r\n"
					+ "			END AS cadastrado \r\n"
					+ "			\r\n"
					+ "FROM pagina AS p\r\n"
					+ "		LEFT JOIN (\r\n"
					+ "			SELECT up.pagina_id\r\n"
					+ "			FROM usuario_pagina AS up\r\n"
					+ "			WHERE up.usuario_id = ?1 \r\n"
					+ "		) AS paginas ON ( p.id = paginas.pagina_id )\r\n"
					+ "WHERE p.sistema_id = ?2 \r\n"
					+ "",
			countQuery="SELECT COUNT(id) FROM pagina where sistema_id = ?2",
			nativeQuery = true)
	Page<IQueryGeralComunDto> findByPaginasDoUsuario(Long idUsuario, Long idSistema, Pageable pageable);
	

}
