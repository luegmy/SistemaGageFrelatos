package com.frelatos.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_detalleventa")
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

}
