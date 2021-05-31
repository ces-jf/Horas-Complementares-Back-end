package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.services.UserTypeService;

@RestController
@RequestMapping(value="/usertype")
public class UserTypeResource {
	
	@Autowired
	private UserTypeService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		UserType obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
