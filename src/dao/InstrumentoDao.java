package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Instrumento;
import util.JPAUtil;

public class InstrumentoDao {

	public static void salvar(Instrumento instrumento)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(instrumento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void deletar(Instrumento instrumento)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		instrumento = entityManager.find(Instrumento.class, instrumento.getId());
		entityManager.remove(2);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static List<Instrumento> listar()
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		Query query = entityManager.createQuery("SELECT instrumento FROM Instrumento instrumento");
		List<Instrumento> listaInstrumentos = query.getResultList();
		entityManager.close();
		return listaInstrumentos;
	}
	
	public static int contar()
	{
		List<Instrumento> listaInstrumentos = InstrumentoDao.listar();
		return listaInstrumentos.size();
	}
	
}
