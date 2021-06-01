package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Campus;
import br.edu.uniacademia.ativcompl.repositories.CampusRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class CampusService {
	
	@Autowired
	private CampusRepository repo;
	
	public Campus find(Long id) {
		Optional<Campus> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Campus.class.getName()));
	}
	
	public Campus insert(Campus obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Campus update(Campus obj) {
		Campus newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Campus que possui cursos.");
		}
	}
	
	public List<Campus> findAll(){
		return repo.findAll();
	}
	
	public Page<Campus> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Campus newObj, Campus obj) {
		newObj.setName(obj.getName());
	}

}
