package com.frelatos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.frelatos.entidad.Usuario;

public class UsuarioDAO {
	EntityManagerFactory emf = null;
	EntityManager em = null;

	private void open() {
		emf = Persistence.createEntityManagerFactory("frelatos");
		em = emf.createEntityManager();
	}

	private void close() {
		em.close();
		emf.close();
	}

	@SuppressWarnings("unchecked")
	public Usuario validarUsuario(String usuario) {
		open();
		List<Usuario> usuarios;
		Usuario objUsuario;
		int codigo = 0;

		Query q = em.createNamedQuery("usuario.buscarUsuario").setParameter("x", usuario);
		usuarios = q.getResultList();

		if (!usuarios.isEmpty()) {
			for (Usuario u : usuarios) {
				codigo = u.getCodUsuario();
			}
			objUsuario = em.find(Usuario.class, codigo);
		} else {
			objUsuario = new Usuario();
			objUsuario.setCodUsuario(0);
			objUsuario.setUsuario("");
			objUsuario.setClave("");
			return objUsuario;
		}
		close();
		return objUsuario;
		
	}

}
