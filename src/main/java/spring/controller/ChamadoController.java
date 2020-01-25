package spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.model.Chamado;
import spring.validator.ChamadoValidator;
import spring.service.ChamadoService;
import spring.service.StringResponse;;

@RestController
@RequestMapping(value = { "/api/chamado" })
public class ChamadoController {
	@Autowired
	ChamadoService chamadoService;

	@PostMapping(value = "", headers = "Accept=application/json")
	public ResponseEntity<Object> create(@Valid @RequestBody Chamado chamado) {
		try {
			ChamadoValidator validator = new ChamadoValidator(chamado);
			if (validator.getMessage() != null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validator.getMessage());
			} else {
				chamadoService.createChamado(chamado);
				StringResponse response = new StringResponse(
						"O chamado foi gerado. Seu código é " + chamado.getCodigo());
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
		} catch (Exception err) {
			StringResponse response = new StringResponse("Ocorreu um erro ao registrar o chamado: " + err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Validated
	@GetMapping(value = "", headers = "Accept=application/json")
	public ResponseEntity<Object> getAll() {
		try {
			List<Chamado> chamados = chamadoService.getChamado();
			return ResponseEntity.status(HttpStatus.OK).body(chamados);
		} catch (Exception err) {
			StringResponse response = new StringResponse("Ocorreu um erro ao buscar os chamados: " + err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Validated
	@GetMapping(value = "/{cod}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getByCodigo(@PathVariable("cod") String cod) {
		try {
			Chamado chamado = chamadoService.findByCod(cod);
			if (chamado == null) {
				StringResponse response = new StringResponse("O registro " + cod + " não foi encontrado.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			return ResponseEntity.status(HttpStatus.OK).body(chamado);
		} catch (Exception err) {
			StringResponse response = new StringResponse("Ocorreu um erro ao buscar o chamados: " + err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Validated
	@PutMapping(value = "", headers = "Accept=application/json")
	public ResponseEntity<Object> update(@Valid @RequestBody Chamado atualizacao) {
		Chamado chamado = chamadoService.findByCod(atualizacao.getCodigo());
		if (chamado == null) {
			StringResponse response = new StringResponse(
					"Não foi encontrando nenhum chamado com o Código " + atualizacao.getCodigo());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} else {
			chamado.atualizaChamado(atualizacao);
			chamadoService.update(chamado);
			StringResponse response = new StringResponse("O chamado foi atualizado com sucesso.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String cod) {
		Chamado chamado = chamadoService.findByCod(cod);
		if (chamado == null) {
			StringResponse response = new StringResponse("Não foi encontrando nenhum chamado com o Código " + cod);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		chamadoService.deleteChamadoByCod(cod);
		StringResponse response = new StringResponse("O chamado " + cod + " foi excluído");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
