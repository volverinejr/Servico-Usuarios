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

import br.com.claudemirojr.usuarios.dto.SistemaDto;
import br.com.claudemirojr.usuarios.dto.SistemaResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.service.SistemaService;

@RestController
@RequestMapping("/sistema/v1")
public class SistemaController {

	@Autowired
	private SistemaService sistemaService;

	@PostMapping
	public ResponseEntity<SistemaResponseDto> create(@RequestBody @Valid SistemaDto sistemaDto) {
		SistemaResponseDto novo = sistemaService.criar(sistemaDto);

		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SistemaResponseDto> update(@PathVariable("id") Long id,
			@RequestBody @Valid SistemaDto sistemaDto) {
		SistemaResponseDto existe = sistemaService.atualizar(id, sistemaDto);

		return new ResponseEntity<>(existe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		sistemaService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<SistemaResponseDto> registros = sistemaService.findAll(prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-id-maior-igual/{id}")
	public ResponseEntity<?> findAllIdMaiorIgual(@PathVariable Long id, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<SistemaResponseDto> registros = sistemaService.findAllIdMaiorIgual(id, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/search-nome/{nome}")
	public ResponseEntity<?> findAllDescricaoContem(@PathVariable String nome,
			@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<SistemaResponseDto> registros = sistemaService.findAllNomeContem(nome, prm);

		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public SistemaResponseDto findById(@PathVariable("id") Long id) {
		return sistemaService.findById(id);
	}

}
