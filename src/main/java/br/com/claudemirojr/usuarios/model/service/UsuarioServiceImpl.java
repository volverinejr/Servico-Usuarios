package br.com.claudemirojr.usuarios.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.usuarios.config.Security;
import br.com.claudemirojr.usuarios.converter.DozerConverter;
import br.com.claudemirojr.usuarios.dto.IQueryGeralComunDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAlterarPasswordDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAtualizarDto;
import br.com.claudemirojr.usuarios.dto.UsuarioDto;
import br.com.claudemirojr.usuarios.dto.UsuarioFullResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioResponseDto;
import br.com.claudemirojr.usuarios.exception.ResourceNotFoundException;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.entity.Pagina;
import br.com.claudemirojr.usuarios.model.entity.Usuario;
import br.com.claudemirojr.usuarios.model.repository.PaginaRepository;
import br.com.claudemirojr.usuarios.model.repository.RoleRepository;
import br.com.claudemirojr.usuarios.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PaginaRepository paginaRepository;

	@Autowired
	private Security security;

	private UsuarioResponseDto convertToUsuarioResponseDto(Usuario entity) {
		return DozerConverter.parseObject(entity, UsuarioResponseDto.class);
	}

	private PaginaResponseDto convertToPaginaResponseDto(Pagina entity) {
		return DozerConverter.parseObject(entity, PaginaResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public UsuarioResponseDto criar(UsuarioDto usuarioCriarDto) {
		var entity = DozerConverter.parseObject(usuarioCriarDto, Usuario.class);

		var usuario = usuarioRepository.save(entity);

		return convertToUsuarioResponseDto(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public UsuarioResponseDto atualizar(Long id, UsuarioAtualizarDto usuarioAtualizarDto) {
		var entity = usuarioRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", id)));

		entity.Atualizar(usuarioAtualizarDto.getEnabled(), usuarioAtualizarDto.getNome(),
				usuarioAtualizarDto.getApelido(), usuarioAtualizarDto.getEmail());

		var usuario = usuarioRepository.save(entity);

		return convertToUsuarioResponseDto(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		var entity = usuarioRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", id)));

		usuarioRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = usuarioRepository.findAll(pageable);

		return page.map(this::convertToUsuarioResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = usuarioRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToUsuarioResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = usuarioRepository.findByNomeIgnoreCaseContaining(nome, pageable);

		return page.map(this::convertToUsuarioResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponseDto findById(Long id) {
		var entity = usuarioRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", id)));

		return convertToUsuarioResponseDto(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioFullResponseDto findByUsername(String username) {
		var entity = usuarioRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(
				String.format("Usuário não encontrado para username %s", username)));

		return DozerConverter.parseObject(entity, UsuarioFullResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public void addRole(Long usuarioId, Long roleId) {
		var role = roleRepository.findById(roleId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Role não encontrado para id %d", roleId)));

		var usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", usuarioId)));

		usuario.addRole(role);

		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeRole(Long usuarioId, Long roleId) {
		var role = roleRepository.findById(roleId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Role não encontrado para id %d", roleId)));

		var usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", usuarioId)));

		usuario.removeRole(role);

		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarPassword(Long id, UsuarioAlterarPasswordDto usuarioAlterarPasswordDto) {
		var usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", id)));

		usuario.AlterarPassword(usuarioAlterarPasswordDto.getPassword());

		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<IQueryGeralComunDto> findByRolesDoUsuario(Long idUsuario, ParamsRequestModel prm) {
		usuarioRepository.findById(idUsuario).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", idUsuario)));

		Pageable pageable = prm.toSpringPageRequest();

		return usuarioRepository.findByRolesDoUsuario(idUsuario, pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public void addPagina(Long usuarioId, Long paginaId) {
		var pagina = paginaRepository.findById(paginaId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Página não encontrado para id %d", paginaId)));

		var usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", usuarioId)));

		usuario.addPagina(pagina);

		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false)
	public void removePagina(Long usuarioId, Long paginaId) {
		var pagina = paginaRepository.findById(paginaId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Página não encontrado para id %d", paginaId)));

		var usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", usuarioId)));

		usuario.removePagina(pagina);

		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<IQueryGeralComunDto> findByPaginasDoUsuario(Long idUsuario, Long idSistema, ParamsRequestModel prm) {
		usuarioRepository.findById(idUsuario).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %d", idUsuario)));

		Pageable pageable = prm.toSpringPageRequest();

		return usuarioRepository.findByPaginasDoUsuario(idUsuario, idSistema, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaginaResponseDto> carregarMenuDoUsuario() {
		var usuarioLogado = security.getUsuarioLogado();

		var entity = usuarioRepository.findByUsername(usuarioLogado).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário não encontrado para id %s", usuarioLogado)));

		return entity.getPaginas().stream()
				.map(this::convertToPaginaResponseDto).collect(Collectors.toList());
	}

}
