# Votacao

## Para rodar projeto:

    Para rodar o projeto é necessário possuir
      - JDK 11
      - Maven
    Caso esteja no Mac ou Windows, ter o Docker Desktop

    Na pasta raiz do projeto, executar os comandos:
    - mvn clean install
    - Se for a primeira vez que o projeto vai ser executado: 
        - docker-compose up --build
    - Se já tiver executado o projeto: 
        - docker-compose up
    
    O Swagger do projeto pode ser acessado no link
    - http://localhost:8080/swagger-ui.html

## Objetivo:

Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

Cadastrar uma nova pauta
Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
Contabilizar os votos e dar o resultado da votação na pauta

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java, usando Spring-boot, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

O projeto utiliza Java 11, Spring Boot, Maven e MySQL.