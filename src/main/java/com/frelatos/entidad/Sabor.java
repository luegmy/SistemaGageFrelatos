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
@Table(name = "tb_sabor")

@NamedQuery(name = "sabor.listarSabor", query = "select p from Sabor p")
public class Sabor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_SABOR = "sabor.listarSabor";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int codSabor;
	private @Getter @Setter String descripcion;

}
