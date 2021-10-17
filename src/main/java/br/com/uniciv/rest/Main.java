package br.com.uniciv.rest;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class Main {

	public static void main(String[] args) {

		SslConfigurator sslConfigurator = SslConfigurator.newInstance()
				.trustStoreFile("server.keystore")
				.trustStorePassword("livraria");

		SSLContext context = sslConfigurator.createSSLContext();

		Client client = ClientBuilder.newBuilder().sslContext(context).build();

		HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature
				.basic("user", "123456");

		client.register(authenticationFeature);

		Livros livros = client.target("http://localhost:8080/livraria-virtual")
				.path("livro").request(MediaType.APPLICATION_XML)
				.get(Livros.class);
		for (Livro livro : livros.getLivros()) {
			System.out.println(livro.getTitulo());
		}

	}

}
