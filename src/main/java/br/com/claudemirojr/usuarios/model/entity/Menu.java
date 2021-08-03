package br.com.claudemirojr.usuarios.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

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
@Table(name = "menu")
@Audited
@AuditTable(value = "menu_audit")
@AuditOverride(forClass = EntityBase.class)
@EntityListeners(AuditingEntityListener.class)

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 100, unique = true, nullable = false)
	private String nome;

	public void Atualizar(String nome) {
		this.nome = nome;
	}

}
