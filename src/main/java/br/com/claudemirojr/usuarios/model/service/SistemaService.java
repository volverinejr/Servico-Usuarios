package br.com.claudemirojr.usuarios.model.service;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.usuarios.dto.SistemaDto;
import br.com.claudemirojr.usuarios.dto.SistemaResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;

public interface SistemaService {

	public SistemaResponseDto criar(SistemaDto sistemaCriarDto);

	public SistemaResponseDto atualizar(Long id, SistemaDto sistemaAtualizarDto);

	public void delete(Long id);

	public Page<SistemaResponseDto> findAll(ParamsRequestModel prm);

	public Page<SistemaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<SistemaResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm);

	public SistemaResponseDto findById(Long id);

}
