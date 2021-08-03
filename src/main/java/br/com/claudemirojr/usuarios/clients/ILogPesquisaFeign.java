package br.com.claudemirojr.usuarios.clients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.claudemirojr.usuarios.dto.LogPesquisaDto;
import br.com.claudemirojr.usuarios.dto.LogPesquisaResponseDto;

@FeignClient(name = "servico-rastreamento/log-pesquisa/v1")
public interface ILogPesquisaFeign {
	
	@PostMapping
	public ResponseEntity<LogPesquisaResponseDto> create(@RequestBody @Valid LogPesquisaDto logPesquisaDto);

}
