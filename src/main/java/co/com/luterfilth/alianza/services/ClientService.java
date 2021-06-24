/**
 * 
 */
package co.com.luterfilth.alianza.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.luterfilth.alianza.daos.IClientDao;
import co.com.luterfilth.alianza.model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cbuitrago
 *
 */
@Service
public class ClientService {

    Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private IClientDao clientDao;

	public List<Client> getClients(String name, String phone, String email, LocalDate startDate, LocalDate endDate) {
		logger.debug("Inicial el servicio de consulta");
		List<Client> clients = clientDao.getClients(name, phone, email);
		if (clients.isEmpty()) {
			logger.debug("Lista de datos vacia, busca el total de registros");
			logger.debug("Llena los registros de todos los clientes");
			clients.addAll((List<Client>) clientDao.findAll());
		}
		logger.debug("Retorna los datos de los clientes encontrados");
		return clients;
	}

	public Client createClient(Client client) {
		logger.debug("Asigna el valor del ID a nulo");
		client.setId(null);
		logger.debug("Generación de la llave automatica");
		client.setSharedKey(RandomStringUtils.randomAlphabetic(10));
		logger.info("Guarda el registro de cliente");
		return clientDao.save(client);
	}

}
