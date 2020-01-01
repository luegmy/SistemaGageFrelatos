package com.frelatos.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_venta")
@NamedQuery(name = "venta.listarVentas", query = "select v from Venta v")
public class Venta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String LISTAR_VENTAS = "venta.listarVentas";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numVenta;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private String hora;
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "codCliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<DetalleVenta> detalles;

	public int getNumVenta() {
		return numVenta;
	}

	public void setNumVenta(int numVenta) {
		this.numVenta = numVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

}
