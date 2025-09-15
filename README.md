<!DOCTYPE html>
<html lang="PT-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head> 
<body> 
  <h1>Sistema de Gestão de Projetos (SGP)</h1>
  <h2>Sobre o Projeto</h2>
  <p>O Sistema de Gestão de Projetos (SGP) é uma aplicação de desktop desenvolvida como parte das atividades academicas da Universidade do Sul de Santa Catarina (Unisul). O objetivo principal do projeto foi aplicar os conceitos de Engenharia de Software, programação orientada a objetos com Java e gestão de bases de dados relacionais para criar uma solução de software funcional e bem estruturada. A aplicação permite o controle completo sobre utilizadores, projetos e tarefas, oferecendo diferentes niveis de acesso e relatórios para  tomada de decisões, simulando um ambiente corporativo de gestão.</p>
  <h2>Funcionalidades Principais</h2>
<p>O sistema foi desenvolvido para cumprir um conjunto de requisitos explícitos, resultando nas seguintes funcionalidades:</p>
<strong>Autenticação Segura:</strong> <p>Tela de login que valida as credenciais no banco de dados. As senhas são armazenadas de forma segura utilizando hash SHA-256.</p>
<strong>Gestão de Utilizadores:</strong> <p>Cadastro, edição, listagem e exclusão de utilizadores.</p>
<strong>Atribuição de perfis de acesso:</strong> <p>Administrador, Gerente e Colaborador.</p>  
<strong>Gestão de Projetos:</strong> <p>Cadastro de novos projetos com nome, descrição, datas e status. Associação de um gerente responsável a cada projeto.</p>
<strong>Gestão de Tarefas:</strong> <p>Cadastro de tarefas detalhadas. Vinculação de cada tarefa a um projeto específico e a um utilizador responsável.</p>
<strong>Módulo de Relatórios:</strong>
<strong>Andamento dos Projetos:</strong> <p>Visualização consolidada de todos os projetos, status e gerentes.</p>
<strong>Desempenho dos Colaboradores:</strong> <p>Tabela que exibe o total de tarefas atribuídas e concluídas por cada utilizador.</p>
<strong>Projetos em Risco:</strong> <p>Relatório que filtra e exibe projetos não concluídos cuja data final prevista já foi ultrapassada.</p>
  
<h2>Tecnologias e Arquitetura</h2>
<h3>Tecnologias Utilizadas</h3>
<strong>Linguagem:</strong> <p>Java (JDK 11 ou superior)</p>
<strong>Interface Gráfica:</strong> <p>Java Swing</p>
<strong>Banco de Dados:</strong> <p>MySQL 8</p>
<strong>Gestor de Dependências:</strong> <p>Apache Maven</p>
<strong>Driver de Conexão:</strong> <p>MySQL Connector/J</p>

<h2>Arquitetura de Software</h2>
<p>O projeto foi estruturado seguindo os princípios da separação de responsabilidades, utilizando uma arquitetura em camadas inspirada nos padrões MVC (Model-View-Controller) e DAO (Data Access Object).</p>

<strong>model:</strong> <p>Contém as classes de entidade (POJOs) que espelham as tabelas da base de dados (Usuario, Projeto, Tarefa).</p>
<strong>view:</strong> <p>Responsável por todas as classes da interface gráfica (TelaLogin, TelaPrincipal, etc.).</p>
<strong>dao:</strong> <p>Camada de acesso a dados. Contém todo o código SQL e a lógica para comunicar com o banco de dados.</p>
<strong>util:</strong> <p>Classes utilitárias, como a de conexão com o banco de dados (ConexaoMySQL) e de segurança (SegurancaUtil).</p>
<strong>main:</strong> <p>Ponto de entrada da aplicação.</p>
<strong>model:</strong>

<h2>Pré-requisitos</h2>
<strong>Java Development Kit (JDK) - Versão 11 ou superior.</strong>
<strong>MySQL Server & Workbench - Versão 8.</strong>
<strong>Apache Maven (geralmente já vem integrado em IDEs modernas).</strong>
<strong>Uma IDE Java, como IntelliJ IDEA ou Eclipse.</strong>

<h2>Configuração do Banco de Dados</h2>
<p>Abra o MySQL Workbench e conecte-se à sua instância local, em seguida crie um novo schema (banco de dados). O script database_script.sql cria o banco de dados gestao_projetos_db, as tabelas necessárias e insere um utilizador administrador padrão. Execute o conteúdo completo do script database_script.sql. </p>
<h2>Configuração do Projeto</h2>
<p>Clone este repositório ou descarregue os ficheiros do projeto. Abra o projeto na sua IDE como um "Existing Maven Project". A IDE irá descarregar automaticamente as dependências (como o MySQL Connector). Navegue até o ficheiro src/main/java/br/com/gestaoprojetos/util/ConexaoMySQL.java.</p>
</body>
</html>


