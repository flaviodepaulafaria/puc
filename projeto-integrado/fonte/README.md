Esta aplicação é uma implementação de arquitetura orientada a eventos (EDA) utilizando SpringBoot e Kafka para comunicação assíncrona com MongoDB para persistência de dados. Abaixo um resumo das funcionalidades principais:

1. **Recepção e Envio de Dados GPS**:
   - A classe `GpsController` recebe dados GPS via uma requisição HTTP POST e os envia para um tópico Kafka usando a classe `KafkaProducer`.

2. **Processamento de Mensagens Kafka**:
   - A classe `KafKaTopicListeners` escuta mensagens de um tópico Kafka e registra as mensagens recebidas.

3. **Consulta de Dados em Pinot**:
   - A classe `PinotController` permite consultas a um banco de dados Pinot via uma requisição HTTP GET. A classe `PinotService` realiza a consulta e exporta os resultados para um arquivo CSV.

4. **Gerenciamento de Veículos**:
   - A classe `VeiculoController` permite a criação e consulta de veículos no MongoDB. A classe `VeiculoService` gerencia as operações de banco de dados usando o repositório `VeiculoRepository`.

5. **Configuração e Dependências**:
   - O arquivo pom.xml define as dependências do projeto, incluindo Spring Boot, Kafka, MongoDB, e OpenFeign para chamadas HTTP.
   - O arquivo application.properties contém configurações como a URL do MongoDB, configurações do Kafka e a URL do Pinot.

6. **Para executar a aplicação**: 

`mvn spring-boot:run`

6. **A seguir um resumo das rotas principais da aplicação**:
   
Recepção de Dados GPS:

Método: POST
Rota: /api/v1/gps
Classe: GpsController
Descrição: Recebe dados GPS e os envia para um tópico Kafka.
Consulta de Dados em Pinot:

Método: GET
Rota: /api/v1/pinot
Classe: PinotController
Descrição: Realiza consultas a um banco de dados Pinot e exporta os resultados para um arquivo CSV.
Gerenciamento de Veículos:

Método: POST

Rota: /api/v1/veiculos

Classe: VeiculoController

Descrição: Cria um novo veículo no MongoDB.

Método: GET

Rota: /api/v1/veiculos

Classe: VeiculoController

Descrição: Consulta veículos no MongoDB.

![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\springboot_run.PNG)
