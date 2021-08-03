package br.com.claudemirojr.usuarios.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.usuarios.converter.DozerConverter;
import br.com.claudemirojr.usuarios.dto.SistemaDto;
import br.com.claudemirojr.usuarios.dto.SistemaResponseDto;
import br.com.claudemirojr.usuarios.exception.ResourceNotFoundException;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.entity.Sistema;
import br.com.claudemirojr.usuarios.model.repository.SistemaRepository;

@Service
public class SistemaServiceImpl implements SistemaService {

	@Autowired
	private SistemaRepository sistemaRepository;

	private SistemaResponseDto convertToSistemaResponseDto(Sistema entity) {
		return DozerConverter.parseObject(entity, SistemaResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public SistemaResponseDto criar(SistemaDto sistemaCriarDto) {
		var entity = DozerConverter.parseObject(sistemaCriarDto, Sistema.class);

		var sistema = sistemaRepository.save(entity);

		return convertToSistemaResponseDto(sistema);
	}

	@Override
	@Transactional(readOnly = false)
	public SistemaResponseDto atualizar(Long id, SistemaDto sistemaAtualizarDto) {
		var entity = sistemaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Sistema não encontrado para id %d", id)));

		entity.Atualizar(sistemaAtualizarDto.getNome());

		var sistema = sistemaRepository.save(entity);

		return convertToSistemaResponseDto(sistema);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		var entity = sistemaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Sistema não encontrado para id %d", id)));

		sistemaRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SistemaResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = sistemaRepository.findAll(pageable);

		return page.map(this::convertToSistemaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SistemaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = sistemaRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToSistemaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SistemaResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = sistemaRepository.findByNomeIgnoreCaseContaining(nome, pageable);

		return page.map(this::convertToSistemaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public SistemaResponseDto findById(Long id) {
		var entity = sistemaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Sistema não encontrado para id %d", id)));

		return convertToSistemaResponseDto(entity);
	}

}
