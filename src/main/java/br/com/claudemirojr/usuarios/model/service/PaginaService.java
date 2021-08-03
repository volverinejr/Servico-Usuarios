package br.com.claudemirojr.usuarios.model.service;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.usuarios.dto.PaginaDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;

public interface PaginaService {

	public PaginaResponseDto criar(PaginaDto paginaCriarDto);

	public PaginaResponseDto atualizar(Long id, PaginaDto paginaAtualizarDto);

	public void delete(Long id);

	public Page<PaginaResponseDto> findAll(ParamsRequestModel prm);

	public Page<PaginaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<PaginaResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm);

	public PaginaResponseDto findById(Long id);

}
