package com.frelatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.frelatos.entidad.Venta;

public class VentaDAO {
	
	EntityManager em = null;

	private void open() {
		em = JpaUtil.getEntityManager();
	}

	private void close() {
		em.close();
	}
	
	public String registrarVenta(Venta venta) {
		open();

		em.getTransaction().begin();
		try {
			em.persist(venta);
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.getTransaction().commit();
		close();

		return "Venta registrada exitosamente";
		
	}	
	@SuppressWarnings("unchecked")
	public List<Venta> listarVenta() {
		open();
		Query q = em.createNamedQuery(Venta.LISTAR_VENTAS);

		return q.getResultList();
	}

}
