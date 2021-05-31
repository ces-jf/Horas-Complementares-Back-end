package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.repositories.ActivityRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository repo;
	
	public Activity find(Long id) {
		Optional<Activity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Activity.class.getName()));
	}

}
