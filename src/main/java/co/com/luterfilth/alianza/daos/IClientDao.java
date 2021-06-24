/**
 * 
 */
package co.com.luterfilth.alianza.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.luterfilth.alianza.model.Client;

/**
 * @author cbuitrago
 *
 */
@Repository
public interface IClientDao extends CrudRepository<Client, Long> {

	@Query("select c from Client c where " + "((:name is null and c.name is not null) or c.name = :name) and "
			+ "((:phone is null and c.phone is not null) or c.phone = :phone) and "
			+ "((:email is null and c.email is not null) or c.email = :email)")
	public List<Client> getClients(String name, String phone, String email);

}
