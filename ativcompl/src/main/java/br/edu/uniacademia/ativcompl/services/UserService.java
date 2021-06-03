package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.dto.UserDTO;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired  private BCryptPasswordEncoder pswdEncoder;
	@Autowired	private UserRepository repo;
	
	public User find(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + User.class.getName()));
	}
	
	public User insert(User obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Não foi possível excluir este Usuário.");
		}
	}
	
	public List<User> findAll(){
		return repo.findAll();
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		newObj.setRegistration(obj.getRegistration());
		newObj.setPassword(obj.getPassword());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getRegistration(), objDto.getName(), objDto.getEmail(), pswdEncoder.encode(objDto.getPassword()));
	}

}
