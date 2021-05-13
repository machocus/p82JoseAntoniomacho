package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import entities.Barco;

public class ControladorPuerto {
	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("puerto");
	private EntityManager em;
	private Query consulta;
	
	public void borrarBarco(Barco v) {
		this.em = entityManagerFactory.createEntityManager();
		Barco aux = null;
		this.em.getTransaction().begin();
		// Si v no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(v)) {
			// Carga v en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(v);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(aux);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	public void modifyBarco(Barco v) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que v existe en el
		// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
		// como entidad gestionada
		// Si el objeto v no existe en la base de datos, se comporta como persist() y la
		// entidad gestionada es la devuelta por merge(), por lo que v es una entidad desconectada
		this.em.merge(v);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void createBarco(Barco v) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// v es una entidad conectada
		this.em.persist(v);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	public Barco findByCodBarco(int codBarco) {
		this.em = entityManagerFactory.createEntityManager();
		Barco aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from Barco where codbarco = ?", Barco.class);
		this.consulta.setParameter(1, codBarco);
		aux = (Barco) consulta.getSingleResult();
		this.em.close();
		return aux;

}

	public Barco findByNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Barco.findNombre");
		this.consulta.setParameter("nombre", nombre);
		Barco v = (Barco) consulta.getSingleResult();
		this.em.close();
		return v;
	}

	public List<Barco> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Barco.findAll");
		List<Barco> listaVehiculos = (List<Barco>) consulta.getResultList();
		this.em.close();
		return listaVehiculos;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
//	public List<Barco> findByMarca(String marca) {
//		this.em = entityManagerFactory.createEntityManager();
//		this.consulta = em.createNativeQuery("Select * from vehiculo where marca=?", Barco.class);
//		this.consulta.setParameter(1, marca);
//		List<Barco> listaVehiculos = (List<Barco>) consulta.getResultList();
//		this.em.close();
//		return listaVehiculos;
//	}
//	}
}

