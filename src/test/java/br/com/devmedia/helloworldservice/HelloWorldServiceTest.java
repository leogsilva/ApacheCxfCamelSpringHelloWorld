package br.com.devmedia.helloworldservice;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.devmedia.helloworldservice.schema.Nome;
import br.com.devmedia.helloworldservice.schema.Saudacao;
import br.com.devmedia.helloworldservice.wsdl.DigaOlaException;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldServiceTest {

	@Autowired
	CamelContext context;
	
	@DirtiesContext
	@Test
	public void requestResponseTest() throws Exception {
		ProducerTemplate template = context.createProducerTemplate();
        Nome _digaOla_parametro = new Nome();
        MessageContentsList ret = null;
		_digaOla_parametro.setConteudo("ze");
		ret = (MessageContentsList) template.sendBody("direct:digaOla",
				ExchangePattern.InOut, _digaOla_parametro);
		Saudacao saudacao = (Saudacao) ret.get(0);
		Assert.assertEquals("Bom dia", saudacao.getConteudo());

    	_digaOla_parametro.setConteudo("maria");
		ret = (MessageContentsList) template.sendBody("direct:digaOla",
				ExchangePattern.InOut, _digaOla_parametro);
		saudacao = (Saudacao) ret.get(0);
        Assert.assertEquals("Ola", saudacao.getConteudo());

    	_digaOla_parametro.setConteudo("anonimo");
    	try {
    		ret = (MessageContentsList) template.sendBody("direct:digaOla",
    				ExchangePattern.InOut, _digaOla_parametro);
    		Assert.assertTrue(false);
    	} catch (Exception e) {
    		Throwable realException = e.getCause();
    		Assert.assertTrue(DigaOlaException.class.isAssignableFrom(realException.getClass()));
     	}
	}
}
