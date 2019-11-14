package com.frelatos.entidad;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetalleVentaPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVenta;
	private int codProducto;

	public int getNumVenta() {
		return numVenta;
	}

	public void setNumVenta(int numVenta) {
		this.numVenta = numVenta;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codProducto;
		result = prime * result + numVenta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleVentaPK other = (DetalleVentaPK) obj;
		if (codProducto != other.codProducto)
			return false;
		if (numVenta != other.numVenta)
			return false;
		return true;
	}

}
