package com.frelatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.frelatos.entidad.Producto;

public class ProductoDAO {
	EntityManager em = null;

	private void open() {
		em = JpaUtil.getEntityManager();
	}

	private void close() {
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> listarProducto() {
		open();
		Query q = em.createNamedQuery(Producto.LISTAR_PRODUCTO);

		return q.getResultList();
	}
	
	public Producto buscarProductoPorCodigo(int codigo) {
		open();
		return em.find(Producto.class, codigo);
	}
	
	public String actualizarProducto(Producto producto) {
		open();
		String mensaje;
		try {
			em.getTransaction().begin();
			if (producto.getCodProducto() == 0) {
				em.persist(producto);
				mensaje = "Producto registrado";
			} else {
				em.merge(producto);
				mensaje = "Producto actualizado";
			}
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			close();
		}

		return mensaje;
	}
	
	public String eliminarProducto(int codigo) {
		open();
		Producto objProducto = em.find(Producto.class, codigo);
		try {
			em.getTransaction().begin();
			em.remove(objProducto);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			return "Ya se encuentra en almacen";
		} finally {
			close();
		}

		return "Producto eliminado";
	}

}
