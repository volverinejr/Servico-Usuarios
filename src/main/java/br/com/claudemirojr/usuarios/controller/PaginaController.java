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

import br.com.claudemirojr.usuarios.dto.PaginaDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.service.PaginaService;

@RestController
@RequestMapping("/pagina/v1")
public class PaginaController {

	@Autowired
	private PaginaService paginaService;

	@PostMapping
	public ResponseEntity<PaginaResponseDto> create(@RequestBody @Valid PaginaDto paginaDto) {
		PaginaResponseDto novo = paginaService.criar(paginaDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PaginaResponseDto> update(@PathVariable("id") Long id,
			@RequestBody @Valid PaginaDto paginaDto) {
		PaginaResponseDto existe = paginaService.atualizar(id, paginaDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		paginaService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<PaginaResponseDto> registros = paginaService.findAll(prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-id-maior-igual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(@PathVariable Long id, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<PaginaResponseDto> registros = paginaService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-nome/{nome}")
	public ResponseEntity<?> findAllDescricaoContem(@PathVariable String nome,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<PaginaResponseDto> registros = paginaService.findAllNomeContem(nome, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public PaginaResponseDto findById(@PathVariable("id") Long id) {
		return paginaService.findById(id);
	}

}
