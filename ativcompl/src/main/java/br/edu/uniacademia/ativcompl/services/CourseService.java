package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.repositories.CourseRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	public Course find(Long id) {
		Optional<Course> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Course.class.getName()));
	}
	
	public Course insert(Course obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Course update(Course obj) {
		Course newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um curso que possua usuários.");
		}
	}
	
	public List<Course> findAll(){
		return repo.findAll();
	}
	
	public Page<Course> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Course newObj, Course obj) {
		newObj.setName(obj.getName());
		newObj.setWorkload(obj.getWorkload());
		newObj.setCampus(obj.getCampus());
	}
}
