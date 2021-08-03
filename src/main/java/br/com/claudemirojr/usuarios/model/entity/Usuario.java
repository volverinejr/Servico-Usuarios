package br.com.claudemirojr.usuarios.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.claudemirojr.usuarios.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Audited
@AuditTable(value = "usuario_audit")
@AuditOverride(forClass = EntityBase.class)
@EntityListeners(AuditingEntityListener.class)

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(length = 20, unique = true, nullable = false)
	private String username;

	@Column(length = 100, nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String apelido;

	@Column(length = 100, unique = true, nullable = false)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
	@AuditJoinTable(name = "usuario_role_audit")
	private List<Role> roles = new ArrayList<>();
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_pagina", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "pagina_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "pagina_id" }) })
	@AuditJoinTable(name = "usuario_pagina_audit")
	private List<Pagina> paginas = new ArrayList<>();
	


	
	public void setPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		this.password = bCryptPasswordEncoder.encode(password);

		//String passwordBCrypt = passwordEncoder.encode(password);
		//this.password = passwordBCrypt;
	}
	
	
	public void AlterarPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		this.password = bCryptPasswordEncoder.encode(password);

		//String passwordBCrypt = passwordEncoder.encode(password);
		//this.password = passwordBCrypt;
	}
	

	public void Atualizar(Boolean enabled, String nome, String apelido, String email) {
		this.enabled = enabled;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
	}

	public void addRole(Role role) {
		Boolean existe = false;

		for (Role roleAux : this.roles) {
			if (roleAux.equals(role)) {
				existe = true;
				break;
			}
		}

		if (!existe) {
			this.roles.add(role);
		}

	}

	public void removeRole(Role role) {
		for (Role roleAux : this.roles) {
			if (roleAux.equals(role)) {
				this.roles.remove(role);
				break;
			}
		}

	}
	
	
	public void addPagina(Pagina pagina) {
		Boolean existe = false;

		for (Pagina paginaAux : this.paginas) {
			if (paginaAux.equals(pagina)) {
				existe = true;
				break;
			}
		}

		if (!existe) {
			this.paginas.add(pagina);
		}

	}

	public void removePagina(Pagina pagina) {
		for (Pagina paginaAux : this.paginas) {
			if (paginaAux.equals(pagina)) {
				this.paginas.remove(pagina);
				break;
			}
		}

	}	
	
	
	

}
