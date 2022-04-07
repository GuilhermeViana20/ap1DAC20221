package dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Usuario;
import util.JPAUtil;

public class UsuarioDao {
	
	public static void salvar(Usuario Usuario)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(Usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void deletar(Integer id)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		Usuario Usuario = entityManager.find(Usuario.class, id);
		entityManager.remove(Usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void update(Usuario Usuario) {
		EntityManager u = JPAUtil.criarEntityManager();
		u.getTransaction().begin();
		u.merge(Usuario);
		u.getTransaction().commit();
		u.close();
	}
	
	public static Usuario getById(Integer id) {
		EntityManager u = JPAUtil.criarEntityManager();
		u.getTransaction().begin();
		Usuario Usuario = u.find(Usuario.class, id);
		u.close();
		return Usuario;
	}
	
	public static List<Usuario> listar(){
		EntityManager u = JPAUtil.criarEntityManager();
		Query q = u.createQuery("select e from Usuario e");
		List<Usuario> lista = q.getResultList();
		u.close();
		return lista;
	}
	
	public static Integer count(){
		EntityManager u = JPAUtil.criarEntityManager();
		Query q = u.createNativeQuery("select count(id) from Usuario");
		int count = ((BigInteger) q.getSingleResult()).intValue();
		u.close();
		return count;
	}
	
	public static Usuario getBySenhaAndUsuario(String senha, String usuario){
		EntityManager u = JPAUtil.criarEntityManager();
		try {
			Query q = u.createNativeQuery("select *from Usuario where senha= :senha and usuario= :usuario", Usuario.class);
			q.setParameter("senha",senha);
			q.setParameter("usuario", usuario);
			Usuario s = (Usuario) q.getSingleResult();
			u.close();
			return s;
		} catch (Exception e) {
			return null;
		}
		
	}
}
