package br.com.claudemirojr.usuarios.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.claudemirojr.usuarios.dto.RoleDto;
import br.com.claudemirojr.usuarios.dto.RoleResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.service.RoleService;

@RestController
@RequestMapping("/role/v1")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<RoleResponseDto> create(@RequestBody @Valid RoleDto roleDto) {
		RoleResponseDto novo = roleService.criar(roleDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleResponseDto> update(
			@PathVariable("id") Long id, 
			@RequestBody @Valid RoleDto roleDto) {
		RoleResponseDto existe = roleService.atualizar(id, roleDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		roleService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<RoleResponseDto> registros = roleService.findAll(prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-id-maior-igual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(
			@PathVariable Long id, 
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<RoleResponseDto> registros = roleService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-nome/{nome}")
	public ResponseEntity<?> findAllDescricaoContem(
			@PathVariable String nome,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<RoleResponseDto> registros = roleService.findAllNomeContem(nome, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public RoleResponseDto findById(@PathVariable("id") Long id) {
		return roleService.findById(id);
	}

}
