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

import br.com.claudemirojr.usuarios.dto.MenuDto;
import br.com.claudemirojr.usuarios.dto.MenuResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.service.MenuService;

@RestController
@RequestMapping("/menu/v1")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@PostMapping
	public ResponseEntity<MenuResponseDto> create(@RequestBody @Valid MenuDto menuDto) {
		MenuResponseDto novo = menuService.criar(menuDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MenuResponseDto> update(@PathVariable("id") Long id, @RequestBody @Valid MenuDto menuDto) {
		MenuResponseDto existe = menuService.atualizar(id, menuDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		menuService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<MenuResponseDto> registros = menuService.findAll(prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-id-maior-igual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(@PathVariable Long id, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<MenuResponseDto> registros = menuService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-nome/{nome}")
	public ResponseEntity<?> findAllDescricaoContem(@PathVariable String nome,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<MenuResponseDto> registros = menuService.findAllNomeContem(nome, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public MenuResponseDto findById(@PathVariable("id") Long id) {
		return menuService.findById(id);
	}

}
