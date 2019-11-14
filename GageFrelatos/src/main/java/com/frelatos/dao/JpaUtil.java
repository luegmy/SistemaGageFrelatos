package com.frelatos.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
private static EntityManagerFactory factory;
	
	static{
		factory = Persistence.createEntityManagerFactory("frelatos");
	}
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

}
