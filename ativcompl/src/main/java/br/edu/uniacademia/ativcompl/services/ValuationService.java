package br.edu.uniacademia.ativcompl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.uniacademia.ativcompl.domain.Valuation;
import br.edu.uniacademia.ativcompl.repositories.ValuationRepository;
import br.edu.uniacademia.ativcompl.services.exceptions.DataIntegrityException;
import br.edu.uniacademia.ativcompl.services.exceptions.ObjectNotFoundException;

@Service
public class ValuationService {

	@Autowired
	private ValuationRepository repo;
	
	public Valuation find(Long id) {
		Optional<Valuation> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Valuation.class.getName()));
	}
	
	public Valuation insert(Valuation obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Valuation update(Valuation obj) {
		Valuation newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Não foi possível excluir esta Avaliação.");
		}
	}
	
	public List<Valuation> findAll(){
		return repo.findAll();
	}
	
	public Page<Valuation> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Valuation newObj, Valuation obj) {
		newObj.setId(obj.getId());
		newObj.setValuation(obj.getValuation());
		newObj.setJustification(obj.getJustification());
		newObj.setActivity(obj.getActivity());
	}
}
