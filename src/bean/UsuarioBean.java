package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import entidade.Usuario;

@ManagedBean
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> lista;
	private int count;
	
   public String login() {
		
			Usuario u = UsuarioDao.getBySenhaAndUsuario(usuario.getSenha(), usuario.getUsuario());
			if(u != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Seja bem vindo."));
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro Usuario n�o encontrado."));
			}
		
		return "list-user";
	}
	
	public String salvar() {
		try {
			UsuarioDao.salvar(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario cadastrado com sucesso!"));
			usuario = new Usuario();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public String deletar(Integer id) {
		try {
			UsuarioDao.deletar(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuario deletado com sucesso!"));
			usuario = new Usuario();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public String editUsuario(Integer id) {
        Usuario i = UsuarioDao.getById(id);
        if(i !=null){
        	usuario.setId(i.getId());
        	usuario.setNome(i.getNome());
        	usuario.setSenha(i.getSenha());
        	usuario.setUsuario(i.getUsuario());
        }else{
        	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro Usuario n�o encontrado."));
        }
        return "update-user";
     }
    
    public String update() {
		try {
			UsuarioDao.update(usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Usuario atualizado com sucesso."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro atualizar Usuario."));
		}
		return null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario Usuario) {
		this.usuario = Usuario;
	}
	
	public List<Usuario> getLista() {
		if(lista == null) {
			lista = UsuarioDao.listar();
		}
		return lista;
	}
	
	public void setListaUsuario(List<Usuario> lista) { 
		this.lista = lista;
	}
	
	public int getCount() {
		count = UsuarioDao.count();
		return count;
	}
}
