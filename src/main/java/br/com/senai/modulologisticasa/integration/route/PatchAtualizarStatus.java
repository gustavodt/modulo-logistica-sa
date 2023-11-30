package br.com.senai.modulologisticasa.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.com.senai.modulologisticasa.integration.processor.ErrorProcessor;

public class PatchAtualizarStatus extends RouteBuilder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Value("${url-modulo-pedidos}")
	private String urlAtualizacao;
	
	@Value("${modulo-pedidos-key}")	
	private String token;
	
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@Override
	public void configure() throws Exception {
		from("direct:atualizarStatus")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.PATCH))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json;charset=UTF-8"))
				.setHeader("Authorization", simple("Bearer " + token))
				.toD(urlAtualizacao + "/pedidos/id/${exchangeProperty.id}/status/${exchangeProperty.status}")
					.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						JSONObject bodyJson = new JSONObject(exchange.getMessage().getBody(String.class));
						exchange.getMessage().setBody(bodyJson.toString());
					}
				})
			.doCatch(Exception.class)
			.setProperty("error", simple("${exception}"))
			.process(errorProcessor)
		.end();
	}
	
}
