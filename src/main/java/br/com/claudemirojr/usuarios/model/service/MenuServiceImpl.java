package br.com.claudemirojr.usuarios.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.usuarios.converter.DozerConverter;
import br.com.claudemirojr.usuarios.dto.MenuDto;
import br.com.claudemirojr.usuarios.dto.MenuResponseDto;
import br.com.claudemirojr.usuarios.exception.ResourceNotFoundException;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.entity.Menu;
import br.com.claudemirojr.usuarios.model.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	private MenuResponseDto convertToMenuResponseDto(Menu entity) {
		return DozerConverter.parseObject(entity, MenuResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public MenuResponseDto criar(MenuDto menuCriarDto) {
		var entity = DozerConverter.parseObject(menuCriarDto, Menu.class);

		var menu = menuRepository.save(entity);

		return convertToMenuResponseDto(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public MenuResponseDto atualizar(Long id, MenuDto menuAtualizarDto) {
		var entity = menuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Menu não encontrado para id %d", id)));

		entity.Atualizar(menuAtualizarDto.getNome());

		var menu = menuRepository.save(entity);

		return convertToMenuResponseDto(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		var entity = menuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Menu não encontrado para id %d", id)));

		menuRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MenuResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = menuRepository.findAll(pageable);

		return page.map(this::convertToMenuResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MenuResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = menuRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToMenuResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MenuResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = menuRepository.findByNomeIgnoreCaseContaining(nome, pageable);

		return page.map(this::convertToMenuResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public MenuResponseDto findById(Long id) {
		var entity = menuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Menu não encontrado para id %d", id)));

		return convertToMenuResponseDto(entity);
	}

}
