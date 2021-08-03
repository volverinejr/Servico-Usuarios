package br.com.claudemirojr.usuarios.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.claudemirojr.usuarios.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagina")
@Audited
@AuditTable(value = "pagina_audit")
@AuditOverride(forClass = EntityBase.class)
@EntityListeners(AuditingEntityListener.class)

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Pagina extends EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false)
	private String rota;
	
	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	private Sistema sistema;
	
	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	private Menu menu;

	public void Atualizar(String nome, String rota, Sistema sistema, Menu menu) {
		this.nome = nome;
		this.rota = rota;
		this.sistema = sistema;
		this.menu = menu;
	}

}
