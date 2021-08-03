package br.com.claudemirojr.usuarios.dto;

import java.io.Serializable;

import br.com.claudemirojr.usuarios.model.entity.Menu;
import br.com.claudemirojr.usuarios.model.entity.Sistema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginaResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String rota;

	private Sistema sistema;

	private Menu menu;

}
