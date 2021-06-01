package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Category;
import br.edu.uniacademia.ativcompl.repositories.CategoryRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Long id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Não é possível excluir uma Categoria que está sendo referenciada em Atividades");
		}
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}

}
