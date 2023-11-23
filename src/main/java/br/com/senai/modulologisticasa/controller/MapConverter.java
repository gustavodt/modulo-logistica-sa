package br.com.senai.modulologisticasa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MapConverter implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ObjectMapper conversor;
	
	public Map<String, Object> toJsonList(Page<?> page, String... exclusoes){
		
		JSONObject pageMap = new JSONObject();
		
		if (!page.getContent().isEmpty()) {
			
			List<Map<String, Object>> listagem = new ArrayList<Map<String,Object>>();			
			
			for (Object obj : page.getContent()) {
				listagem.add(toJsonMap(obj, exclusoes));
			}
			
			pageMap.put("listagem", listagem);
			
			pageMap.put("paginaAtual", page.getNumber());
			pageMap.put("totalDeItens", page.getTotalElements());
			pageMap.put("totalDePaginas", page.getTotalPages());
						
		}

		return pageMap.toMap();
		
	}

	public List<Map<String, Object>> toJsonList(List<?> list, String... exclusoes){

		List<Map<String, Object>> listagem = new ArrayList<Map<String,Object>>();

		for (Object obj : list) {
			listagem.add(toJsonMap(obj, exclusoes));
		}

		return listagem;
	}

	public Set<Map<String, Object>> toJsonSet(Set<?> list, String... exclusoes){

		Set<Map<String, Object>> listagem = new HashSet<Map<String,Object>>();

		for (Object obj : list) {
			listagem.add(toJsonMap(obj, exclusoes));
		}

		return listagem;
	}

	public Map<String, Object> toJsonMap(Object obj, String... exclusoes) {
		try {
			String jsonPlano = conversor.writeValueAsString(obj);
			JSONObject jsonObj = new JSONObject(jsonPlano);
			this.removeEmptyAndNullFields(jsonObj, exclusoes);
			return jsonObj.toMap();

		}catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			return null;
		}

	}

	private void removeEmptyAndNullFields(Object object, String... exclusoes) {

		if (object instanceof JSONArray) {

			JSONArray array = (JSONArray) object;

			for (int i = 0; i < array.length(); ++i) {	        
				removeEmptyAndNullFields(array.get(i), exclusoes);
			}

		} else if (object instanceof JSONObject) {

			JSONObject json = (JSONObject) object;

			JSONArray names = json.names();

			if (names == null) return;

			for (int i = 0; i < names.length(); ++i) {

				String key = names.getString(i);

				try{
					boolean isRemover = json.isNull(key) || isLiberadoParaRemocao(key, exclusoes); 
					if (isRemover) {
						json.remove(key);
					}else{
						removeEmptyAndNullFields(json.get(key), exclusoes);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isLiberadoParaRemocao(String key, String... exclusoes) {
		for (String outraKey : exclusoes) {
			if (key.equals(outraKey)) {
				return true;
			}
		}
		return false;
	}

}
