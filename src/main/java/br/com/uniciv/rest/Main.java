package br.com.uniciv.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Main {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		WebTarget targetBase = client
				.target("http://localhost:8080/livraria-virtual");
		WebTarget targetLivro = targetBase.path("livro");
		Builder builder = targetLivro.request(MediaType.APPLICATION_XML);
		Livros livros = builder.get(Livros.class);
		for (Livro livro : livros.getLivros()) {
			System.out.println(livro.getTitulo());
		}

	}

}
