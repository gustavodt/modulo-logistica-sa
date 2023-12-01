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

public class FromPedidosAceitos extends RouteBuilder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Value("${url-modulo-pedidos}")
	private String urlBusca;
	
	@Value("${modulo-pedidos-key}")	
	private String token;
	
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@Override
	public void configure() throws Exception {
		from("direct:listarPor")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
				.setHeader("Authorization", simple("Bearer " + token))
				.process(new Processor() {					
					@Override
					public void process(Exchange exchange) throws Exception {		
						JSONObject bodyJson = new JSONObject(exchange.getMessage().getBody(String.class));
						exchange.setProperty("status", bodyJson.getString("status"));
						exchange.setProperty("retirada", bodyJson.getString("tipo_entrega"));
						exchange.setProperty("pagina", bodyJson.getString("paginacaoAtual"));
					}
				})
				.toD(urlBusca + "/pedidos?status=${exchangeProperty.status}"
						+ "&retirada=${exchangeProperty.retirada}"
						+ "&pagina=${exchangeProperty.pagina}")
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
