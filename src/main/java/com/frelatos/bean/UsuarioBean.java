package com.frelatos.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.frelatos.dao.UsuarioDAO;
import com.frelatos.entidad.Usuario;

@ManagedBean(name = "usuario")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private MenuModel modelo = new DefaultMenuModel();
	private int codigoUsuario;
	private String usuario;
	private String clave;
	private int sesionCodigoUsuario;
	private String sesionUsuario;

	String login = "login.xhtml?faces-redirect=true";

	UsuarioDAO daoU = new UsuarioDAO();

	public String login() {

		FacesContext context = FacesContext.getCurrentInstance();
		Usuario objUsuario = daoU.validarUsuario(usuario);
		if (!objUsuario.getUsuario().equals(usuario)) {
			context.addMessage("mensajeLogin",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario incorrecto", null));
			return login;
		} else if (!objUsuario.getClave().equals(clave)) {
			context.addMessage("mensajeLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave incorrecta", null));
			return login;
		} else {
			menu();
			setSesionUsuario(objUsuario.getUsuario());
			setSesionCodigoUsuario(objUsuario.getCodUsuario());
			return "venta.xhtml?faces-redirect=true";
		}

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return login;
	}

	public void menu() {

		DefaultSubMenu ventaSubmenu = new DefaultSubMenu("Venta");

		DefaultMenuItem item = new DefaultMenuItem("Registrar");
		item.setCommand("venta.xhtml");
		item.setIcon("ui-icon-cart");
		ventaSubmenu.addElement(item);

		item = new DefaultMenuItem("Consultar");
		item.setCommand("consultaVenta.xhtml");
		item.setIcon("ui-icon-calculator");
		ventaSubmenu.addElement(item);

		modelo.addElement(ventaSubmenu);

		DefaultSubMenu productoSubmenu = new DefaultSubMenu("Producto");
		item = new DefaultMenuItem("Registrar");
		item.setCommand("producto.xhtml");
		item.setIcon("ui-icon-clipboard");
		productoSubmenu.addElement(item);
		
		modelo.addElement(productoSubmenu);

	}

	public MenuModel getModelo() {
		return modelo;
	}

	public void setModelo(MenuModel modelo) {
		this.modelo = modelo;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getSesionCodigoUsuario() {
		return sesionCodigoUsuario;
	}

	public void setSesionCodigoUsuario(int sesionCodigoUsuario) {
		this.sesionCodigoUsuario = sesionCodigoUsuario;
	}

	public String getSesionUsuario() {
		return sesionUsuario;
	}

	public void setSesionUsuario(String sesionUsuario) {
		this.sesionUsuario = sesionUsuario;
	}

}
