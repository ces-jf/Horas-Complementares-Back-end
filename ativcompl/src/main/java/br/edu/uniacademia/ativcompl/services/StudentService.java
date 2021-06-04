package br.edu.uniacademia.ativcompl.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniacademia.ativcompl.domain.Address;
import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.dto.StudentDTO;
import br.edu.uniacademia.ativcompl.dto.StudentNewDTO;
import br.edu.uniacademia.ativcompl.repositories.AddressRepository;
import br.edu.uniacademia.ativcompl.repositories.StudentRepository;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class StudentService {

	@Autowired
	private BCryptPasswordEncoder pswdEncoder;
	@Autowired
	private StudentRepository repo;
	@Autowired
	private UserRepository repoUser;
	@Autowired
	private AddressRepository repoAddress;
	@Autowired
	private CourseService serviceCourse;
	@Autowired
	private UserTypeService serviceUserType;

	public Student find(Long id) {
		Optional<Student> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Student.class.getName()));
	}

	public Student findByRegistration(String registration) {
		User user = repoUser.findByRegistration(registration);
		Student obj = repo.findByUser(user);
		try {
			return obj;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Aluno não encontrado! Matrícula: " + registration + ", Tipo: " + Student.class.getName());
		}
	}

	@Transactional
	public Student insert(Student obj) {
		obj.setId(null);
		repoAddress.save(obj.getAddress());
		repoUser.save(obj.getUser());
		obj = repo.save(obj);
		return obj;
	}

	public Student update(Student obj) {
		Student newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir este Usuário.");
		}
	}

	public List<Student> findAll() {
		return repo.findAll();
	}

	public Page<Student> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(Student newObj, Student obj) {
		newObj.setStartCourse(obj.getStartCourse());
		newObj.getUser().setRegistration(obj.getUser().getRegistration());
		newObj.getUser().setName(obj.getUser().getName());
		newObj.getUser().setEmail(obj.getUser().getEmail());
		newObj.getUser().setPassword(obj.getUser().getPassword());
		newObj.getAddress().setStreet(obj.getAddress().getStreet());
		newObj.getAddress().setNumber(obj.getAddress().getNumber());
		newObj.getAddress().setDistrict(obj.getAddress().getDistrict());
		newObj.getAddress().setCity(obj.getAddress().getCity());
	}

	public Student fromDTO(StudentDTO objDto) {
		User user = new User(objDto.getRegistration(), objDto.getName(), objDto.getEmail(),
				pswdEncoder.encode(objDto.getPassword()));
		Address address = new Address(objDto.getStreet(), objDto.getNumber(), objDto.getDistrict(), objDto.getCity());
		return new Student(objDto.getId(), objDto.getStartCourse(), user, address);
	}

	@Transactional
	public Student fromDTO(StudentNewDTO objDto) {
		Course course = serviceCourse.find(objDto.getCourse_id());
		UserType userType = serviceUserType.find(objDto.getUserType_id());
		User user = new User(objDto.getRegistration(), objDto.getName(), objDto.getEmail(),
				pswdEncoder.encode(objDto.getPassword()));
		user.getCourses().addAll(Arrays.asList(course));
		user.getUserTypeList().addAll(Arrays.asList(userType));
		Address address = new Address(objDto.getStreet(), objDto.getNumber(), objDto.getDistrict(), objDto.getCity());
		Student student = new Student(objDto.getStartCourse(), user, address);
		return student;
	}

}
