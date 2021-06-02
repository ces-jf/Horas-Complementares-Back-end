package br.edu.uniacademia.ativcompl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniacademia.ativcompl.domain.Address;
import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.dto.StudentNewDTO;
import br.edu.uniacademia.ativcompl.repositories.AddressRepository;
import br.edu.uniacademia.ativcompl.repositories.StudentRepository;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class StudentService {
	
	@Autowired  private StudentRepository repo;
	@Autowired  private UserRepository repoUser;
	@Autowired  private AddressRepository repoAddress;
	
	@Autowired  private CourseService serviceCourse;
	@Autowired  private UserTypeService serviceUserType;
	
	public Student find(Long id) {
		Optional<Student> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Student.class.getName()));
	}
	
	@Transactional
	public Student insert(Student obj) {
		obj.setId(null);
		repoAddress.save(obj.getAddress());
		obj = repo.save(obj);
		repoUser.save(obj.getUser());
		return obj;
	}
	
	@Transactional
	public Student fromDTO(StudentNewDTO objDto) {
		Course course = serviceCourse.find(objDto.getCourse_id()); //orElse(new Course());
		UserType userType = serviceUserType.find(objDto.getUserType_id()); //orElse(new UserType(objDto.getUserType_id(), "Tipo Novo"));

		User user = new User(objDto.getRegistration(), objDto.getName(), objDto.getEmail(), objDto.getPassword());
		user.getCourses().add(course);
		user.getUserTypeList().add(userType);
		
		Address address = new Address(objDto.getStreet(), objDto.getNumber(), objDto.getDistrict(), objDto.getCity());		
		
		Student student = new Student(objDto.getStartCourse(), user, address);
		return student; 
	}

}
