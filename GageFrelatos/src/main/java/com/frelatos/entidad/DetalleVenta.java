package com.frelatos.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_detalleVenta")
public class DetalleVenta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private DetalleVentaPK id;
	private BigDecimal cantidad;
	private BigDecimal precio;

	@Transient
	private int index;
	@Transient
	private int codigoProducto;
	@Transient
	private String descripcionProducto;
	@Transient
	private String sabor1;
	@Transient
	private String sabor2;

	@ManyToOne
	@JoinColumn(name = "numVenta", nullable = false, insertable = false, updatable = false)
	private Venta venta;

	@ManyToOne
	@JoinColumn(name = "codProducto", nullable = false, insertable = false, updatable = false)
	private Producto producto;

	public DetalleVentaPK getId() {
		return id;
	}

	public void setId(DetalleVentaPK id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getSabor1() {
		return sabor1;
	}

	public void setSabor1(String sabor1) {
		this.sabor1 = sabor1;
	}

	public String getSabor2() {
		return sabor2;
	}

	public void setSabor2(String sabor2) {
		this.sabor2 = sabor2;
	}

}
