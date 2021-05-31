package br.edu.uniacademia.ativcompl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniacademia.ativcompl.domain.Category;
import br.edu.uniacademia.ativcompl.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Long id){
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
