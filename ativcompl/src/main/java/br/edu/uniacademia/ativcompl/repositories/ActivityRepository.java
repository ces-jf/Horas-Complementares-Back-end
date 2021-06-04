package br.edu.uniacademia.ativcompl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.domain.Student;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Transactional(readOnly = true)
	Page<Activity> findByStudent(Student student, Pageable pageRequest);

}
