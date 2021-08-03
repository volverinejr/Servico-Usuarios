package br.com.claudemirojr.usuarios.model.service;

import org.springframework.data.domain.Page;

import br.com.claudemirojr.usuarios.dto.MenuDto;
import br.com.claudemirojr.usuarios.dto.MenuResponseDto;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;

public interface MenuService {

	public MenuResponseDto criar(MenuDto menuCriarDto);

	public MenuResponseDto atualizar(Long id, MenuDto menuAtualizarDto);

	public void delete(Long id);

	public Page<MenuResponseDto> findAll(ParamsRequestModel prm);

	public Page<MenuResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm);

	public Page<MenuResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm);

	public MenuResponseDto findById(Long id);

}
