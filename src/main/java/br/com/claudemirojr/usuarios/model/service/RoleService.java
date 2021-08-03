package br.com.claudemirojr.usuarios.model.service;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.usuarios.dto.RoleDto;
import br.com.claudemirojr.usuarios.dto.RoleResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;

public interface RoleService {

	public RoleResponseDto criar(RoleDto roleCriarDto);

	public RoleResponseDto atualizar(Long id, RoleDto roleAtualizarDto);

	public void delete(Long id);

	public Page<RoleResponseDto> findAll(ParamsRequestModel prm);

	public Page<RoleResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<RoleResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm);

	public RoleResponseDto findById(Long id);

}
