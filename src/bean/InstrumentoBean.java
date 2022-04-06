package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.InstrumentoDao;
import entidade.Instrumento;

@ManagedBean
public class InstrumentoBean {

	private Instrumento instrumento = new Instrumento();
	private List<Instrumento> lista;
	private int count;
	
	public String salvar() {
		try {
			InstrumentoDao.salvar(instrumento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Instrumento cadastrado com sucesso!"));
			instrumento = new Instrumento();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public String deletar() {
		try {
			InstrumentoDao.deletar(instrumento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Instrumento deletado com sucesso!"));
			instrumento = new Instrumento();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public Instrumento getInstrumento() {
		return instrumento;
	}
	
	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}
	
	public List<Instrumento> getLista() {
		if(lista == null) {
			lista = InstrumentoDao.listar();
		}
		return lista;
	}
	
	public void setListaInstrumento(List<Instrumento> lista) { 
		this.lista = lista;
	}
	
	public int getCount() {
		count = InstrumentoDao.contar();
		return count;
	}
	
}
