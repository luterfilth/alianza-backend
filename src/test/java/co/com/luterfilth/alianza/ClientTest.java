package co.com.luterfilth.alianza;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import co.com.luterfilth.alianza.model.Client;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClientTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void clientOk() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/client";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonDNA = "{ \"name\": \"Cristian Buitrago\", \"phone\": 1233214567, \"email\": \"email@falso.com\", \"startDate\": \"2021-01-01\", \"endDate\": \"2021-02-01\"}";

		HttpEntity<String> request = new HttpEntity<>(jsonDNA, headers);

		ResponseEntity<Client> result = this.restTemplate.postForEntity(uri, request, Client.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}

	@Test
	public void clientsOk() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/client";
		URI uri = new URI(baseUrl);

		ResponseEntity<Client[]> result = this.restTemplate.getForEntity(uri, Client[].class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}

}
