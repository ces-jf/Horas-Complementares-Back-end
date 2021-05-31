package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.repositories.StudentRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	public Student find(Long id) {
		Optional<Student> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Student.class.getName()));
	}

}
