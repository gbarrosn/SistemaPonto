Sistema de Ponto Integrado para o Expresso Cidadão
Descrição

Este projeto, desenvolvido em Java utilizando as tecnologias Ant, MySQL, Java 23 e NetBeans, tem como objetivo automatizar a gestão de ponto eletrônico dos servidores do Programa Expresso Cidadão. O sistema oferece uma solução completa e personalizada, otimizando processos e garantindo a conformidade com a legislação trabalhista.
Tecnologias Utilizadas

    Linguagem de Programação: Java 23
    Framework de Build: Ant
    Banco de Dados: MySQL
    IDE: NetBeans (compatível com VS Code e outras IDEs Java)
    Biblioteca: Biblioteca Java para MySQL (especifique a biblioteca utilizada)

Pré-requisitos

    Java Development Kit (JDK): Versão 23 ou superior.
    Apache Ant: Instalado e configurado.
    MySQL: Instalado e em execução.
    IDE: NetBeans, VS Code ou outra IDE Java.
    Usuário MySQL com privilégios de DBA: Para criação inicial do banco de dados e das tabelas.

Instalação

    Clonar o repositório:
    Bash

    git clone https://github.com/gbarrosn/SistemaPonto.git

    Importar o projeto:
        NetBeans: Importe como projeto Ant.
        VS Code: Abra a pasta do projeto e configure o ambiente Java.
        Outras IDEs: Siga as instruções específicas da sua IDE.

Configuração

    Banco de dados:
        Inicie o serviço MySQL.
        Edite db.conectarBanco: Configure o usuário e senha do banco de dados.
        Crie o banco de dados e as tabelas: Execute as funções criarBanco e criarTabelas na classe db.conectarBanco. Certifique-se de que o usuário tenha privilégios de DBA.

Execução

    Compilar: Execute o script Ant para compilar o projeto.
    Executar: Execute a classe principal (GUI.telaInicial).
    No Apache NetBeans, o projeto já está configurado para executar e compilar, e no VSCode, basta rodar a classe GUI.telaInicial.

Estrutura do Projeto

    GUI: Interface gráfica do usuário.
    db: Classes de conexão e entidades do banco de dados.
    model: Classes que representam os objetos do domínio.
    resources: Arquivos de recursos (imagens, etc.).

Observações

    Biblioteca MySQL: Já incluída e configurada para as IDEs mencionadas.
    Primeira execução: Crie o banco de dados e as tabelas na primeira execução.
    Privilégios de DBA: Necessários para a criação inicial do banco de dados.
    Ambiente de desenvolvimento: Configure um ambiente virtual para isolar dependências.
    Controle de versão: Utilize Git para gerenciar versões.
    Documentação: Documente o código e as classes.
    Testes: Com as minhas atribuições, tenho prazos apertados e várias demandas diferentes, não fiz testes unitários, mas sintam-se à vontade para me ajudar!

Dicas Adicionais

    Configuração do Ant: Verifique a configuração do arquivo build.xml se necessário.
    Gerenciamento de dependências: Utilize Maven ou Gradle para gerenciar dependências externas.
    Logs: Implemente um sistema de logs para rastrear erros e acompanhar o funcionamento do sistema.
    Segurança: Implemente medidas de segurança para proteger os dados dos usuários.

