package br.com.senai.modulologisticasa.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.senai.modulologisticasa.integration.processor.ErrorProcessor;

@Component
public class GetCardapioMktplaceRestaurante extends RouteBuilder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Value("${url-restaurante-mktplace}")
	private String urlBusca;
	
	@Value("${mktplace-key}")	
	private String token;	
	
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@Override
	public void configure() throws Exception {
		from("direct:buscarRestaurantePor")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
				.setHeader("Authorization", simple("Bearer " + token))
				.process(new Processor() {					
					@Override
					public void process(Exchange exchange) throws Exception {		
						JSONObject bodyJson = new JSONObject(exchange.getMessage().getBody(String.class));
						exchange.setProperty("nomeRestaurante", bodyJson.getString("nomeRestaurante"));
						exchange.setProperty("categoriaRestaurante", bodyJson.getString("categoriaRestaurante"));
					}
				})
				.toD(urlBusca + "?nome=${exchangeProperty.nomeRestaurante}"
						+ "&id-categoria=${exchangeProperty.categoriaRestaurante}")
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