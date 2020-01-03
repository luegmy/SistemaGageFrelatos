package com.frelatos.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@NamedQuery(name = "usuario.buscarUsuario", query = "select u from Usuario u where u.usuario=:x")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int codUsuario;
	private @Getter @Setter String usuario;
	private @Getter @Setter String clave;

}
