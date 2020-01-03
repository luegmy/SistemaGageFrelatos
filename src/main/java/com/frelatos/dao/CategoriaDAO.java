package com.frelatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.frelatos.entidad.Categoria;

public class CategoriaDAO {
	EntityManager em = null;

	private void open() {
		em = JpaUtil.getEntityManager();
	}

	private void close() {
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listarCategoria() {
		open();
		Query q = em.createNamedQuery(Categoria.LISTAR_CATEGORIA);

		return q.getResultList();
	}
	
	public String actualizarCategoria(Categoria Categoria) {
		open();
		String mensaje;
		try {
			em.getTransaction().begin();
			if (Categoria.getCodCategoria() == 0) {
				em.persist(Categoria);
				mensaje = "Categoria registrado";
			} else {
				em.merge(Categoria);
				mensaje = "Categoria actualizado";
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
	
	public String eliminarCategoria(int codigo) {
		open();
		Categoria objCategoria = em.find(Categoria.class, codigo);
		try {
			em.getTransaction().begin();
			em.remove(objCategoria);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			return "Ya se encuentra en almacen";
		} finally {
			close();
		}

		return "Categoria eliminado";
	}


}
