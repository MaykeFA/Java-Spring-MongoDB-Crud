package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.Chamado;
import spring.repository.ChamadoRepository;

@Service
@Transactional
public class ChamadoServiceImp implements ChamadoService {
	@Autowired
	ChamadoRepository chamadoRepository;

	public void createChamado(Chamado chamado) {
		chamadoRepository.save(chamado);
	}

	public List<Chamado> getChamado() {
		return (List<Chamado>) chamadoRepository.findAll();
	}

	public Chamado findByCod(String cod) {
		return chamadoRepository.findOne(cod);
	}

	public Chamado update(Chamado chamado) {
		return chamadoRepository.save(chamado);
	}

	public void deleteChamadoByCod(String cod) {
		chamadoRepository.delete(cod);
	}

}
