package com.frelatos.entidad;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DetalleVentaPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVenta;
	private int codProducto;

}
