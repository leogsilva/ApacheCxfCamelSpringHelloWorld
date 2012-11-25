package br.com.devmedia.helloworldservice;

import org.apache.camel.builder.RouteBuilder;

/**
 * Classe que permite a criacao de rotas do camel atraves da DSL em java
 * @author leogsilva
 *
 */
public class HelloWorldContextBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:digaOla").to("cxf:bean:soapMessageEndpoint?dataFormat=POJO");
		from("cxf:bean:soapMessageEndpoint?dataFormat=POJO").to("bean:pojoHelloWorldService");
	}

}
