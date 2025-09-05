Sistema de Gestão de Projetos (SGP)
Sobre o Projeto
O Sistema de Gestão de Projetos (SGP) é uma aplicação de desktop desenvolvida como parte das atividades académicas da Universidade do Sul de Santa Catarina (Unisul). O objetivo principal do projeto foi aplicar os conceitos de Engenharia de Software, programação orientada a objetos com Java e gestão de bases de dados relacionais para criar uma solução de software funcional e bem estruturada.

A aplicação permite o controlo completo sobre utilizadores, projetos e tarefas, oferecendo diferentes níveis de acesso e relatórios para a tomada de decisões, simulando um ambiente corporativo de gestão.

Funcionalidades Principais
O sistema foi desenvolvido para cumprir um conjunto de requisitos explícitos, resultando nas seguintes funcionalidades:

Autenticação Segura: Tela de login que valida as credenciais no banco de dados. As senhas são armazenadas de forma segura utilizando hash SHA-256.

Gestão de Utilizadores:

Cadastro, edição, listagem e exclusão de utilizadores.

Atribuição de perfis de acesso: Administrador, Gerente e Colaborador.

Gestão de Projetos:

Cadastro de novos projetos com nome, descrição, datas e status.

Associação de um gerente responsável a cada projeto.

Gestão de Tarefas:

Cadastro de tarefas detalhadas.

Vinculação de cada tarefa a um projeto específico e a um utilizador responsável.

Módulo de Relatórios:

Andamento dos Projetos: Visualização consolidada de todos os projetos, status e gerentes.

Desempenho dos Colaboradores: Tabela que exibe o total de tarefas atribuídas e concluídas por cada utilizador.

Projetos em Risco: Relatório que filtra e exibe projetos não concluídos cuja data final prevista já foi ultrapassada.

Tecnologias e Arquitetura
Tecnologias Utilizadas
Linguagem: Java (JDK 11 ou superior)

Interface Gráfica: Java Swing

Banco de Dados: MySQL 8

Gestor de Dependências: Apache Maven

Driver de Conexão: MySQL Connector/J

Arquitetura de Software
O projeto foi estruturado seguindo os princípios da separação de responsabilidades, utilizando uma arquitetura em camadas inspirada nos padrões MVC (Model-View-Controller) e DAO (Data Access Object).

model: Contém as classes de entidade (POJOs) que espelham as tabelas da base de dados (Usuario, Projeto, Tarefa).

view: Responsável por todas as classes da interface gráfica (TelaLogin, TelaPrincipal, etc.).

dao: Camada de acesso a dados. Contém todo o código SQL e a lógica para comunicar com o banco de dados.

util: Classes utilitárias, como a de conexão com o banco de dados (ConexaoMySQL) e de segurança (SegurancaUtil).

main: Ponto de entrada da aplicação.

Pré-requisitos
Java Development Kit (JDK) - Versão 11 ou superior.

MySQL Server & Workbench - Versão 8.

Apache Maven (geralmente já vem integrado em IDEs modernas).

Uma IDE Java, como IntelliJ IDEA ou Eclipse.

Tecnologias e Arquitetura
Tecnologias Utilizadas
Linguagem: Java (JDK 11 ou superior)

Interface Gráfica: Java Swing

Banco de Dados: MySQL 8

Gestor de Dependências: Apache Maven

Driver de Conexão: MySQL Connector/J

Arquitetura de Software
O projeto foi estruturado seguindo os princípios da separação de responsabilidades, utilizando uma arquitetura em camadas inspirada nos padrões MVC (Model-View-Controller) e DAO (Data Access Object).

model: Contém as classes de entidade (POJOs) que espelham as tabelas da base de dados (Usuario, Projeto, Tarefa).

view: Responsável por todas as classes da interface gráfica (TelaLogin, TelaPrincipal, etc.).

dao: Camada de acesso a dados. Contém todo o código SQL e a lógica para comunicar com o banco de dados.

util: Classes utilitárias, como a de conexão com o banco de dados (ConexaoMySQL) e de segurança (SegurancaUtil).

main: Ponto de entrada da aplicação.

Como Executar o Projeto
Siga os passos abaixo para configurar e executar o sistema no seu ambiente de desenvolvimento.

Pré-requisitos
Java Development Kit (JDK) - Versão 11 ou superior.

MySQL Server & Workbench - Versão 8.

Apache Maven (geralmente já vem integrado em IDEs modernas).

Uma IDE Java, como IntelliJ IDEA ou Eclipse.

1. Configuração do Banco de Dados
Abra o MySQL Workbench e conecte-se à sua instância local.

Crie um novo schema (banco de dados) ou utilize o script fornecido. O script database_script.sql (disponível na raiz do projeto) cria o banco de dados gestao_projetos_db, as tabelas necessárias e insere um utilizador administrador padrão.

Execute o conteúdo completo do script database_script.sql.

2. Configuração do Projeto
Clone este repositório ou descarregue os ficheiros do projeto.

Abra o projeto na sua IDE como um "Existing Maven Project". A IDE irá descarregar automaticamente as dependências (como o MySQL Connector).

Navegue até o ficheiro src/main/java/br/com/gestaoprojetos/util/ConexaoMySQL.java.


