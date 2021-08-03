package br.com.claudemirojr.usuarios.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.claudemirojr.usuarios.dto.IQueryGeralComunDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAlterarPasswordDto;
import br.com.claudemirojr.usuarios.dto.UsuarioAtualizarDto;
import br.com.claudemirojr.usuarios.dto.UsuarioDto;
import br.com.claudemirojr.usuarios.dto.UsuarioFullResponseDto;
import br.com.claudemirojr.usuarios.dto.UsuarioResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.service.UsuarioService;

@RestController
@RequestMapping("/usuario/v1")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid UsuarioDto usuarioDto) {
		UsuarioResponseDto novo = usuarioService.criar(usuarioDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> update(
			@PathVariable("id") Long id,
			@RequestBody @Valid UsuarioAtualizarDto usuarioAtualizarDto) {
		UsuarioResponseDto existe = usuarioService.atualizar(id, usuarioAtualizarDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> alterarPassword(
			@PathVariable("id") Long id,
			@RequestBody @Valid UsuarioAlterarPasswordDto usuarioAlterarSenhaDto) {
		
		usuarioService.alterarPassword(id, usuarioAlterarSenhaDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		usuarioService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<UsuarioResponseDto> registros = usuarioService.findAll(prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-id-maior-igual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(
			@PathVariable Long id, 
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<UsuarioResponseDto> registros = usuarioService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-nome/{nome}")
	public ResponseEntity<?> findAllDescricaoContem(
			@PathVariable String nome,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<UsuarioResponseDto> registros = usuarioService.findAllNomeContem(nome, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public UsuarioResponseDto findById(@PathVariable("id") Long id) {
		return usuarioService.findById(id);
	}

	@GetMapping("/search-username/{username}")
	public UsuarioFullResponseDto findByUsername(@PathVariable("username") String username) {
		return usuarioService.findByUsername(username);
	}
	
	
	
	//ROLE
	@GetMapping("/{usuarioId}/role")
	public ResponseEntity<?> findByPermissaoDoUser(@PathVariable Long usuarioId, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);
		
		Page<IQueryGeralComunDto> registros = usuarioService.findByRolesDoUsuario(usuarioId, prm);

		return ResponseEntity.ok(registros);
	}

	@PostMapping("/{usuarioId}/role/{roleId}")
	public ResponseEntity<?> addRole(@PathVariable("usuarioId") Long usuarioId, @PathVariable("roleId") Long roleId) {
		usuarioService.addRole(usuarioId, roleId);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{usuarioId}/role/{roleId}")
	public ResponseEntity<?> removeRole(@PathVariable("usuarioId") Long usuarioId, @PathVariable("roleId") Long roleId) {
		usuarioService.removeRole(usuarioId, roleId);
		
		return ResponseEntity.ok().build();
	}
	
	
	//PAGINA
	@GetMapping("/{usuarioId}/sistema/{sistemaId}/pagina")
	public ResponseEntity<?> findByPaginaDoUser(
			@PathVariable Long usuarioId,
			@PathVariable Long sistemaId,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);
		
		Page<IQueryGeralComunDto> registros = usuarioService.findByPaginasDoUsuario(usuarioId, sistemaId, prm);

		return ResponseEntity.ok(registros);
	}
	
	@PostMapping("/{usuarioId}/pagina/{paginaId}")
	public ResponseEntity<?> addPagina(@PathVariable("usuarioId") Long usuarioId, @PathVariable("paginaId") Long paginaId) {
		usuarioService.addPagina(usuarioId, paginaId);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{usuarioId}/pagina/{paginaId}")
	public ResponseEntity<?> removePagina(@PathVariable("usuarioId") Long usuarioId, @PathVariable("paginaId") Long paginaId) {
		usuarioService.removePagina(usuarioId, paginaId);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/carregar-menu")
	public ResponseEntity<?> carregarMenuDoUsuario() {
		List<PaginaResponseDto> registros = usuarioService.carregarMenuDoUsuario();

		return ResponseEntity.ok(registros);
	}
	
	

}
