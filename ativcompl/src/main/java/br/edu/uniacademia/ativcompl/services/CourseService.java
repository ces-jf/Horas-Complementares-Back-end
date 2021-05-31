package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.repositories.CourseRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	public Course find(Long id) {
		Optional<Course> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Course.class.getName()));
	}

}
