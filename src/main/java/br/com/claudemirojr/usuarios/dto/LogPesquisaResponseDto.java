package br.com.claudemirojr.usuarios.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPesquisaResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String servico;

	private String usuario;

	private String className;

	private String metodo;

	private String argumento;

	private String resultado;

}
