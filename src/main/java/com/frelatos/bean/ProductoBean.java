package com.frelatos.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.UploadedFile;

import com.frelatos.dao.ProductoDAO;
import com.frelatos.entidad.Producto;

/**
 * @author PC-LENOVO
 *
 */
@ManagedBean(name = "productoBean")
@ViewScoped
public class ProductoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Formulario buscar
	private String descripcionProducto = "";

	// Formulario producto
	private int codigoProducto;
	private String descripcion;
	private BigDecimal costo;
	private BigDecimal precio;
	private UploadedFile imagen;

	private List<Producto> productos;
	private Producto productoSeleccionado;

	ProductoDAO dao = new ProductoDAO();

	public ProductoBean() {
		productos = new ArrayList<Producto>();
	}

	public void editarCelda(CellEditEvent event) {
		System.out.println("----"+event.getRowKey()+"---"+event.getColumn().toString()+"++++++"+event.getOldValue());
		Object newValue = event.getNewValue();
		productoSeleccionado.setDescripcion((String) newValue);
		dao.actualizarProducto(productoSeleccionado);

	}

	public void editarProducto(ActionEvent e) {
		int codigo = (int) e.getComponent().getAttributes().get("codigo");
		Producto objProducto = dao.buscarProductoPorCodigo(codigo);
		setCodigoProducto(objProducto.getCodProducto());
		setDescripcion(objProducto.getDescripcion());
		setCosto(objProducto.getCosto());
		setPrecio(objProducto.getPrecio());
	}
//
//	public void actualizarProducto() {
//		FacesContext context = FacesContext.getCurrentInstance();
//
//		Producto objP = new Producto();
//		objP.setCodProducto(codigoProducto);
//		objP.setDescripcion(descripcion);
//		objP.setCosto(costo);
//		objP.setPrecio(precio);
//
//		InputStream is;
//		try {
//			is = imagen.getInputstream();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[1024];
//			for (int i = 0; (i = is.read(buffer)) > 0;) {
//				baos.write(buffer, 0, i);
//			}
//			objP.setImagen(baos.toByteArray());
//		} catch (IOException e) {
//
//		}
//		System.out.println("---" + objP.getDescripcion() + objP.getPrecio() + objP.getImagen());
//		context.addMessage("mensajeRegistroProducto",
//				new FacesMessage(FacesMessage.SEVERITY_INFO, dao.actualizarProducto(objP), null));
//		System.out.println("-++--" + objP.getDescripcion() + objP.getPrecio() + objP.getImagen());
//		limpiar();
//	}

	public void limpiar() {
		codigoProducto = 0;
		descripcion = "";
		costo = new java.math.BigDecimal("0.00");
		precio = new java.math.BigDecimal("0.00");
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public UploadedFile getImagen() {
		return imagen;
	}

	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

	public List<Producto> getProductos() {
		productos = new ArrayList<Producto>();
		dao.listarProducto().stream().forEach(productos::add);
		return productos;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

}
