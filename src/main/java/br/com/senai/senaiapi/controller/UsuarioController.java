package br.com.senai.senaiapi.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.senaiapi.dto.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@GetMapping(value = "/buscar/{id}")
	public Usuario buscarPor(@PathParam("id") String id) {
		System.out.println("ID RECEBIDO => " + id);
		Usuario usuario = new Usuario();
		usuario.setLogin("aguero.jogador");
		usuario.setNome("Aguero de la penha");
		return usuario;
	}

}
