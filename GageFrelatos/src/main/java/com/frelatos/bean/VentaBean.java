package com.frelatos.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.frelatos.dao.ProductoDAO;
import com.frelatos.dao.SaborDAO;
import com.frelatos.entidad.DetalleVenta;
import com.frelatos.entidad.Producto;
import com.frelatos.entidad.Sabor;

@ManagedBean
@SessionScoped
public class VentaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Producto> productos;
	private List<DetalleVenta> temporales;
	private List<Sabor> sabores;
	private DetalleVenta detalleSeleccionado;
	private int indice;
	private BigDecimal igv = new java.math.BigDecimal("0.00");
	private BigDecimal subtotal = new java.math.BigDecimal("0.00");
	private BigDecimal total = new java.math.BigDecimal("0.00");
	private String cliente = "";
	private Date fecha = new Date();

	ProductoDAO daoP = new ProductoDAO();
	SaborDAO daoS = new SaborDAO();

	public VentaBean() {
		temporales = new ArrayList<DetalleVenta>();
	}

	int index = 0;

	public void agregarVenta(ActionEvent e) {
		int codigo = (int) e.getComponent().getAttributes().get("codigo");
		Producto objP = daoP.buscarProductoPorCodigo(codigo);
		DetalleVenta objD = new DetalleVenta();
		objD.setCodigoProducto(objP.getCodProducto());
		objD.setDescripcionProducto(objP.getDescripcion());
		objD.setPrecio(objP.getPrecio());
		objD.setSabor1("");
		objD.setSabor2("");
		index++;
		objD.setIndex(index);
		temporales.add(objD);
		iterarLista();
	}
	
	public void grabarVenta() {
		
	}

	void iterarLista() {
		// Crea una lista auxiliar
		BigDecimal auxSubtotal = new java.math.BigDecimal("0.00");

		// Recorre la lista principal
		Iterator<DetalleVenta> it = temporales.iterator();
		while (it.hasNext()) {
			DetalleVenta detalleJPA = it.next();
			auxSubtotal = detalleJPA.getPrecio();
		}

		setTotal(getTotal().add(auxSubtotal).setScale(2, RoundingMode.HALF_EVEN));
		setSubtotal(getTotal().divide(new BigDecimal("1.18"), 2, RoundingMode.HALF_EVEN));
		setIgv(getTotal().subtract(getSubtotal()));
	}

	// metodo que elimina un item de la lista detalle
	@SuppressWarnings("rawtypes")
	public void quitarListaProductoVenta() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String pIndex = (String) map.get("item");
		int index = Integer.parseInt(pIndex);
		setIndice(index);

		BigDecimal monto = new java.math.BigDecimal("0.00");
		monto = temporales.get(getIndice()).getPrecio();
		temporales.remove(getIndice());
		setTotal(getTotal().subtract(monto));
		setSubtotal(getTotal().divide(new BigDecimal("1.18"), 2, RoundingMode.HALF_EVEN));
		setIgv(getTotal().subtract(getSubtotal()));

	}

	@SuppressWarnings("rawtypes")
	public void obtenerIndice() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String pIndex = (String) map.get("item");
		int index = Integer.parseInt(pIndex);
		setIndice(index);
	}

	public void agregarSabor(ActionEvent e) {
		int codigo = (int) e.getComponent().getAttributes().get("codigo");
		Sabor objS = daoS.buscarSaborPorCodigo(codigo);
		temporales.get(getIndice()).setSabor1(objS.getDescripcion());

	}

	public void agregarSabor2(ActionEvent e) {
		int codigo = (int) e.getComponent().getAttributes().get("codigo");
		Sabor objS = daoS.buscarSaborPorCodigo(codigo);
		temporales.get(getIndice()).setSabor2(objS.getDescripcion());
	}

	public List<Producto> getProductos() {
		productos = new ArrayList<Producto>();
		daoP.listarProducto().stream().sorted(Comparator.comparing(Producto::getDescripcion)).forEach(productos::add);
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Sabor> getSabores() {
		sabores = new ArrayList<Sabor>();
		daoS.listarSabor().stream().sorted(Comparator.comparing(Sabor::getDescripcion)).forEach(sabores::add);
		return sabores;
	}

	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}

	public List<DetalleVenta> getTemporales() {
		return temporales;
	}

	public void setTemporales(List<DetalleVenta> temporales) {
		this.temporales = temporales;
	}

	public DetalleVenta getDetalleSeleccionado() {
		return detalleSeleccionado;
	}

	public void setDetalleSeleccionado(DetalleVenta detalleSeleccionado) {
		this.detalleSeleccionado = detalleSeleccionado;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getIgv() {
		return igv;
	}

	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
