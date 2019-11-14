package com.frelatos.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sabor")

@NamedQuery(name = "sabor.listarSabor", query = "select p from Sabor p")
public class Sabor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_SABOR = "sabor.listarSabor";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codSabor;
	private String descripcion;

	public int getCodSabor() {
		return codSabor;
	}

	public void setCodSabor(int codSabor) {
		this.codSabor = codSabor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
