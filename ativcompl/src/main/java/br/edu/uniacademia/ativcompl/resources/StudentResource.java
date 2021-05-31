package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

	@Autowired
	private StudentService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> find(@PathVariable Long id){
		Student obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
