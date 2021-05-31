package br.edu.uniacademia.ativcompl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uniacademia.ativcompl.domain.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long>{

}
