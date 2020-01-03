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
	private @Getter @Setter int codCategoria;
	private @Getter @Setter String descripcion;


}
