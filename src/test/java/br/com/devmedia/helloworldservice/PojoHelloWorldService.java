package br.com.devmedia.helloworldservice;

import br.com.devmedia.helloworldservice.schema.Erro;
import br.com.devmedia.helloworldservice.schema.Nome;
import br.com.devmedia.helloworldservice.schema.Saudacao;
import br.com.devmedia.helloworldservice.wsdl.DigaOlaException;

public class PojoHelloWorldService {

	public Saudacao digaOla(Nome parametro) throws DigaOlaException {
		Saudacao s = new Saudacao();
		if ("ze".equals(parametro.getConteudo())) {
			s.setConteudo("Bom dia");
		} else if ("maria".equals(parametro.getConteudo())){
			s.setConteudo("Ola");
		} else {
			Erro erro = new Erro();
			erro.setConteudo("ERR_01");
			throw new DigaOlaException("Nao quero conversar",erro);
		}
		return s;
	}
}
