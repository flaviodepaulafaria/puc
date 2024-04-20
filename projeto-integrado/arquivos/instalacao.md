#### Ambiente de desenvolvimento utilizado para implementação do protótipo computacional

- ###### Mongo 

  - instalar o mongosh (windows) : https://www.mongodb.com/pt-br/docs/mongodb-shell/install/
  - Criar e configurar organização e projeto via interface Gráfica (https://cloud.mongodb.com/)
    - ​	user: mongo pass : mongo
    - ​	Crie uma organização ex.: PUC
    - ​	Crie um projeto com o nome TrackBZ.
  - Criar o cluster via Interface gráfica em Deployment -> Database
  - conectar e criar o bd: mongosh "`mongodb+srv://cluster0.zyomj46.mongodb.net/trackbz-db" --apiVersion 1 --username mongo --password mongo`
  - Crie um índice para o campo veiculo_id na coleção de veículos;Crie um índice para o campo veiculo_id na coleção de veículos: `db.veiculos.createIndex( { "veiculo_id": 1 }, { unique: true } )`
  - Crie a coleção de veículos e insere com o nome dos motoristas utilizando o MongoSH:
  - Crie a coleção de veículos e insere com o nome dos motoristas utilizando o Compass (GUI):
    - Crie a coleção de veículos e insere com o nome dos motoristas:
      `db.veiculos.insertOne( { veiculo_id: 10, nome_motorista: "João da Silva" } );`
      `db.veiculos.insertOne( { veiculo_id: 20, nome_motorista: "José Duarte" } );`
      `db.veiculos.insertOne( { veiculo_id: 30, nome_motorista: "Maria Aparecida" } );`
    - Listar a coleção de veículo: db.veiculos.find({}, {veiculo_id:1, nome_motorista:1, _id:0}) 

![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\mongodb.png)

***OBS: utilizando o Mongo Compass (https://www.mongodb.com/try/download/compass) é possível se conectar ao banco para criar e manipular a coleção utilizando a string de conexão do cluster: `mongodb+srv://mongo:mongo@cluster0.zyomj46.mongodb.net/`*** 

![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\compass.png)

- ###### Kafka

  - criar conta: https://confluent.cloud/signup

  ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_cluster.png)

  - instalar cli (windows): `curl -sL --http1.1 https://cnfl.io/cli | sh -s -- -l`
  - executar o comando no cli: `confluent login --save --no-browser`

  ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_login.png)

  - associar a *API Key* gerada ao cluster

    ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_apikkey_cluster.png)

  - criar o tópico "**topic_gps**" no cluster_0 (criado automaticamente na criação da conta)

  - ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_topic_gps.png)

  - criar API Key: confluent api-key create --resource <<id do cluster>>

  ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_apikey.png)

  - associar  API key ao cluster

  ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_apikkey_cluster.png)

  - criar conector do MongoDB: O conector irá alimentar um tópico gerado automaticamente com os dados existentes na coleção no MongoDB. A cada inserção do nome do motorista associado ao veículo no MongoDB, será gerado um evento neste tópico em tempo real.

    - clicar em Connectors e selecione a opção MongoDB Atlas Source

    - definir um prefixo: db

    - selecionar a opção "Use an existing API Key"

      - Connection host: cluster0.zyomj46.mongodb.net (O mesmo host utilizado para conectar no banco com o mongosh e compass gui)
      - Connection user: mongo
      - Connection password: mongo
      - Database name: trackbz-db
      - Collection name: veiculos

    - Selecionar a opção "Output Kafka record value format: JSON" e expanda a opção "Show advanced configurations"

    - Alterar o Startup mode para "copy_existing"

    - criar trasnformações: ""Transforms -> Single Message Transform", clicar em "Add SMT" e adicione as transformações:

      - 	Transform type: ExtractField$Value
        	Field: fullDocument
        	
        	Transform type: ReplaceField$Value
        	Field (exclude): _id
        	(deixe em branco os outros campos) 
        	
        	Transform type: ReplaceField$Value
        	Field (exclude): _class
        	(deixe em branco os outros campos) 
        	
        	Transform type: ValueToKey
        	Field: veiculo_id
        	
        	Transform type: ExtractField$Key
        	Field: veiculo_id
        	

    - clicar em "Continue" até finalizar e aguardar o status "running"

    

    ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\mongodb_conector.png)

    

- ###### KSQL

  - clicar no botão "Create cluster myself"
    - Cluster name: trackbz_ksqlDB_cluster
    - Cluster size: 1
    - Configuration options: default

  - acessar o cluster e criar o stream: `CREATE STREAM tracking_stream (veiculo_id VARCHAR, localizacao STRUCT<latitude DOUBLE, longitude DOUBLE>, datahora BIGINT)WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'topic_gps');`
  - criar a tabela: `CREATE TABLE veiculos (veiculo_id VARCHAR PRIMARY KEY, nome_motorista VARCHAR) WITH (KAFKA_TOPIC = 'db.trackbz-db.veiculos', VALUE_FORMAT = 'JSON');`
  - criar o stream tracking_enriched_stream no ksqlDB e criar/associar ao tópico localizacoes_enriquecidas no Kafka (Se o tópico não existir, ele será criado automaticamente no Kafka): `CREATE STREAM tracking_enriched_stream WITH (kafka_topic = 'tracking_enriched', VALUE_FORMAT = 'JSON') AS SELECT lo.veiculo_id as id, AS_VALUE(lo.veiculo_id) as veiculo_id, ve.nome_motorista as motorista, lo.localizacao->latitude as latitude, lo.localizacao->longitude as longitude, lo.datahora as datahora FROM tracking_stream lo JOIN veiculos ve ON lo.veiculo_id = ve.veiculo_id emit changes;`



![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\kafka_ksql.png)





- Pinot

  - criar conta na *StarTree* (https://startree.cloud/)

  ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\starttree.PNG)

  - criar o "*dataset"* do tipo "Kafka"

    - **Connection name**: conexao-kafka-demo

    - **Broker Url**: XXXX.confluent.cloud:9092 (***Confluent Cloud***-> **Cluster overview** -> **Cluster Settings** -> **Endpoints** -> **Bootstrap server**.)

    - Authentication Type: SASL

      - **Security Protocol**: SASL_SSL
      - **SASL Mechanism**: PLAIN
      - **Username**: *api-key* (***Confluent Cloud***-> **Cluster overview** -> **API Keys**.)
      - **Password**: *api-secret*  (***Confluent Cloud***-> **Cluster overview** -> **API Keys**.)

      ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\starttree_dataset_kafka.PNG)

    - 

    - Editar "**Data Details"**

      - **Dataset Description - name**: tracking_enriched
      - **Kafka input format - Topic name**: tracking_enriched
      - **Kafka input format - Data format**: JSON

      ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\starttree_dataset_kafka_details.PNG)

    - Exemplo dos dados retornados

    ![](C:\FPF\Pessoal\PUC\projeto integrado\projetos\trackBZ\imagens\starttree_dataset_kafka_data.PNG)

    - criar uma nova coluna de data e hora e alterar o tipo da coluna DATAHORA
      - **Column name**: TS
      - **Field type**: DATETIME
    - selecionar a chave primária: veiculo_id
    - avançar até a etapa "Preview Data" e clicar em "Create Dataset"