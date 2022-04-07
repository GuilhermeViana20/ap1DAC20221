package dao;

import java.math.BigInteger;
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
	
	public static void deletar(Integer id)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		Instrumento instrumento = entityManager.find(Instrumento.class, id);
		entityManager.remove(instrumento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void update(Instrumento instrumento) {
		EntityManager s = JPAUtil.criarEntityManager();
		s.getTransaction().begin();
		s.merge(instrumento);
		s.getTransaction().commit();
		s.close();
	}
	
	public static Instrumento getById(Integer id) {
		EntityManager s = JPAUtil.criarEntityManager();
		s.getTransaction().begin();
		Instrumento instrumento = s.find(Instrumento.class, id);
		s.close();
		return instrumento;
	}
	
	public static List<Instrumento> listar(){
		EntityManager s = JPAUtil.criarEntityManager();
		Query q = s.createQuery("select e from Instrumento e");
		List<Instrumento> lista = q.getResultList();
		s.close();
		return lista;
	}
	
	public static Integer count(){
		EntityManager s = JPAUtil.criarEntityManager();
		Query q = s.createNativeQuery("select count(id) from instrumento");
		int count = ((BigInteger) q.getSingleResult()).intValue();
		s.close();
		return count;
	}
	
}
