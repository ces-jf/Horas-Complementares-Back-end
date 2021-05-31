package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.services.ActivityService;

@RestController
@RequestMapping(value = "/activities")
public class ActivityResource {
	
	@Autowired
	private ActivityService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Activity> find(@PathVariable Long id){
		Activity obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
