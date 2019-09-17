/*

File name: C:\Users\gmpoy\Music\sisgee_postgre.sql

Creation date: 11/21/2017

Created by MySQL to PostgreSQL 3.3 [Demo]

--------------------------------------------------

More conversion tools at http://www.convert-in.com

*/



/*

Table structure for table 'public.agenteintegracao'

*/



DROP TABLE IF EXISTS "public"."agenteintegracao" CASCADE;

CREATE TABLE "public"."agenteintegracao" (

	"idAgenteIntegracao" SERIAL NOT NULL,

	"cnpjAgenteIntegracao" VARCHAR(42)  NOT NULL,

	"nomeAgenteIntegracao" VARCHAR(100)  NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."agenteintegracao" ADD PRIMARY KEY("idAgenteIntegracao");
DROP INDEX IF EXISTS "cnpjAgenteIntegracao_UNIQUE";

CREATE UNIQUE INDEX "cnpjAgenteIntegracao_UNIQUE" ON "public"."agenteintegracao"("cnpjAgenteIntegracao");
DROP INDEX IF EXISTS "idAgenteIntegracao_UNIQUE";

CREATE UNIQUE INDEX "idAgenteIntegracao_UNIQUE" ON "public"."agenteintegracao"("idAgenteIntegracao");


/*

Dumping data for table 'public.agenteintegracao'

*/



INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (1, '8334959000175', 'Abr Vencer R. Humanos');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (2, '10329223000183', 'Abre  Ag. Brasileira de Estudante Ltda. ');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (3, '9525685000164', 'Adepe - Assoc. de Desenv. Da Educ. e Prom. Do Estudante');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (4, '289809000185', 'Afamar  Assessoria RH');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (5, '68646500048', 'Alliage  Consult. Ass. S/C Ltda');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (6, '3543152000129', 'Assoc. de Apoio ao Estudante - AERJ');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (7, '8029517000115', 'Cia. de Est. PPM Human Resources Ltda - ME  ');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (8, '33661745000150', 'Ciee  Centro de Integração Empresa Escola');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (9, '73574386000119', 'Clave Consultoria Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (10, '5266318000132', 'DSRH  Desafios e Soluções em RH');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (11, '3038224000185', 'Foco R.H. Ltda  Grupo Foco');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (12, '33663519000109', 'Mudes  Fund. Mov. Univ. Desenv. E.S. ');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (13, '5316824000105', 'Gestão de Talentos Seres ');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (14, '9324352000177', 'Inst. Euvaldo Lodi Núcleo Regional - IEL');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (15, '5699372000171', 'Inst. Nacional de Capacit. Educ. p/Trabalho Via de Acesso');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (16, '8466536000109', 'Inst. Capacitare Consult.Empresarial Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (17, '12076828000102', 'Natpasi Consult. Empresarial Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (18, '2704396000183', 'Nube  Nucleo Bras. de Est. Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (19, '1194833000101', 'Parceria Consultoria Empresarial');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (20, '31571573000107', 'People On Time Consult. Planej. e Servs Temporários Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (21, '7620237000114', 'Recrutare e Consult. Gestão de R.H.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (22, '28317881000198', 'Secretaria de Est. Do Trabalho e Renda ( Setrab Cecope )');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (23, '3658267000169', 'Stag Central de Estágios SS Ltda.');

INSERT INTO "public"."agenteintegracao"("idAgenteIntegracao", "cnpjAgenteIntegracao", "nomeAgenteIntegracao") VALUES (24, '5166464000196', 'Talentos Consult. Em R.H. Ltda.');



/*

Table structure for table 'public.aluno'

*/



DROP TABLE IF EXISTS "public"."aluno" CASCADE;

CREATE TABLE "public"."aluno" (

	"idAluno" SERIAL NOT NULL,

	"matricula" VARCHAR(100)  NOT NULL,

	"situacao" VARCHAR(25)  NOT NULL,

	"idCurso" INTEGER NOT NULL,

	"idPessoa" INTEGER NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."aluno" ADD PRIMARY KEY("idAluno", "idCurso", "idPessoa");
DROP INDEX IF EXISTS "idAluno_UNIQUE";

CREATE UNIQUE INDEX "idAluno_UNIQUE" ON "public"."aluno"("idAluno");
DROP INDEX IF EXISTS "matricula_UNIQUE";

CREATE UNIQUE INDEX "matricula_UNIQUE" ON "public"."aluno"("matricula");
DROP INDEX IF EXISTS "fk_Aluno_Curso1_idx";

CREATE INDEX "fk_Aluno_Curso1_idx" ON "public"."aluno"("idCurso");
DROP INDEX IF EXISTS "fk_Aluno_Pessoa1_idx";

CREATE INDEX "fk_Aluno_Pessoa1_idx" ON "public"."aluno"("idPessoa");

/*

Table structure for table 'public.campus'

*/



DROP TABLE IF EXISTS "public"."campus" CASCADE;

CREATE TABLE "public"."campus" (

	"idCampus" INTEGER NOT NULL,

	"nomeCampus" VARCHAR(300)  NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."campus" ADD PRIMARY KEY("idCampus");
DROP INDEX IF EXISTS "idCampus_UNIQUE";

CREATE UNIQUE INDEX "idCampus_UNIQUE" ON "public"."campus"("idCampus");


/*

Table structure for table 'public.convenio'

*/



DROP TABLE IF EXISTS "public"."convenio" CASCADE;

CREATE TABLE "public"."convenio" (

	"idConvenio" SERIAL NOT NULL,

	"numeroConvenio" VARCHAR(30)  NOT NULL,

	"dataInicioConvenio" DATE,

	"dataFimConvenio" DATE,

	"idEmpresa" INTEGER NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."convenio" ADD PRIMARY KEY("idConvenio", "idEmpresa");
DROP INDEX IF EXISTS "numeroConvenio_UNIQUE";

CREATE UNIQUE INDEX "numeroConvenio_UNIQUE" ON "public"."convenio"("numeroConvenio");
DROP INDEX IF EXISTS "idConvenio_UNIQUE";

CREATE UNIQUE INDEX "idConvenio_UNIQUE" ON "public"."convenio"("idConvenio");
DROP INDEX IF EXISTS "fk_Convenio_Empresa1_idx";

CREATE INDEX "fk_Convenio_Empresa1_idx" ON "public"."convenio"("idEmpresa");


/*

Table structure for table 'public.curso'

*/



DROP TABLE IF EXISTS "public"."curso" CASCADE;

CREATE TABLE "public"."curso" (

	"idCurso" SERIAL NOT NULL,

	"codigoCurso" VARCHAR(50)  NOT NULL,

	"nomeCurso" VARCHAR(255)  NOT NULL,

	"idCampus" INTEGER NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."curso" ADD PRIMARY KEY("idCurso", "idCampus");
DROP INDEX IF EXISTS "codigoCurso_UNIQUE";

CREATE UNIQUE INDEX "codigoCurso_UNIQUE" ON "public"."curso"("codigoCurso");
DROP INDEX IF EXISTS "idCurso_UNIQUE";

CREATE UNIQUE INDEX "idCurso_UNIQUE" ON "public"."curso"("idCurso");
DROP INDEX IF EXISTS "fk_Curso_Campus1_idx";

CREATE INDEX "fk_Curso_Campus1_idx" ON "public"."curso"("idCampus");



/*

Table structure for table 'public.empresa'

*/



DROP TABLE IF EXISTS "public"."empresa" CASCADE;

CREATE TABLE "public"."empresa" (

	"idEmpresa" SERIAL NOT NULL,

	"cnpjEmpresa" VARCHAR(42)  NOT NULL,

	"nomeEmpresa" VARCHAR(100)  NOT NULL,

	"idAgenteIntegracao" INTEGER

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."empresa" ADD PRIMARY KEY("idEmpresa");
DROP INDEX IF EXISTS "cnpjEmpresa_UNIQUE";

CREATE UNIQUE INDEX "cnpjEmpresa_UNIQUE" ON "public"."empresa"("cnpjEmpresa");
DROP INDEX IF EXISTS "idEmpresa_UNIQUE";

CREATE UNIQUE INDEX "idEmpresa_UNIQUE" ON "public"."empresa"("idEmpresa");
DROP INDEX IF EXISTS "fk_Empresa_AgenteIntegracao1_idx";

CREATE INDEX "fk_Empresa_AgenteIntegracao1_idx" ON "public"."empresa"("idAgenteIntegracao");



/*

Table structure for table 'public.pessoa'

*/



DROP TABLE IF EXISTS "public"."pessoa" CASCADE;

CREATE TABLE "public"."pessoa" (

	"idPessoa" INTEGER NOT NULL,

	"cpf" VARCHAR(42)  NOT NULL,

	"nome" VARCHAR(100)  NOT NULL,

	"dataNascimento" DATE,

	"tipoEndereco" VARCHAR(100) ,

	"endereco" VARCHAR(255) ,

	"numeroEndereco" VARCHAR(10) ,

	"complementoEndereco" VARCHAR(150) ,

	"bairroEndereco" VARCHAR(150) ,

	"cepEndereco" VARCHAR(15) ,

	"distritoEndereco" VARCHAR(150) ,

	"cidadeEndereco" VARCHAR(150) ,

	"estadoEndereco" VARCHAR(2) ,

	"paisEndereco" VARCHAR(100) ,

	"email" VARCHAR(150) ,

	"ddiResidencial" INTEGER,

	"dddResidencial" INTEGER,

	"telefoneResidencial" VARCHAR(30) ,

	"ddiComercial" INTEGER,

	"dddComercial" INTEGER,

	"telefoneComercial" VARCHAR(30) ,

	"ddiCelular" INTEGER,

	"dddCelular" INTEGER,

	"telefoneCelular" VARCHAR(30) 

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."pessoa" ADD PRIMARY KEY("idPessoa");
DROP INDEX IF EXISTS "cpf_UNIQUE";

CREATE UNIQUE INDEX "cpf_UNIQUE" ON "public"."pessoa"("cpf");
DROP INDEX IF EXISTS "idPessoa_UNIQUE";

CREATE UNIQUE INDEX "idPessoa_UNIQUE" ON "public"."pessoa"("idPessoa");



/*

Table structure for table 'public.professororientador'

*/



DROP TABLE IF EXISTS "public"."professororientador" CASCADE;

CREATE TABLE "public"."professororientador" (

	"idProfessorOrientador" SERIAL NOT NULL,

	"nomeProfessorOrientador" VARCHAR(80)  NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."professororientador" ADD PRIMARY KEY("idProfessorOrientador");
DROP INDEX IF EXISTS "idProfessorOrientador_UNIQUE";

CREATE UNIQUE INDEX "idProfessorOrientador_UNIQUE" ON "public"."professororientador"("idProfessorOrientador");



/*

Table structure for table 'public.termoaditivo'

*/



DROP TABLE IF EXISTS "public"."termoaditivo" CASCADE;

CREATE TABLE "public"."termoaditivo" (

	"idTermoAditivo" INTEGER NOT NULL,

	"dataFimTermoAditivo" DATE,

	"cargaHorariaTermoAditivo" SMALLINT NOT NULL,

	"valorBolsaTermoAditivo" REAL NOT NULL,

	"enderecoTermoAditivo" VARCHAR(255)  NOT NULL,

	"numeroEnderecoTermoAditivo" VARCHAR(10)  NOT NULL,

	"complementoEnderecoTermoEstagio" VARCHAR(150)  NOT NULL,

	"bairroEnderecoTermoAditivo" VARCHAR(150)  NOT NULL,

	"cepEnderecoTermoAditivo" VARCHAR(15)  NOT NULL,

	"cidadeEnderecoTermoAditivo" VARCHAR(150)  NOT NULL,

	"estadoEnderecoTermoAditivo" VARCHAR(2)  NOT NULL,

	"idTermoEstagio" INTEGER NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."termoaditivo" ADD PRIMARY KEY("idTermoAditivo", "idTermoEstagio");
DROP INDEX IF EXISTS "idTermoEstagio_UNIQUE";

CREATE UNIQUE INDEX "idTermoEstagio_UNIQUE" ON "public"."termoaditivo"("idTermoAditivo");
DROP INDEX IF EXISTS "fk_TermoAditivo_TermoEstagio1_idx";

CREATE INDEX "fk_TermoAditivo_TermoEstagio1_idx" ON "public"."termoaditivo"("idTermoEstagio");




/*

Table structure for table 'public.termoestagio'

*/



DROP TABLE IF EXISTS "public"."termoestagio" CASCADE;

CREATE TABLE "public"."termoestagio" (

	"idTermoEstagio" SERIAL NOT NULL,

	"dataInicioTermoEstagio" DATE,

	"dataFimTermoEstagio" DATE,

	"dataRescisaoTermoEstagio" DATE,

	"situacaoTermoEstagio" VARCHAR(25)  NOT NULL,

	"cargaHorariaTermoEstagio" SMALLINT NOT NULL,

	"valorBolsa" REAL NOT NULL,

	"enderecoTermoEstagio" VARCHAR(255)  NOT NULL,

	"numeroEnderecoTermoEstagio" VARCHAR(10)  NOT NULL,

	"complementoEnderecoTermoEstagio" VARCHAR(150)  NOT NULL,

	"bairroEnderecoTermoEstagio" VARCHAR(150)  NOT NULL,

	"cepEnderecoTermoEstagio" VARCHAR(15)  NOT NULL,

	"cidadeEnderecoTermoEstagio" VARCHAR(150)  NOT NULL,

	"estadoEnderecoTermoEstagio" VARCHAR(6)  NOT NULL,

	"eEstagioObrigatorio" SMALLINT NOT NULL,

	"idProfessorOrientador" INTEGER,

	"idAluno" INTEGER NOT NULL,

	"idConvenio" INTEGER NOT NULL

) WITH OIDS;

-- DROP INDEX IF EXISTS "PRIMARY";

ALTER TABLE "public"."termoestagio" ADD PRIMARY KEY("idTermoEstagio", "idAluno", "idConvenio");
DROP INDEX IF EXISTS "idTermoEstagio_UNIQUE";

CREATE UNIQUE INDEX "idTermoEstagio_UNIQUE" ON "public"."termoestagio"("idTermoEstagio");
DROP INDEX IF EXISTS "fk_TermoEstagio_ProfessorOrientador_idx";

CREATE INDEX "fk_TermoEstagio_ProfessorOrientador_idx" ON "public"."termoestagio"("idProfessorOrientador");
DROP INDEX IF EXISTS "fk_TermoEstagio_Aluno1_idx";

CREATE INDEX "fk_TermoEstagio_Aluno1_idx" ON "public"."termoestagio"("idAluno");
DROP INDEX IF EXISTS "fk_TermoEstagio_Convenio1_idx";

CREATE INDEX "fk_TermoEstagio_Convenio1_idx" ON "public"."termoestagio"("idConvenio");

