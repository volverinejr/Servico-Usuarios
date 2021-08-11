package br.com.claudemirojr.usuarios.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.usuarios.converter.DozerConverter;
import br.com.claudemirojr.usuarios.dto.RoleDto;
import br.com.claudemirojr.usuarios.dto.RoleResponseDto;
import br.com.claudemirojr.usuarios.exception.ResourceNotFoundException;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.entity.Role;
import br.com.claudemirojr.usuarios.model.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	private RoleResponseDto convertToRoleResponseDto(Role entity) {
		return DozerConverter.parseObject(entity, RoleResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public RoleResponseDto criar(RoleDto roleCriarDto) {
		var entity = DozerConverter.parseObject(roleCriarDto, Role.class);

		var role = roleRepository.save(entity);

		return convertToRoleResponseDto(role);
	}

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "roleCache", key = "#id")
	public RoleResponseDto atualizar(Long id, RoleDto roleAtualizarDto) {
		var entity = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Role não encontrado para id %d", id)));

		entity.Atualizar(roleAtualizarDto.getNome());

		var role = roleRepository.save(entity);

		return convertToRoleResponseDto(role);
	}

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "roleCache")
	public void delete(Long id) {
		var entity = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Role não encontrado para id %d", id)));

		roleRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "roleCache")
	public Page<RoleResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = roleRepository.findAll(pageable);

		return page.map(this::convertToRoleResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RoleResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = roleRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToRoleResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RoleResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = roleRepository.findByNomeIgnoreCaseContaining(nome, pageable);

		return page.map(this::convertToRoleResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "roleCache")
	public RoleResponseDto findById(Long id) {
		var entity = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Role não encontrado para id %d", id)));

		return convertToRoleResponseDto(entity);
	}

}
