package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.repositories.UserTypeRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class UserTypeService {
	
	@Autowired
	private UserTypeRepository repo;
		
	public UserType find(Long id) {
		Optional<UserType> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + UserType.class.getName()));
		}

}
