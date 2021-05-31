package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.services.CourseService;

@RestController
@RequestMapping(value = "/courses")
public class CourseResource {

	@Autowired
	private CourseService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Course> find(@PathVariable Long id){
		Course obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
