package br.com.senai.senaiapi.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.senaiapi.entity.Motorista;
import br.com.senai.senaiapi.service.MotoristaService;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

	@Autowired
	private MotoristaService service;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> motoristaMap) {
		Motorista novoMotorista = new Motorista();
		novoMotorista.setNomeCompleto(motoristaMap.getOrDefault("nomeCompleto", "").toString());
		novoMotorista.setCnh(motoristaMap.getOrDefault("cnh", "").toString());
		String dataDeValidade = motoristaMap.getOrDefault("dataDeValidade", "").toString();
		String[] camposDaData = dataDeValidade.split("-");
		LocalDate validade = LocalDate.of(Integer.parseInt(camposDaData[0]), Integer.parseInt(camposDaData[1]),
				Integer.parseInt(camposDaData[2]));
		novoMotorista.setDataDeValidade(validade);
		Motorista motoristaSalvo = service.inserir(novoMotorista);
		return ResponseEntity.created(URI.create("/motoristas/id" + motoristaSalvo.getId())).build();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		try {
			Motorista motoristaEncontrado = service.buscarPor(id);
			String json = mapper.writeValueAsString(motoristaEncontrado);
			JSONObject jsonObj = new JSONObject(json);
			return ResponseEntity.ok(jsonObj.toMap());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
