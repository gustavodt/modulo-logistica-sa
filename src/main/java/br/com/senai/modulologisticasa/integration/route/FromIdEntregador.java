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
public class FromIdEntregador extends RouteBuilder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Value("${url-modulo-rh}")
	private String urlApi;
	
	@Value("${modulo-rh-user}")
	private String usuario;
	
	@Value("${modulo-rh-senha}")
	private String senha;
	
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@Override
	public void configure() throws Exception {
		
		from("direct:autenticarRh")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json;charset=UTF-8"))
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						String bodyJson = exchange.getMessage().getBody(String.class);
						JSONObject jsonObject = new JSONObject(bodyJson);
						String email = jsonObject.getString("email");
						exchange.setProperty("email", email);
						
						jsonObject = new JSONObject();
						jsonObject.put("login", usuario);
						jsonObject.put("senha", senha);
						
						exchange.getMessage().setBody(jsonObject.toString());
					}
				})
				.toD(urlApi + "auth")
				.process(new Processor() {					
					@Override
					public void process(Exchange exchange) throws Exception {
						String bodyJson = exchange.getMessage().getBody(String.class);
						JSONObject jsonObject = new JSONObject(bodyJson);
						String token = jsonObject.getString("token");
						exchange.setProperty("tokenRh", token);
						exchange.getMessage().setBody(null);
					}
				})
			.doCatch(Exception.class)
				.setProperty("error", simple("${exception}"))
				.process(errorProcessor)
		.end();
		
		from("direct:buscarIdEntregador")
			.doTry()
				.toD("direct:autenticarRh")
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
				.setHeader("Authorization", simple("Bearer ${exchangeProperty.tokenRh}"))
				.toD(urlApi + "entregador/email/${exchangeProperty.email}")
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