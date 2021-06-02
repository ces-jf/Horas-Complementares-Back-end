package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Valuation;
import br.edu.uniacademia.ativcompl.services.ValuationService;

@RestController
@RequestMapping(value = "/valuations")
public class ValuationResource {
	
	@Autowired
	private ValuationService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Valuation> find(@PathVariable Long id){
		Valuation obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
