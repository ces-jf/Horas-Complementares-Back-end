package br.edu.uniacademia.ativcompl.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.dto.UserDTO;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserDTO> {

	@Autowired
	private UserRepository repo;
	
	@Override
	public void initialize(UserInsert ann) {
	}
	
	@Override
	public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		User aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
