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
	
	public String deletar(Integer id) {
		try {
			InstrumentoDao.deletar(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Instrumento deletado com sucesso!"));
			instrumento = new Instrumento();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public String editInstrumento(Integer id) {
        Instrumento i = InstrumentoDao.getById(id);
        if(i !=null){
              instrumento.setMarca(i.getMarca());
              instrumento.setModelo(i.getModelo());
              instrumento.setNome(i.getNome());
              instrumento.setValor(i.getValor());
              instrumento.setId(i.getId());
        }else{
        	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro instrumento n�o encontrado."));
        }
        return "update";
     }
    
    public String update() {
		try {
			InstrumentoDao.update(instrumento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Instrumento atualizado com sucesso."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro atualizar instrumento."));
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
		count = InstrumentoDao.count();
		return count;
	}
	
}
