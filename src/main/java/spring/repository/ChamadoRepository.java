package spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.model.Chamado;

@Repository
public interface ChamadoRepository extends CrudRepository<Chamado, String> {

}
