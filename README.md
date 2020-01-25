### Configuração de Banco de Dados

Para este projeto, foi utilizado o MongoDB. Para facilitar as coisas, foi criado um ambiente _sandbox_ no AtlasDB, o qual foi utilizado no código.
Caso queira realizar os testes com um banco local, basta editar o arquivo **src\main\resources\application.properties.**, alterando a propriedade **spring.data.mongodb.uri** para a string de conexão com o seu banco de dados.

### Subindo a aplicação

1. Instale as depêndencias:
   mvn clean install
1. Suba a aplicação.
   mvn spring-boot:run

Com isso, você já terá um servidor de aplição rodando a API.

### Testando a API

##### Obter todos os chamados

Método: GET
Endpoint: http://localhost:8080/api/chamado
Parâmetros: nenhum
Restrições: nenhuma

##### Obter um chamado pelo código

Método: GET
Endpoint: http://localhost:8080/api/chamado/:codigo
Paramêtros:
-- Pathparam: cod
Restrições: nenhuma

> Exemplo: Caso queira recuperar o chamado de código 123, seria enviada uma requisição GET para o endpoint http://localhost:8080/api/chamado/123

##### Incluir um novo chamado

Método: POST
Endpoint: http://localhost:8080/api/chamado
Paramêtros:
-- Body: Json incluindo os campos tipo, descricao e data.
Restrições:
-- Tipo: deve ser Sugestão, Reclamação ou Elogio
-- Descrição: ao menos 10 caracteres.
-- Data: no formato dd/MM/yyyy

> Exemplo: Caso queira registrar um chamado de Sugestão, com a descrição: "melhorar o suporte do site", na data de 25/01/2020, deve-se enviar uma requisição do tipo POST para o endpoint http://localhost:8080/api/chamado enviando o seguinte JSON no body da requisição:

{
"tipo": "Sugestão",
"descricao": "melhorar o suporte do site",
"data": "20/01/2020"
}

##### Atualizar um chamado já existente

Método: PUT
Endpoint: http://localhost:8080/api/chamado
Paramêtros:
-- Body: Json com o código do chamado que será atualizado, e pelo menos um par chave valor para atualizar;
Restrições:
-- O código informado deve existir;

> Exemplo: O método POST anterior gerou um chamado com o código 1234, no entanto, a data informada estava errada. Neste caso, deve-se enviar uma requisição do tipo PUT para o endpoint http://localhost:8080/api/chamado com o seguinte JSON no body da requisição:

    	{
    		"codigo": "1234",
    		"data":  "25/01/2020"
    	}


##### Excluir um chamado

Método: DELETE
Endpoint: http://localhost:8080/api/chamado
Paramêtros:
-- PathParam: Código do chamado que se deseja excluir
Restrições:
-- O código informado deve existir;

> Exemplo: Caso queira excluir o chamado de código 1234 atualizado anteriormente, deve-se enviar uma requisição do tipo DELETE para o endpoint http://localhost:8080/api/chamado/1234
