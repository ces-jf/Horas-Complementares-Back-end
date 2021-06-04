package br.edu.uniacademia.ativcompl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.domain.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Transactional(readOnly = true)
	Student findByUser(User user);
}
