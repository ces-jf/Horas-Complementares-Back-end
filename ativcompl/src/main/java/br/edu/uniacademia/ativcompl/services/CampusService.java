package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Campus;
import br.edu.uniacademia.ativcompl.repositories.CampusRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class CampusService {
	
	@Autowired
	private CampusRepository repo;
	
	public Campus find(Long id) {
		Optional<Campus> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Campus.class.getName()));
	}

}
