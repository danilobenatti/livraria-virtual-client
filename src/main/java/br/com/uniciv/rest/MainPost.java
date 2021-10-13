package br.com.uniciv.rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class MainPost {

	public static void main(String[] args) {

		Livro livro = new Livro(3L, "Livro C", "ISBN-9999", "G�nero A", 19.99,
				"Autor 1");

		Livro livroResposta = ClientBuilder.newClient()
				.target("http://localhost:8080/livraria-virtual").path("livro")
				.request()
				.post(Entity.entity(livro, MediaType.APPLICATION_XML),
						Livro.class);
		System.out.println(livroResposta.getTitulo());

	}

}
