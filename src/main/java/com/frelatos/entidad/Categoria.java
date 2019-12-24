package com.frelatos.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categoria")
@NamedQuery(name = "categoria.listarCategoria", query = "select p from Categoria p")
public class Categoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_CATEGORIA = "categoria.listarCategoria";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCategoria;
	private String descripcion;

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
