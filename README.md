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

# Configuraçao da app
- A App conta com um arquivo application.properties onde:
  - Conta com a URL de consulta externa para a validação de documento
  - Flag ("br.com.voteapp.externalvalidator") que indica se deve haver consulta externa ao validador de documento (CPF);
 
# Versionamento da API
- Por convenção, sempre utilizei "/v1/", "/v2"..."/vN/" para versionar APIs e a anotação @Deprecated no controller.

# Considerações:
- Utilizei a estratégia de interfaces "<name>Service" e a implementação "<name>ServiceImpl" para garantir a injeção de agnóstica da classe, sem expor a implementação, em um cenário mais complexo, com múltiplas classes, qualificadores poderiam orientar a classe que seria importada para o Framework; 
