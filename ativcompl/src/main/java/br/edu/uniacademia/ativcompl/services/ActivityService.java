package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.domain.Category;
import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.dto.ActivityNewDTO;
import br.edu.uniacademia.ativcompl.repositories.ActivityRepository;
import br.edu.uniacademia.ativcompl.security.UserSS;
import br.edu.uniacademia.ativcompl.services.exceptions.AuthorizationException;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repo;

	@Autowired
	private EmailService serviceEmail;
	@Autowired
	private StudentService serviceStudent;
	@Autowired
	private CategoryService serviceCategory;

	public Activity find(Long id) {
		Optional<Activity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Activity.class.getName()));
	}

	@Transactional
	public Activity insert(Activity obj) {
		obj.setId(null);

		repo.save(obj);

//		serviceEmail.sendOrderConfirmationHtmlEmail(obj);
		serviceEmail.sendOrderConfirmationEmail(obj);
		return obj;
	}

	public Activity update(Activity obj) {
		Activity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir esta Atividade.");
		}
	}

	public List<Activity> findAll() {
		return repo.findAll();
	}

	public Page<Activity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS userLogged = UserLoggedService.authenticated();
		if (userLogged == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Student student = serviceStudent.findByRegistration(userLogged.getUsername());
		return repo.findByStudent(student, pageRequest);
	}

	private void updateData(Activity newObj, Activity obj) {
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
		newObj.setStart(obj.getStart());
		newObj.setWorkload(obj.getWorkload());
		newObj.setHoursCompleted(obj.getHoursCompleted());
		newObj.setClosed(obj.getClosed());
		newObj.setCertificate(obj.getCertificate());
	}

	public Activity fromDTO(ActivityNewDTO objDto) {
		Student student = serviceStudent.find(objDto.getStudent_id());
		Category category = serviceCategory.find(objDto.getCategory_id());
		Activity activity = new Activity(objDto.getName(), objDto.getStart(), objDto.getWorkload(),
				objDto.getHoursCompleted(), objDto.getClosed(), objDto.getCertificate(), student, category);
		return activity;
	}
}
