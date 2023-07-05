# VoteApp

Proposta: Criar aplicativo que permita realizar votação de uma assembleia (Agenda) de forma performática, utilizando requisitos de Clean Code e framework Spring.

# Frameworks e Libs utilizadas
- SpringBoot;
- OpenFeign (comunicação externa);
- MongoDB para armazenamento;
- OpenAI para documentação/swagger;
- JUnit;
- Mockito para testes unitários;

# Desafios técnicos
- Concorrência de múltiplos usuários votantes:
  - Solução: A classe: https://github.com/carlosbridi/voteapp/blob/main/src/main/java/br/com/vote/service/impl/RegisterVoteImpl.java, que realiza o integraliza o voto, utiliza o método .inc do Update, para evitar carregar o objeto em memória, evitando tráfego de recuperar, alterar e persistir, esse método delega ao banco de dados a responsabilidade, atômica de fazer o update do registro e contabilizar sem problemas de concorrência.
- Validação de somente um voto por cooperado/assembleia
  - Implementado @Cacheable na rotina de busca do banco de dados por cooperador/assembléia.
- Implementei uma rotina de cronJob para encerrar a Assembleia, definida aqui: https://github.com/carlosbridi/voteapp/blob/main/src/main/java/br/com/vote/service/impl/AgendaJobFinish.java que, roda a cada 5 segundos buscando as Assembleias que ultrapassaram o prazo e encerrando elas;
    - Esse processo também é delegado ao banco de dados evitando recuperar, iterar, alterar e persistir.
 
# Exceções
- Escolhi tratar com 3 exceções base para o projeto, uma mais abrangente e as duas mais especificas. O Handle da exceção para o controller ocorre aqui: 
 https://github.com/carlosbridi/voteapp/blob/main/src/main/java/br/com/vote/exception/RestResponseEntityExceptionHandler.java

# Configuração da app
- A App conta com um arquivo application.properties onde:
  - Conta com a URL de consulta externa para a validação de documento
  - Flag ("br.com.voteapp.externalvalidator") que indica se deve haver consulta externa ao validador de documento (CPF);
 
# Versionamento da API
- Por convenção, sempre utilizei "/v1/", "/v2"..."/vN/" para versionar APIs e a anotação @Deprecated no controller.

# Considerações:
- Considerando o tempo para que uma Assembleia fique ativa, criei no Documento respectivo um campo de ttlInMinutes (tempo para vida de votação), definido aqui: https://github.com/carlosbridi/voteapp/blob/main/src/main/java/br/com/vote/documents/Agenda.java#L35 que soma horário atual mais os minutos definidos, caso não informado, 1 como default.
- Utilizei a estratégia de interfaces "<name>Service" e a implementação "<name>ServiceImpl" para garantir a injeção de agnóstica da classe, sem expor a implementação, em um cenário mais complexo, com múltiplas classes, qualificadores poderiam orientar a classe que seria importada para o Framework; 
- Foram implementados testes unitários das rotinas;

# Rodar App
- Clonar a app localmente
- Iniciar ela e acessar: http://localhost:8080/swagger-ui/index.html
- Criar uma Agenda (Assembléia)
- Copiar o ID retornado e informar o ID no endoint de votação junto com o documento (cpf) do votante. No corpo da requisição, deverá ser informado YES ou NO como opções para voto.
