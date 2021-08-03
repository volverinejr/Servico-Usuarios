package br.com.claudemirojr.usuarios.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SistemaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Length(min = 4, max = 100)
	private String nome;

}
