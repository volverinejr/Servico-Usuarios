package br.com.claudemirojr.usuarios.dto;

import java.io.Serializable;
import java.util.List;

import br.com.claudemirojr.usuarios.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private Boolean enabled;

	private String nome;

	private String apelido;

	private String email;
	
	private List<Role> roles;

}
