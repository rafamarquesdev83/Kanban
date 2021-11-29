# KanbanService
Esse projeto ilustra a estrutura gerada através do Maven utilizando o JDK11

## Começando
Para executar o projeto, será necessário instalar os seguintes programas:
- JDK 11
- Maven 3.8.4
- Eclipse

## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/rafamarquesdev83/Kanban.git
```

### Construção

Para construir o projeto com o Maven, execute os comandos abaixo:

```shell
mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Features

O projeto pode ser usado como modelo para iniciar o desenvolvimento de um projeto Java usando o Maven. Ele também demonstra de forma prática como configurar o Maven em um projeto Java.

## Configuração

Para executar o projeto, é necessário utilizar o Eclipse, para que o mesmo identifique as dependências necessárias para a execução no repositório .m2 do Maven. Uma vez importado o projeto, será criado um arquivo *.classpath* que irá informar qual a classe principal para a execução.

## Licença

Não se aplica.