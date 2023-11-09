package br.com.senai.modulologisticasa.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.rest.RestBindingMode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import br.com.senai.modulologisticasa.dto.Notificacao;
import br.com.senai.modulologisticasa.integration.processor.ErrorProcessor;

@Component
public class ToGoogleMatrix extends RouteBuilder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Value("${email.url}")
	private String urlDeEnvio;
	
	@Autowired
	private ErrorProcessor errorProcessor;

	@Override
	public void configure() throws Exception {
		/*
	from("direct:").doTry()
	.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
	.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
	.process(new Processor() {
		public void process(Exchange exchange) throws Exception {
			Localizacao localizacao = exchange.getMessage().getBody(Localizao.class);
			JSONObject localizacaoJson = new JSONObject();
			
		}
	}
	*/
	}

	
	/*public void configure() throws Exception {
		from("direct:enviarEmail")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
				.process(new Processor() {					
					@Override
					public void process(Exchange exchange) throws Exception {				
						Notificacao notificacao = exchange.getMessage().getBody(Notificacao.class);
						JSONObject notificacaoJson = new JSONObject();
						notificacaoJson.put("destinatario", notificacao.getDestinatario());
						notificacaoJson.put("titulo", notificacao.getTitulo());
						notificacaoJson.put("mensagem", notificacao.getMensagem());
						exchange.getMessage().setBody(notificacaoJson.toString());
					}
				})
				.toD(urlDeEnvio)
			.doCatch(Exception.class)
				.setProperty("error", simple("${exception}"))
				.process(errorProcessor)
		.end();
	}*/
	
}