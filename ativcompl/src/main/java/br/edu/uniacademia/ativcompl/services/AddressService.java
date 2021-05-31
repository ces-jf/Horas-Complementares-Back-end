package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Address;
import br.edu.uniacademia.ativcompl.repositories.AddressRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repo;
		
	public Address find(Long id) {
		Optional<Address> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Address.class.getName()));
		}

}
