package spring.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spring.model.Chamado;
import spring.service.StringResponse;

public class ChamadoValidator {
    private List<StringResponse> msg;

    public ChamadoValidator(Chamado chamado) {
        msg = new ArrayList<StringResponse>();
        validateDescricao(chamado.getDescricao());
        validateTipo(chamado.getTipo());
        validateData(chamado.getData());
    }

    public List<StringResponse> getMessage() {
        if (msg.size() >= 1)
            return msg;
        else
            return null;
    }

    public void validateDescricao(String descricao) {
        if (descricao == "") {
            StringResponse response = new StringResponse("A descrição deve ser preenchida.");
            msg.add(response);
        } else if (descricao.length() < 10) {
            StringResponse response = new StringResponse("A descrição deve conter ao menos 10 caracteres.");
            msg.add(response);
        }
    }

    public void validateTipo(String tipo) {
        if (tipo == "") {
            StringResponse response = new StringResponse("O tipo deve ser preenchido.");
            msg.add(response);
        } else if (!tipo.equalsIgnoreCase("Sugestão") && !tipo.equalsIgnoreCase("Reclamação")
                && !tipo.equalsIgnoreCase("Elogio")) {
            StringResponse response = new StringResponse("Os tipos aceitos são: Sugestão, Reclamação ou Elogio.");
            msg.add(response);
        }
    }

    public void validateData(Date data) {
        if (data == null) {
            StringResponse response = new StringResponse("O data deve ser preenchida.");
            msg.add(response);
        }
    }
}
