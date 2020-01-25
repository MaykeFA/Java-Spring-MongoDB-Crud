package spring.model;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chamados")
public class Chamado {
	@Id
	private String codigo;

	private String tipo;

	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	public Chamado() {

	}

	public Chamado(String tipo, String descricao, Date data) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.data = data;
	}

	public void atualizaChamado(Chamado chamado) {
		if (chamado.getData() != null) {
			setData(chamado.getData());
			System.out.println("Atualizando DATA");
		}
		if (chamado.getDescricao() != null) {
			setDescricao(chamado.getDescricao());
			System.out.println("Atualizando DES");
		}
		if (chamado.getTipo() != null) {
			setTipo(chamado.getTipo());
			System.out.println("Atualizando TIPO");
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
