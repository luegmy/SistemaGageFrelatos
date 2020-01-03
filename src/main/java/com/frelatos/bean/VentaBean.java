package com.frelatos.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.frelatos.dao.ProductoDAO;
import com.frelatos.dao.SaborDAO;
import com.frelatos.dao.VentaDAO;
import com.frelatos.entidad.Cliente;
import com.frelatos.entidad.DetalleVenta;
import com.frelatos.entidad.DetalleVentaPK;
import com.frelatos.entidad.Producto;
import com.frelatos.entidad.Sabor;
import com.frelatos.entidad.Venta;

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
	private int indice;
	private BigDecimal igv = new java.math.BigDecimal("0.00");
	private BigDecimal subtotal = new java.math.BigDecimal("0.00");
	private BigDecimal total = new java.math.BigDecimal("0.00");
	private BigDecimal monto = new java.math.BigDecimal("0.00");
	private BigDecimal vuelto = new java.math.BigDecimal("0.00");
	private String cliente = "";
	private Date fecha = new Date();
	private int codigoCliente = 1;
	
	@ManagedProperty(value = "#{usuario.sesionCodigoUsuario}")
	private int usuario;

	ProductoDAO daoP = new ProductoDAO();
	SaborDAO daoS = new SaborDAO();
	VentaDAO daoV = new VentaDAO();

	public VentaBean() {
		temporales = new ArrayList<DetalleVenta>();
	}

	int index = 0;
	
	int retornaNumeroVenta(){
		int numero=daoV.listarVenta().stream().mapToInt(v->v.getNumVenta()).max().orElse(0)+1;
		return numero;
	}
	
	public void agregarVenta(ActionEvent e) {
		int codigo = (int) e.getComponent().getAttributes().get("codigo");
		Producto objP = daoP.buscarProductoPorCodigo(codigo);
		DetalleVentaPK pk = new DetalleVentaPK();
		pk.setCodProducto(objP.getCodProducto());
		pk.setNumVenta(retornaNumeroVenta());
		DetalleVenta objD = new DetalleVenta();
		
		objD.setDescripcionProducto(objP.getDescripcion());
		objD.setCantidad(new BigDecimal("1"));
		objD.setPrecio(objP.getPrecio());
		objD.setSabor1("");
		objD.setSabor2("");
		index++;
		objD.setIndex(index);
		objD.setId(pk);
		temporales.add(objD);
		iterarLista();
	}

	public void grabarVenta() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		// formato para registrar la hora
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

		Cliente objCliente = new Cliente();
		objCliente.setCodCliente(codigoCliente);

		Venta objVenta = new Venta();
		objVenta.setCliente(objCliente);
		objVenta.setFecha(fecha);
		objVenta.setHora(sdf.format(new java.util.Date()));
		objVenta.setTotal(total);
		objVenta.setDetalles(temporales);

		context.addMessage("mensajeVenta", new FacesMessage(FacesMessage.SEVERITY_INFO,daoV.registrarVenta(objVenta), null));
		temporales.clear();
		total=new BigDecimal("0.00");
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getVuelto() {
		return vuelto;
	}

	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
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

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	

}
