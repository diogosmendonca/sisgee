--	NOME DOS PROFESSORES
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (1, 'SILVIA JOSEFINA LERARIO RAMOS');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (2, 'FRANCISCA MARIA CARDAMONE LERARI');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (3, 'SANDRA LERARIO RAMOS');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (4, 'GUILHERME LERARIO RAMOS');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (5, 'GILVAN SILVA DE OLIVEIRA');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (6, 'JULIO CESAR TOLEDO PIZA JUNIOR');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (7, 'EDUARDO NATALE PACIULLI');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (8, 'FRANCISCO MESQUITA NETO');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (9, 'PIEDADE ELVIRA CATARINACHO');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (10, 'MARCELO MAIA DE AZEVEDO CORREA');  
insert into ProfessorOrientador (idProfessorOrientador, nomeProfessorOrientador) values (11, 'ALOYSIO DE ANDRADE FARIA');

-- AGENTES DE INTEGRAÇÃO                                                                                                                              
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (1,  36543427113604, 'AUGUSTO JOAO GIL');           
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (2,  75740307137567, 'TULLIO PRADA');               
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (3,  90562296778339, 'ELIO CEPOLLINA');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (4,  95165470511892, 'MARIA CARLOTTA');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (5,  76463266014114, 'HELIO PENTAGNA GUIMARAES');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (6,  46733051502603, 'MUSSI ZAUITH');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (7,  53574401563098, 'HENRIQUE KRACOCHANSKY');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (8,  40173501611303, 'BENY MARIA VERDI HADDAD');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (9,  81449348838235, 'ROSY LAVINIA ROQUETTE VERDI');         
insert into AgenteIntegracao (idAgenteIntegracao, cnpjAgenteIntegracao, nomeAgenteIntegracao) values (10, 50978351525160, 'JOAO BATISTELA BIAZON');     


--	EMPRESAS COM OU SEM AGENTE DE INTEGRAÇÃO
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (1,  43317270368123, 'SUZANO PAPEL E CELULOSE SA', 1);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (2,  88068866509228, 'GRADIENTE ELETRONICA S/A', 2);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (3,  39172430941687, 'FORD MOTOR COMPANY BRASIL LTDA', 3);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (4,  55349130116787, 'VALE FERTILIZANTES SA', 4);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (5,  96308231739124, 'BOLSA DE VALORES DE SAO PAULO', 5);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (6,  24440228390279, 'CONSTRUTORA OAS LTDA', 6);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (7,  03698972868493, 'ENGERAL SA', 7);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (8,  94796594586781, 'DAMOVO DO BRASIL SA', 8);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (9,  50937036294078, 'GLOBEX ADM DE CONSORCIOS LTDA', 9);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa, idAgenteIntegracao) values (10, 73991064341721, 'RODOBENS A E C DE SEGUROS S C LT', 10);
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (11, 68289895222506, 'CONDOMINIO PALAZZO RAVENNA');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (12, 70230198082962, 'MUNIR ABBUD EMPREENDIMENTOS IMOBILIARIO');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (13, 34354671415441, 'JAMARI ADMINISTRADORA DE CONSORCIOS S/C');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (14, 84235609877558, 'GE WATER & PROCESS TECHNOLOGIES DO BRAS');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (15, 61045321239122, 'AUTO VIACAO URUBUPUNGA LTDA');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (16, 91647215260065, 'ENSIN EMPRESA NACIONAL S. E. LTDA');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (17, 79117110444874, 'IBF INDUSTRIA BRASILEIRA DE FILMES SA');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (18, 44334044184046, 'FINASA ZURICH P RV30 FIF FIN INV MERCSP');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (19, 37851243074558, 'KONA FIF');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (20, 30140532623433, 'COINVALORES CCVM LTDA');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (21, 32599796770751, 'BRADESCO FUNDO DE INVEST FINAN ACORIANO');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (22, 21656231704119, 'FINASA RF EXCLUSIVO FC FIF MERCSP MERCS');
insert into Empresa (idEmpresa, cnpjEmpresa, nomeEmpresa) values (23, 84302913962910, 'DESENBAHIA FUNDESE GIRO');

-- CONVENIO
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (1,  6721005388, '2013-02-20', '2014-02-19', 1);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (2,  6370547536, '2013-02-20', '2014-02-19', 21);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (3,  8543730878, '2013-02-20', '2014-02-19', 22);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (4,  5784156115, '2013-02-20', '2014-02-19', 3);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (5,  7939585726, '2013-02-20', '2014-02-19', 7);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (6,  3749484989, '2013-02-20', '2014-02-19', 4);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (7,  0815186283, '2013-02-20', '2014-02-19', 14);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (8,  0819257114, '2013-02-20', '2014-02-19', 8);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (9,  7018131827, '2013-02-20', '2014-02-19', 14);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (10, 9131773954, '2013-02-20', '2014-02-19', 12);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (11, 6962246274, '2013-02-20', '2014-02-19', 15);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (12, 8637535725, '2013-02-20', '2014-02-19', 3);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (13, 6204216593, '2013-02-20', '2014-02-19', 15);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (14, 5365797456, '2013-02-20', '2014-02-19', 15);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (15, 7714197465, '2013-02-20', '2014-02-19', 4);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (16, 9191226129, '2013-02-20', '2014-02-19', 9);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (17, 2692200562, '2013-02-20', '2014-02-19', 7);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (18, 1205456057, '2013-02-20', '2014-02-19', 12);
insert into Convenio (idConvenio, numeroConvenio, dataInicioConvenio, dataFimConvenio, idEmpresa) values (19, 4998663708, '2013-02-20', '2014-02-19', 10);

-- CAMPUS
insert into Campus (idCampus, nomeCampus) values (1, 'Maracanã');
insert into Campus (idCampus, nomeCampus) values (2, 'Angra dos Reis');
insert into Campus (idCampus, nomeCampus) values (3, 'Itaguaí');
insert into Campus (idCampus, nomeCampus) values (4, 'Maria da Graça');
insert into Campus (idCampus, nomeCampus) values (5, 'Nova Friburgo');
insert into Campus (idCampus, nomeCampus) values (6, 'Nova Iguaçu');
insert into Campus (idCampus, nomeCampus) values (7, 'Petrópolis');
insert into Campus (idCampus, nomeCampus) values (8, 'Valença');
                                                                                                                                                      
-- CURSO
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (1,  'Bacharelado em Engenharia Civil', 'Bacharelado em Engenharia Civil', 1);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (2,  'Bacharelado em Engenharia Ambiental', 'Bacharelado em Engenharia Ambiental', 2);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (3,  'Bacharelado em Engenharia da Computação', 'Bacharelado em Engenharia da Computação', 3);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (4,  'Bacharelado em Administração', 'Bacharelado em Administração', 4);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (5,  'Bacharelado em Engenharia de Controle e Automação', 'Bacharelado em Engenharia de Controle e Automação Industrial', 5);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (6,  'Bacharelado em Engenharia Produção', 'Bacharelado em Engenharia Produção', 6);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (7,  'Bacharelado em Engenharia de Telecomunições', 'Bacharelado em Engenharia de Telecomunições', 7);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (8,  'Bacharelado em Engenharia Elétrica', 'Bacharelado em Engenharia Elétrica', 8);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (9,  'Bacharelado em Engenharia Mecânica', 'Bacharelado em Engenharia Mecânica', 1);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (10, 'Bacharelado Sistemas de informação', 'Bacharelado Sistemas de informação', 3);
insert into Curso (idCurso, codigoCurso, nomeCurso, idCampus) values (11, 'Licenciatura em Física', 'Licenciatura em Física', 5);

-- PESSOA

insert into Pessoa (idPessoa, cpf, nome, dataNascimento, tipoEndereco, endereco, numeroEndereco, complementoEndereco, bairroEndereco, cepEndereco, distritoEndereco, cidadeEndereco, estadoEndereco, paisEndereco, email, ddiResidencial, dddResidencial, telefoneResidencial, ddiComercial, dddComercial, telefoneComercial, ddiCelular, dddCelular, telefoneCelular) values (1, 12345678982, 'HUMBERTO SANTANA ' ,'1992-09-09', 'casa',  'Rua bla bla bla', '105', '', 'Pavuna', '22673123','', 'Rio de Janeiro', 'RJ', 'Brasil', 'exemplo@asdasdas.com', 55, 21, '12313212', 55, 21, '22321234', 55, 21, '92341234');
insert into Pessoa (idPessoa, cpf, nome, dataNascimento, tipoEndereco, endereco, numeroEndereco, complementoEndereco, bairroEndereco, cepEndereco, distritoEndereco, cidadeEndereco, estadoEndereco, paisEndereco, email, ddiResidencial, dddResidencial, telefoneResidencial, ddiComercial, dddComercial, telefoneComercial, ddiCelular, dddCelular, telefoneCelular) values (2, 7775678922, 'OTTO WALTER FRIEDRICH KNEUBUHLER'  ,'1992-09-09', 'casa',  'Rua bla bla bla', '105', '', 'Pavuna', '22673123','', 'Rio de Janeiro', 'RJ', 'Brasil', 'exemplo@asdasdas.com', 55, 21, '12313212', 55, 21, '22321234', 55, 21, '92341234');
insert into Pessoa (idPessoa, cpf, nome, dataNascimento, tipoEndereco, endereco, numeroEndereco, complementoEndereco, bairroEndereco, cepEndereco, distritoEndereco, cidadeEndereco, estadoEndereco, paisEndereco, email, ddiResidencial, dddResidencial, telefoneResidencial, ddiComercial, dddComercial, telefoneComercial, ddiCelular, dddCelular, telefoneCelular) values (3, 52145678952, 'ANTONIO CARLOS BARROS FORMIGA'   ,'1992-09-09', 'casa',  'Rua bla bla bla', '105', '', 'Pavuna', '22673123','', 'Rio de Janeiro', 'RJ', 'Brasil', 'exemplo@asdasdas.com', 55, 21, '12313212', 55, 21, '22321234', 55, 21, '92341234');
insert into Pessoa (idPessoa, cpf, nome, dataNascimento, tipoEndereco, endereco, numeroEndereco, complementoEndereco, bairroEndereco, cepEndereco, distritoEndereco, cidadeEndereco, estadoEndereco, paisEndereco, email, ddiResidencial, dddResidencial, telefoneResidencial, ddiComercial, dddComercial, telefoneComercial, ddiCelular, dddCelular, telefoneCelular) values (4, 82345678952, 'MARIA LOURDES'   ,'1992-09-09', 'casa',  'Rua bla bla bla', '105', '', 'Pavuna', '22673123','', 'Rio de Janeiro', 'RJ', 'Brasil', 'exemplo@asdasdas.com', 55, 21, '12313212', 55, 21, '22321234', 55, 21, '92341234');
insert into Pessoa (idPessoa, cpf, nome, dataNascimento, tipoEndereco, endereco, numeroEndereco, complementoEndereco, bairroEndereco, cepEndereco, distritoEndereco, cidadeEndereco, estadoEndereco, paisEndereco, email, ddiResidencial, dddResidencial, telefoneResidencial, ddiComercial, dddComercial, telefoneComercial, ddiCelular, dddCelular, telefoneCelular) values (5, 99945678952, 'EDGAR SILVEIRA'   ,'1992-09-09', 'casa',  'Rua bla bla bla', '105', '', 'Pavuna', '22673123','', 'Rio de Janeiro', 'RJ', 'Brasil', 'exemplo@asdasdas.com', 55, 21, '12313212', 55, 21, '22321234', 55, 21, '92341234');

--	ALUNOS
insert into Aluno (idAluno, matricula, situacao, idCurso, idPessoa) values (1, '1411021ENG', 'ativo', 1, 1);
insert into Aluno (idAluno, matricula, situacao, idCurso, idPessoa) values (2, '1412035ENG', 'ativo', 2, 2);
insert into Aluno (idAluno, matricula, situacao, idCurso, idPessoa) values (3, '1431627ENG', 'ativo', 3, 3);
insert into Aluno (idAluno, matricula, situacao, idCurso, idPessoa) values (4, '1491321ENG', 'ativo', 4, 4);
insert into Aluno (idAluno, matricula, situacao, idCurso, idPessoa) values (5, '1123456ENG', 'ativo', 5, 5);

--	TERMO ESTAGIO
insert into TermoEstagio (idTermoEstagio, dataInicioTermoEstagio, dataFimTermoEstagio, dataRescisaoTermoEstagio, situacaoTermoEstagio, cargaHorariaTermoEstagio, valorBolsa, enderecoTermoEstagio, numeroEnderecoTermoEstagio, complementoEnderecoTermoEstagio, bairroEnderecoTermoEstagio, cepEnderecoTermoEstagio, cidadeEnderecoTermoEstagio, estadoEnderecoTermoEstagio, eEstagioObrigatorio, idProfessorOrientador, idAluno, idConvenio) values (1, '2016-07-07', '2016-12-08', '2016-12-09', 'Rescindido', 6, 500.00, 'Endereco Termo Estagio', '206', 'complementoEnderecoTermoEstagio', 'bairroEnderecoTermoEstagio', '21321312', 'cidadeEnderecoTermoEstagio', 'RJ', 1, 1, 1, 1);
insert into TermoEstagio (idTermoEstagio, dataInicioTermoEstagio, dataFimTermoEstagio, dataRescisaoTermoEstagio, situacaoTermoEstagio, cargaHorariaTermoEstagio, valorBolsa, enderecoTermoEstagio, numeroEnderecoTermoEstagio, complementoEnderecoTermoEstagio, bairroEnderecoTermoEstagio, cepEnderecoTermoEstagio, cidadeEnderecoTermoEstagio, estadoEnderecoTermoEstagio, eEstagioObrigatorio, idProfessorOrientador, idAluno, idConvenio) values (2, '2016-07-07', '2016-12-08', '2016-12-09', 'Rescindido', 5, 800.00, 'Endereco Termo Estagio', '206', 'complementoEnderecoTermoEstagio', 'bairroEnderecoTermoEstagio', '21321312', 'cidadeEnderecoTermoEstagio', 'RJ', 2, 2, 2, 2);
insert into TermoEstagio (idTermoEstagio, dataInicioTermoEstagio, dataFimTermoEstagio, dataRescisaoTermoEstagio, situacaoTermoEstagio, cargaHorariaTermoEstagio, valorBolsa, enderecoTermoEstagio, numeroEnderecoTermoEstagio, complementoEnderecoTermoEstagio, bairroEnderecoTermoEstagio, cepEnderecoTermoEstagio, cidadeEnderecoTermoEstagio, estadoEnderecoTermoEstagio, eEstagioObrigatorio, idProfessorOrientador, idAluno, idConvenio) values (3, '2016-07-07', '2016-12-08', '2016-12-09', 'Rescindido', 5, 900.00, 'Endereco Termo Estagio', '206', 'complementoEnderecoTermoEstagio', 'bairroEnderecoTermoEstagio', '21321312', 'cidadeEnderecoTermoEstagio', 'RJ', 3, 3, 3, 3);

--	TERMO ADITIVO
insert into TermoAditivo (idTermoAditivo, dataFimTermoAditivo, cargaHorariaTermoAditivo, valorBolsaTermoAditivo, enderecoTermoAditivo, numeroEnderecoTermoAditivo, complementoEnderecoTermoEstagio, bairroEnderecoTermoAditivo, cepEnderecoTermoAditivo, cidadeEnderecoTermoAditivo, estadoEnderecoTermoAditivo, idTermoEstagio) values (1, '2016-12-30', 6, 900.00, 'Endereco Termo Estagio', '206', 'complementoEnderecoTermoEstagio', 'bairroEnderecoTermoEstagio', '21321312', 'cidadeEnderecoTermoEstagio', 'RJ', 2);
insert into TermoAditivo (idTermoAditivo, dataFimTermoAditivo, cargaHorariaTermoAditivo, valorBolsaTermoAditivo, enderecoTermoAditivo, numeroEnderecoTermoAditivo, complementoEnderecoTermoEstagio, bairroEnderecoTermoAditivo, cepEnderecoTermoAditivo, cidadeEnderecoTermoAditivo, estadoEnderecoTermoAditivo, idTermoEstagio) values (2, '2016-12-30', 6, 900.00, 'Endereco Termo Estagio', '206', 'complementoEnderecoTermoEstagio', 'bairroEnderecoTermoEstagio', '21321312', 'cidadeEnderecoTermoEstagio', 'RJ', 3);
