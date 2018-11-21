<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>

        <style type="text/css">

            div.container {
                margin-bottom: 2em;
            }
            form {
                margin-top: 50px;
            }
            fieldset.form-group {
                border:1px solid #999999;
            }
            fieldset legend.col-form-legend {
                font-weight: bold;
            }
            div.form-row {
                padding: 0px 15px;
            }

        </style>

        <title><fmt:message key = "br.cefetrj.sisgee.resources.form.AlteracaoTermoAditivo"/></title>
    </head>
    <body>
        <%@include file="import_navbar.jspf"%>	


        <div class="container">
            <c:if test="${ not empty msg and empty msg3 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <c:if test="${ not empty msg2 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg2 }
                </div>
            </c:if>
            <c:if test="${ not empty msg3 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg3 }
                </div>
            </c:if>

            <p class="tituloForm">

            <h5><fmt:message key = "br.cefetrj.sisgee.resources.form.AlteracaoTermoAditivo"/></h5>		
        </p>		
        <form action="FormAlterarTermoAditivo" method="post">
            <fieldset class="form-group dadosAluno" disabled>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosAluno"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="matricula"><fmt:message key = "br.cefetrj.sisgee.resources.form.matricula"/></label>
                        <div class="input-group">
                            <input type="hidden" id="idAluno" name="idAluno" value="${param.alId}">
                            <input type="text" maxlength="100" class="form-control"  id="matricula" name="matricula" value="${alMatricula}">
                        </div>
                    </div>

                    <div class="form-group col-md">
                        <label for="nome"><fmt:message key = "br.cefetrj.sisgee.resources.form.nome"/></label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${ alNome }" readonly>
                    </div>

                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeCurso"><fmt:message key = "br.cefetrj.sisgee.resources.form.curso"/></label>
                        <input type="text" class="form-control" id="nomeCurso"  name="nomeCurso" value="${ alCurso }" readonly>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="nomeCampus"><fmt:message key = "br.cefetrj.sisgee.resources.form.unidade"/></label>
                        <input type="text" class="form-control" id="nomeCampus"  name="nomeCampus" value="${ alCampus }" readonly>
                    </div>
                </div>
            </fieldset>

            <!-- AQUI VEM O CONVÊNIO-->
            <fieldset class="form-group" disabled>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosEmpresaConveniada"/></legend>
                <div class="form-group col-md-15">
                    <!-- AQUI VEM O NOME E NUMERO DO CONVÊNIO-->
                    <div class="form-row">  
                        <div class="form-group col-md-5">
                            <label for="numeroConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numeroConvenio"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="numeroConvenio" name="numeroConvenio" maxlength="10" value="${ cvNumero }" >
                            </div> 
                        </div>        
                        <div class="form-group col-md-7">
                            <label for="nomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeConvenio"/></label>
                            <div class="input-group">

                                <input type="text" class="form-control" id="nomeConvenio" name="nomeConvenio" maxlength="100"  value="${ cvNome }" >                            
                            </div>     
                        </div>
                    </div>   
                    <!-- AQUI TERMINA O NOME E NUMERO DO CONVÊNIO-->
                    <div class="form-group">
                        <div class="input-group">

                            <div class="custom-controls-stacked d-block my-3">
                                <label><fmt:message key = "br.cefetrj.sisgee.resources.form.tipoPJ_PF"/></label>                                
                                <label class="custom-control custom-radio">
                                    <input id="pj" class="custom-control-input" type="radio" name="tipoConvenio" value="pj" ${ tConvenio == 'pj' ? 'checked' : '' } disabled> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.pj"/></span>
                                </label>						

                                <label class="custom-control custom-radio">
                                    <input id="pf" class="custom-control-input" type="radio" name="tipoConvenio" value="pf" ${ tConvenio == 'pf' ? 'checked' : '' } disabled> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.pf"/></span>
                                </label>                
                            </div>						              
                            <!-- AQUI SELECIONA AGENTE DE INTEGRACAO-->
                            <div class="custom-controls-stacked d-block my-3">
                                <label for="isAgenteIntegracao"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_agente_integracao"/></label>
                                <label class="custom-control custom-radio">

                                    <input id="agenteSim" class="custom-control-input" type="radio" name="isAgenteIntegracao" ${ agIntegracao == 'true' ? 'checked' : '' } value="true"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span><!-- AQUI SELECIONA SIM PARA AGENTE DE INTEGRACAO-->
                                </label>						

                                <label class="custom-control custom-radio">
                                    <input id="agenteNao" class="custom-control-input" type="radio" name="isAgenteIntegracao" ${ agIntegracao != 'true' ? 'checked' : '' } value="false"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span><!-- AQUI SELECIONA NAO PARA AGENTE DE INTEGRACAO-->
                                </label>
                            </div>						
                            <!-- AQUI TERMINA SELECIONA AGENTE DE INTEGRACAO-->                            
                            <input type="hidden" class="form-control nomeAgenciada nomeAgenciada"  id="nomeAgenciada1" name="nomeAgenciada1" value="${ nomeAgenciada}">  
                            <label for="nomeAgenciada"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeAgenciada"/>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="nomeAgenciada"  name="nomeAgenciada" value="${ nomeAgenciada }" maxlength="20">
                                </div>
                            </label>
                        </div>
                    </div> 
                </div>                      

                <!-- AQUI COMECA EMPRESA-->
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="hidden" class="form-control cnpjEcpf cnpjEcpf"  id="cnpjEcpf1" name="cnpjEcpf1" value="${cvCpfCnpj}">    
                        <label for="cnpjEcpf"><fmt:message key = "br.cefetrj.sisgee.resources.form.exibirCPFeCNPJ"/></label>
                        <div class="input-group">						  
                            <input type="text" class="form-control cnpjEcpf" id="cnpjEcpf" name="cnpjEcpf" value="${cvCpfCnpj}" readonly>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="nomeEmpresaPessoa"><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial"/></label>
                        <input type="text" class="form-control nomeEmpresaPessoa nomeEmpresaPessoa" id="nomeEmpresaPessoa" name="nomeEmpresaPessoa" value="${ cvNome }" readonly>
                    </div>
                </div>
            </fieldset>
            <!-- Aqui Começa O termo Aditivo -->

            <!-- Aqui começa Vigencia-->
            <c:if test="${ not empty periodoMsg }">
                <div class="alert alert-danger" role="alert">${ periodoMsg }</div>
            </c:if>
            <fieldset class="form-group" ${ showVigencia eq 'sim' ? '' :'disabled'}>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-6">

                        <label for="dataInicioTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataInicio"/></label>
                        <input type="text" class="form-control col-sm-4 ${ not empty dataInicioMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ vidataInicioTermoEstagio }" disabled onchange="sugereData();">
                        <p class="valid-feedback" id="dataIni" name="dataIni"></p>
                        <c:if test="${ not empty dataInicioMsg }">
                            <div class="invalid-feedback">${ dataInicioMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="dataFimTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataTermino"/></label>
                        <input type="text" class="form-control col-sm-4 ${ not empty dataFimMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${showVigencia eq 'sim' ? '' : vidataFimTermoEstagio }" ${ showVigencia eq 'sim' ? '' :'disabled'} >
                        <c:if test="${ not empty dataFimMsg }">
                            <div class="invalid-feedback">${ dataFimMsg }</div>
                        </c:if>
                    </div>
                </div>
            </fieldset>
            <!-- Aqui Termina Vigencia-->

            <!-- Aqui começa Carga Horária-->
            <fieldset class="form-group">
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="cargaHorariaTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.horasDia"/></label>
                        <input type="text" required="required" maxlength="1" pattern="[1-6]+$" class="form-control col-sm-2 ${ not empty cargaHorariaMsg ? 'is-invalid': 'is-valid' }" id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ showCargaHoraria eq 'sim' ? '' :cacargaHorariaTermoEstagio }" ${ showCargaHoraria eq 'sim' ? '' :'disabled'}>
                        <c:if test="${ not empty cargaHorariaMsg }">
                            <div class="invalid-feedback">${ cargaHorariaMsg }</div>
                        </c:if>
                    </div>
                </div>
            </fieldset>
            <!-- Aqui Termina Carga Horária-->

            <!-- Aqui começa Valor Bolsa-->
            <fieldset class="form-group" ${ showValorBolsa eq 'sim' ? '' :'disabled'}>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="valorBolsa">Valor (R$)</label>
                        <input type="text" class="form-control col-sm-6 ${ not empty valorBolsaMsg ? 'is-invalid': 'is-valid' }" id="valorBolsa" name="valorBolsa" value="${vavalorBolsa}">
                        <c:if test="${ not empty valorBolsaMsg }">
                            <div class="invalid-feedback">${ valorBolsaMsg }</div>
                        </c:if>
                    </div>
                </div>
            </fieldset>
            <!-- Aqui termina Valor Bolsa-->

            <!-- Aqui começa local estágio-->
            <fieldset class="form-group" ${ showLocal eq 'sim' ? '' :'disabled'}>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.localEstagio"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-12">

                        <label for="enderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></label>
                        <input type="text"  maxlength="100" class="form-control ${ not empty enderecoMsg ? 'is-invalid': not empty enderecoMsg ? 'is-invalid' : 'is-valid' }" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${enenderecoTermoEstagio }">
                        <!--type="text" required="required" pattern="[1-9,a-z\s]+$" maxlength="100"-->
                        <c:if test="${ not empty enderecoMsg }">
                            <div class="invalid-feedback">${ enderecoMsg }</div>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="numeroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numero"/></label>
                        <input type="text" maxlength="10" class="form-control ${ not empty numeroEnderecoMsg ? 'is-invalid': not empty numeroEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="numeroEnderecoTermoEstagio" name="numeroEnderecoTermoEstagio" value="${ennumeroEnderecoTermoEstagio }">
                        <c:if test="${ not empty numeroEnderecoMsg }">
                            <div class="invalid-feedback">${ numeroEnderecoMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="complementoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></label>
                        <input maxlength="150" type="text" class="form-control ${ not empty complementoEnderecoMsg ? 'is-invalid': not empty complementoEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${encomplementoEnderecoTermoEstagio }">
                        <c:if test="${ not empty complementoEnderecoMsg }">
                            <div class="invalid-feedback">${ complementoEnderecoMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="bairroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></label>
                        <input type="text" maxlength="150" class="form-control ${ not empty bairroEnderecoMsg ? 'is-invalid': not empty bairroEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${enbairroEnderecoTermoEstagio }">
                        <c:if test="${ not empty bairroEnderecoMsg }">
                            <div class="invalid-feedback">${ bairroEnderecoMsg }</div>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">					
                    <div class="form-group col-md-6">
                        <label for="cidadeEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></label>
                        <input type="text" maxlength="150" class="form-control ${ not empty cidadeEnderecoMsg ? 'is-invalid': not empty cidadeEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${encidadeEnderecoTermoEstagio }">
                        <c:if test="${ not empty cidadeEnderecoMsg }">
                            <div class="invalid-feedback">${ cidadeEnderecoMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="estadoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></label>
                        <select name = "estadoEnderecoTermoEstagio" id="estadoEnderecoTermoEstagio" class="form-control ${ not empty estadoEnderecoMsg ? 'is-invalid': not empty estadoEnderecoMsg ? 'is-invalid' : 'is-valid' }">
                            <option value="" selected>${showLocal eq 'sim' ? '' :enuf}</option>
                            <c:forEach items="${ uf }" var="uf">
                                <option value="${ uf }">${ uf }</option>
                            </c:forEach>							
                        </select>
                        <c:if test="${ not empty estadoEnderecoMsg }">
                            <div class="invalid-feedback">${ estadoEnderecoMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cepEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></label>
                        <input type="text" class="form-control ${ not empty cepEnderecoMsg ? 'is-invalid': not empty cepEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${encepEnderecoTermoEstagio }">
                        <c:if test="${ not empty cepEnderecoMsg }">
                            <div class="invalid-feedback">${ cepEnderecoMsg }</div>
                        </c:if>
                    </div>
                </div>
            </fieldset>
            <!-- Aqui começa local estágio-->

            <!-- Aqui começa Supervisor-->
            <fieldset class="form-group" ${ showSupervisor eq 'sim' ? '' :'disabled'}>
                <legend class="col-form-legend col-lg">Supervisor do Estágio</legend>    
                <div class="form-row" > 
                    <div class="form-group col-md-3"  >
                        <label for="eEstagioObrigatorio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></label>
                    </div>

                    <div class="custom-controls-stacked d-block my-3" >
                        <label class="custom-control custom-radio"> 
                            <input id="estagioSim" name="eobrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "sim"  ${ eobrigatorio eq 'true'? 'checked' : '' }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                        </label> 
                        <label class="custom-control custom-radio"> 
                            <input id="estagioNao" name="eobrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "nao"  ${ eobrigatorio eq 'false'? 'checked' : ''  }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                        </label>
                    </div>				
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></label>
                        <input type="text" required="required" pattern="[a-z\s,A-Z\s]+$" maxlength="100"class="form-control" id="nomeSupervisor"  name="nomeSupervisor" value="${nomeSupervisor }" maxlength="80">
                        <c:if test="${ not empty nomeSupervisorMsg }">
                            <div class="invalid-feedback">${ nomeSupervisorMsg }</div>
                        </c:if>                        
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cargoSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></label>
                        <input type="text" required="required" pattern="[a-z\s,A-Z\s]+$" maxlength="50" class="form-control" id="cargoSupervisor"  name="cargoSupervisor" value="${cargoSupervisor }" maxlength="80">
                        <c:if test="${ not empty cargoSupervisorMsg }">
                            <div class="invalid-feedback">${ cargoSupervisorMsg }</div>
                        </c:if>                      
                    </div>
                </div>                        
            </fieldset>
            <!-- Aqui termina Supervisor-->

            <!-- Aqui começa Professor Orientador-->             
            <fieldset class="form-group" ${ showProfessor eq 'sim' ? '' :'disabled'}>
                <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/></legend>
                <div class="form-group col-md-8">
                    <label for="idProfessorOrientador"></label>
                    <select name="nomeProfessorOrientador" id="nomeProfessorOrientador" class="form-control ${ not empty idProfessorMsg ? 'is-invalid': not empty idProfessorMsg ? 'is-invalid' : 'is-valid' }" >
                        <option value="" selected>${ showProfessor eq 'sim' ? '' : pfnomeprofessor }</option>
                        <c:forEach items="${professores}" var="professor">
                            <option value="${professor.idProfessorOrientador}">${professor.nomeProfessorOrientador}</option>
                        </c:forEach>					
                    </select>
                    <c:if test="${not empty idProfessorMsg}">
                        <div class="invalid-feedback">${ idProfessorMsg }</div>
                    </c:if>				
                </div>
            </fieldset>
            <input type="hidden" name="matricula" value="${alMatricula}" />
            <input type="hidden" name="idTermoEstagio" value="${idTermoEstagio}" />
            <input type="hidden" name="idTermoAditivo" value="${idTermoAditivo}" />
            <input type="hidden" name="dataInicioTermoEstagio" value="${vidataInicioTermoEstagio }" />
            <input type="hidden" name="showVigencia" value="${ showVigencia }" />
            <input type="hidden" name="showCargaHoraria" value="${ showCargaHoraria }" />
            <input type="hidden" name="showProfessor" value="${ showProfessor }" />
            <input type="hidden" name="showValorBolsa" value="${ showValorBolsa }" />
            <input type="hidden" name="showLocal" value="${ showLocal }" />
            <input type="hidden" name="showSupervisor" value="${ showSupervisor }" />
            <input type="hidden" name="idProfessorOrientador" id="idProfessorOrientador"/>
            <button type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.resources.form.salvar"/></button>
            <c:choose>
                <c:when test="${ not empty termoEstagio }">
                    <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'form_termo_aditivo.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                </c:otherwise>
            </c:choose>	
        </form>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <%@include file="import_footer.jspf"%>
    <%@include file="import_finalbodyscripts.jspf"%>
    <%@include file="import_scripts.jspf"%>
    <script>
        $(document).ready(function () {
            var tamanho = $("#cnpjEcpf1").val().length;

            $('#cargaHorariaTermoEstagio').mask('9');
            //$('#valorBolsa').mask('000.000,00', {reverse: true});
            $('#dataInicioTermoEstagio').mask('99/99/9999');
            $('#dataFimTermoEstagio').mask('99/99/9999');
            $("#cnpjEcpf1").mask("99.999.999/9999-99");
            $('#cepEnderecoTermoEstagio').mask('99.999-999');
            $('#dataIni').mask('99/99/9999');
            $('#nomeProfessorOrientador').editableSelect().on('select.editable-select', 
            function (e, li) {
                $('#idProfessorOrientador').val(li.val());
                
            });

        });


        function alerta() {
            alert("aqui");
        }

        function sugereData() {
            //var data = new Date(document.getElementById('dataInicioTermoEstagio').value);
            var tipoDeAluno = document.getElementById('tipoAluno').value;

            var data = document.getElementById('dataInicioTermoEstagio').value;
            var dia = data.substring(0, 2);
            var mes = data.substring(3, 5);
            var ano = data.substring(6, 10);

            var dataNova = new Date(mes + "-" + dia + "-" + ano);
            var tipoDeAluno = "";
            if (tipoDeAluno != null) {
                if (tipoDeAluno == "tecnico") {
                    dataNova.setMonth(dataNova.getMonth() + 6);
                    tipoDeAluno = "Curso Técnico";
                } else {
                    dataNova.setMonth(dataNova.getMonth() + 12);
                    tipoDeAluno = "Graduação";
                }
                document.getElementById("dataIni").innerHTML = "Esse Estágio terminaria em " + dataNova.toLocaleDateString() + " para este aluno de " + tipoDeAluno;
            }

        }

    </script>
</body>
</html>