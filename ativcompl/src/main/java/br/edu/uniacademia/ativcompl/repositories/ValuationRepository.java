package br.edu.uniacademia.ativcompl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uniacademia.ativcompl.domain.Valuation;

@Repository
public interface ValuationRepository extends JpaRepository<Valuation, Long> {

}
