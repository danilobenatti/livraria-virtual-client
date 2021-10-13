package br.com.uniciv.rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class MainPost {

	public static void main(String[] args) {

		Livro livro = new Livro(4L, "Livro D", "ISBN-0000", "Gênero A", 19.99,
				"Autor 1");

		Response response = ClientBuilder.newClient()
				.target("http://localhost:8080/livraria-virtual").path("livro")
				.request()
				.post(Entity.xml(livro));
		if (response.getStatus() == Status.CREATED.getStatusCode()) {
			ItemBusca itemBusca = ClientBuilder.newClient()
					.target(response.getLocation())
					.request(MediaType.APPLICATION_XML).get(ItemBusca.class);
			System.out.println(itemBusca.getLivro().getTitulo());
		} else if (response.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
			System.out.println("Verifique se todos os dados estão corretos");
		}

	}

}
