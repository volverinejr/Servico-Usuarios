package br.com.claudemirojr.usuarios.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claudemirojr.usuarios.converter.DozerConverter;
import br.com.claudemirojr.usuarios.dto.PaginaDto;
import br.com.claudemirojr.usuarios.dto.PaginaResponseDto;
import br.com.claudemirojr.usuarios.exception.ResourceNotFoundException;
import br.com.claudemirojr.usuarios.model.ParamsRequestModel;
import br.com.claudemirojr.usuarios.model.entity.Pagina;
import br.com.claudemirojr.usuarios.model.repository.PaginaRepository;

@Service
public class PaginaServiceImpl implements PaginaService {

	@Autowired
	private PaginaRepository paginaRepository;

	private PaginaResponseDto convertToPaginaResponseDto(Pagina entity) {
		return DozerConverter.parseObject(entity, PaginaResponseDto.class);
	}

	@Override
	@Transactional(readOnly = false)
	public PaginaResponseDto criar(PaginaDto paginaCriarDto) {
		var entity = DozerConverter.parseObject(paginaCriarDto, Pagina.class);

		var pagina = paginaRepository.save(entity);

		return convertToPaginaResponseDto(pagina);
	}

	@Override
	@Transactional(readOnly = false)
	public PaginaResponseDto atualizar(Long id, PaginaDto paginaAtualizarDto) {
		var entity = paginaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Página não encontrada para id %d", id)));

		entity.Atualizar(paginaAtualizarDto.getNome(), paginaAtualizarDto.getRota(), paginaAtualizarDto.getSistema(),
				paginaAtualizarDto.getMenu());

		var pagina = paginaRepository.save(entity);

		return convertToPaginaResponseDto(pagina);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		var entity = paginaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Página não encontrada para id %d", id)));

		paginaRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PaginaResponseDto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = paginaRepository.findAll(pageable);

		return page.map(this::convertToPaginaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PaginaResponseDto> findAllIdMaiorIgual(Long id, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = paginaRepository.findByIdGreaterThanEqual(id, pageable);

		return page.map(this::convertToPaginaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PaginaResponseDto> findAllNomeContem(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		var page = paginaRepository.findByNomeIgnoreCaseContaining(nome, pageable);

		return page.map(this::convertToPaginaResponseDto);
	}

	@Override
	@Transactional(readOnly = true)
	public PaginaResponseDto findById(Long id) {
		var entity = paginaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Página não encontrada para id %d", id)));

		return convertToPaginaResponseDto(entity);
	}

}
