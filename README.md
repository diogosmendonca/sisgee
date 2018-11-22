# sisgee

Instruções de instalação:


1.Crie um banco de dados no postgres com o nome sisgeeDB e ajuste o login e senha no persistence.xml do projeto.

2.Na pasta sisgee/src/main/webapp/ utilize o [npm](https://nodejs.org/en/)  (gerenciador de pacotes javascript do node.js, instale ele se você não tiver já instalado) para baixar as dependências Javascript do projeto executando o seguinte comando:
```
npm install
```

3.Gere o arquivo de implantação utilizando o Maven. Na raiz do projeto, onde está o pom.xml, execute o comando abaixo. Será gerado o arquivo target/sisgee.war
```
mvn install
```

4.Implante o sistema no tomcat movendo o arquivo sisgee.war para a pasta <instalacao do tomcat>/webapps

5.Inicie o tomcat e acesse a url no browser http://localhost:8000/sisgee/

############################   Importante - Atualização especial do Grupo Roxo ############################

Pra efeito de testes, deem drop em QUALQUER database do sisgee previamente criada pois fizemos alterações em estruturas de tabelas e caso estas alterações não estejam presentes, a aplicação apresentará falhas.

############################################################################################
