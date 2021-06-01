package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.repositories.UserTypeRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class UserTypeService {
	
	@Autowired
	private UserTypeRepository repo;
		
	public UserType find(Long id) {
		Optional<UserType> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + UserType.class.getName()));
	}
	
	public UserType insert(UserType obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public UserType update(UserType obj) {
		UserType newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Não é possível excluir um Tipo de Usuário que está relacionado com usuários.");
		}
	}
	
	public List<UserType> findAll(){
		return repo.findAll();
	}
	
	public Page<UserType> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(UserType newObj, UserType obj) {
		newObj.setType(obj.getType());
	}
}
