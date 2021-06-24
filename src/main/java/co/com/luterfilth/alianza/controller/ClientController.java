/**
 * 
 */
package co.com.luterfilth.alianza.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.luterfilth.alianza.model.Client;
import co.com.luterfilth.alianza.services.ClientService;

/**
 * @author cbuitrago
 *
 */
@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("client")
	public List<Client> getClients(@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone, @RequestParam(required = false) String email,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate endDate) {
		return clientService.getClients(name, phone, email, startDate, endDate);
	}

	@PostMapping("client")
	public Client createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}

}
