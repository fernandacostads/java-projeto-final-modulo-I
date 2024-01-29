<a name="readme-top"></a>
 # Projeto: Agenda de Contatos em Java

 ## Visão geral
 O projeto "Agenda de Contatos" é uma aplicação Java 17 desenvolvida com o gerenciador de dependências Maven. Seu propósito é permitir o registro, edição, remoção e visualização de contatos, armazenando dados em arquivos de texto para simular uma base de dados.

 ## Estrutura da Aplicação
 A estrutura da aplicação segue o seguinte formato:

 ```
 ./
 |-- src/
 |    |-- main/
 |         |-- java/
 |              |-- com/
 |                   |-- adatech
 |                        |-- Agenda.java
 |                        |-- AgendaController.java
 |                        |-- AgendaFileHandler.java
 |                        |-- Contato.java
 |                        |-- Main.java  
 |                        |-- Telefone.java
 |
 |-- target/
 |-- .gitignore
 |-- agenda.txt
 |-- pom.xml
 |-- README.md
 ```

## Execução do Projeto
Para executar o projeto, siga os passos abaixo:

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute o seguinte comando no terminal:
```
$ git clone git@github.com:fernandacostads/java-projeto-final-modulo-I.git
$ cd java-projeto-final-modulo-I
$ java -cp target/classes com.adatech.Main
```

## Funcionalidades
A aplicação oferece as seguintes funcionalidades:

### 1. Visualização de Contatos
A tela principal da aplicação exibe a lista de contatos registrados na agenda, mostrando o ID e o Nome de cada contato.
```
##################
##### AGENDA #####
##################
>>>> Contatos <<<<
Id | Nome
1 | Alex Araujo
2 | Joao Gomes
3 | Silvio Santos
```

### 2. Menu de Operações
A aplicação possui um menu interativo que permite ao usuário realizar diversas operações na agenda, como adicionar, remover, editar contatos ou sair da aplicação.
```
>>>> Menu <<<<
1 - Adicionar Contato
2 - Remover Contato
3 - Editar Contato
4 - Sair
```
### 3. Requisitos Não-Funcionais
Armazenamento de Dados: Utiliza arquivos de texto para armazenar os dados, simulando uma base de dados.
```
/agenda.txt
```
## Requisitos Funcionais

## Requisitos Funcionais

| **RN** | **Descrição**                                                                                          |
|--------|--------------------------------------------------------------------------------------------------------|
| RN1    | Não é permitido armazenar contatos com o mesmo ID.                                                     |
| RN2    | Não é permitido armazenar contatos com telefones já cadastrados. A aplicação verifica duplicatas.      |
| RN3    | Para realizar as ações, será necessário informar o ID do contato.                                       |


### RN1: Não é permitido armazenar contatos com o mesmo id
A aplicação valida e garante que não há contatos duplicados com o mesmo ID.

### RN2: Não é permitido armazenar contatos com telefones já cadastrados
A aplicação verifica se já existe um contato com o mesmo número de telefone antes de permitir o cadastro.

### RN3: Para realizar as ações, será necessário informar o id do contato
Para editar ou remover um contato, é necessário fornecer o ID correspondente ao contato desejado.

## Encerramento

Obrigado por explorar a documentação do projeto "Agenda de Contatos em Java". Fique à vontade para contribuir, reportar problemas ou sugerir melhorias.


> #### A persistência é o caminho do êxito.
>
> -- Charles Chaplin


<p align="center">
  <a href="https://github.com/fernandacostads">
    <img src="https://avatars.githubusercontent.com/u/59060824?v=4" width="100px" height="100px">
  </a>
</p>
<p align="center">
💬<a href="https://github.com/fernandacostads#-contacts">Contato</a>💬

<p align="right">(<a href="#readme-top">Voltar ao topo</a>)</p>