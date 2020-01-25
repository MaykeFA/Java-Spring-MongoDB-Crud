package spring.service;

import java.util.List;

import spring.model.Chamado;

import org.springframework.stereotype.Service;

@Service
public interface ChamadoService {
	public void createChamado(Chamado chamado);

	public List<Chamado> getChamado();

	public Chamado findByCod(String cod);

	public Chamado update(Chamado chamado);

	public void deleteChamadoByCod(String cod);
}
