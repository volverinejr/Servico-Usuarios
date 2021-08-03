package br.com.claudemirojr.usuarios.model.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.usuarios.dto.IQueryGeralComunDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAlterarPasswordDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAtualizarDto;
import br.com.claudemirojr.usuarios.dto.UsuarioDto;
import br.com.claudemirojr.usuarios.dto.UsuarioFullResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;

public interface UsuarioService {

	public UsuarioResponseDto criar(UsuarioDto usuarioCriarDto);

	public UsuarioResponseDto atualizar(Long id, UsuarioAtualizarDto usuarioAtualizarDto);

	public void delete(Long id);

	public Page<UsuarioResponseDto> findAll(ParamsRequestModel prm);

	public Page<UsuarioResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<UsuarioResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm);

	public UsuarioResponseDto findById(Long id);

	public UsuarioFullResponseDto findByUsername(String username);

	public void addRole(Long usuarioId, Long roleId);

	public void removeRole(Long usuarioId, Long roleId);
	
	public void alterarPassword(Long id, UsuarioAlterarPasswordDto usuarioAlterarSenhaDto);
	
	public Page<IQueryGeralComunDto> findByRolesDoUsuario(Long idUsuario, ParamsRequestModel prm);
	
	public void addPagina(Long usuarioId, Long paginaId);

	public void removePagina(Long usuarioId, Long paginaId);
	
	public Page<IQueryGeralComunDto> findByPaginasDoUsuario(Long idUsuario, Long idSistema, ParamsRequestModel prm);
	
	public List<PaginaResponseDto> carregarMenuDoUsuario();

}
