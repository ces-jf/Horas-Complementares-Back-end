package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Campus;
import br.edu.uniacademia.ativcompl.services.CampusService;

@RestController
@RequestMapping(value = "/campus")
public class CampusResource {
	
	@Autowired
	private CampusService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campus> find(@PathVariable Long id){
		Campus obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
