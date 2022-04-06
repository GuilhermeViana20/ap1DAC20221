package principal;

import dao.InstrumentoDao;
import entidade.Instrumento;

public class Principal {

	public static void main(String[] args) {
		Instrumento instrumento = new Instrumento();
		instrumento.setNome("Teste");
		instrumento.setMarca("Teste");
		
		InstrumentoDao.salvar(instrumento);
	}
	
}
