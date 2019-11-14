package com.frelatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.frelatos.entidad.Sabor;

public class SaborDAO {
	EntityManager em = null;

	private void open() {
		em = JpaUtil.getEntityManager();
	}

	private void close() {
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sabor> listarSabor() {
		open();
		Query q = em.createNamedQuery(Sabor.LISTAR_SABOR);

		return q.getResultList();
	}
	
	public Sabor buscarSaborPorCodigo(int codigo) {
		open();
		return em.find(Sabor.class, codigo);
	}
	
	public String actualizarSabor(Sabor Sabor) {
		open();
		String mensaje;
		try {
			em.getTransaction().begin();
			if (Sabor.getCodSabor() == 0) {
				em.persist(Sabor);
				mensaje = "Sabor registrado";
			} else {
				em.merge(Sabor);
				mensaje = "Sabor actualizado";
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
	
	public String eliminarSabor(int codigo) {
		open();
		Sabor objSabor = em.find(Sabor.class, codigo);
		try {
			em.getTransaction().begin();
			em.remove(objSabor);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			return "Ya se encuentra en almacen";
		} finally {
			close();
		}

		return "Sabor eliminado";
	}


}
