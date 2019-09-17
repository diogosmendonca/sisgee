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

        <title>
            <c:choose>
                <c:when test="${ not empty termoEstagio }">
                    <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoEstagio"/>
                </c:otherwise>
            </c:choose>
        </title>
    </head>
    <body>
        <%@include file="import_navbar.jspf"%>	


        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <c:if test="${ not empty msg2 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg2 }
                </div>
            </c:if>

            <p class="tituloForm">

            <h5>		
                <c:choose>
                    <c:when test="${ not empty termoEstagio }">
                        <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoEstagio"/>
                    </c:otherwise>
                </c:choose>
            </h5>		
        </p>		

        <c:choose>
            <c:when test="${ not empty termoEstagio }">
                <form action="FormTermoAditivoServlet" method="post">
                </c:when>
                <c:otherwise>
                    <form action="FormTermoEstagioServlet" method="post">
                    </c:otherwise>
                </c:choose>


                <fieldset class="form-group dadosAluno" ${ not empty termoEstagio ? 'disabled' :'' }>
                    <%@include file="import_busca_aluno.jspf"%>
                </fieldset>

                <!-- AQUI VEM O CONVÊNIO-->
                <fieldset class="form-group" ${ not empty termoEstagio ? 'disabled' : '' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosEmpresaConveniada"/></legend>
                    <div class="form-group col-md-12">
                        <!-- AQUI VEM O NOME E NUMERO DO CONVÊNIO-->
                        <div class="input-group">  
                            <div class="form-group col-md-5">
                                <label for="numeroConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numeroConvenio"/></label>
                                <div class="input-group">
                                    <input type="hidden" class="form-control numeroConvenio numeroConvenio"  id="numeroConvenio1" name="numeroConvenio1" value="${ param.numeroConvenio}">
                                    <input type="hidden" class="form-control idConvenio idConvenio"  id="idConvenio" name="idConvenio" value="${ param.idConvenio}"> 
                                    <input type="text" class="form-control ${ not empty numeroConvenioMsg ? 'is-invalid': 'is-valid' } numeroConvenio" id="numeroConvenio" name="numeroConvenio"  maxlength="6" value="${ param.numeroConvenio }" placeholder="<fmt:message key = "br.cefetrj.sisgee.resources.form.placeholder_numeroConvenio"/>">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="btnBuscarConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>    
                                    <c:if test="${ not empty numeroConvenioMsg }">
                                        <div class="invalid-feedback">${ numeroConvenioMsg }</div>
                                    </c:if> 
                                </div> 
                            </div>        
                            <div class="form-group col-md-7">
                                <label for="nomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeConvenio"/></label>
                                <div class="input-group">
                                    <input type="hidden" class="form-control nomeConvenio nomeConvenio"  id="nomeConvenio1" name="nomeConvenio1" value="${ param.nomeConvenio}">                      
                                    <input type="text" class="form-control ${ not empty nomeConvenioMsg ? 'is-invalid': 'is-valid' } nomeConvenio" placeholder="<fmt:message key="br.cefetrj.sisgee.resources.form.placeholder_nomeConvenio"/>" id="nomeConvenio" name="nomeConvenio" maxlength="100"  value="${ param.nomeConvenio }" >                            
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="btnBuscarNomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>   
                                    <c:if test="${ not empty nomeConvenioMsg }">
                                        <div class="invalid-feedback">${ nomeConvenioMsg }</div>
                                    </c:if>                             
                                </div>     
                            </div>
                        </div>   
                        <!-- AQUI TERMINA O NOME E NUMERO DO CONVÊNIO-->
                        <div class="form-group">
                            <div class="input-group">
                                <label><fmt:message key = "br.cefetrj.sisgee.resources.form.tipoPJ_PF"/></label>                                
                                <label class="custom-control">
                                    <input id="tipoConvenio" class="form-control tipoConvenio" type="text" name="tipoConvenio" value="${param.tipoConvenio}" readonly> 
                                </label>						              
                                <!-- AQUI SELECIONA AGENTE DE INTEGRACAO-->
                                <label for="isAgenteIntegracao"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_agente_integracao"/></label>
                                <label class="custom-control">
                                    <input id="isAgenteIntegracao" class="form-control isAgenteIntegracao" type="text" name="isAgenteIntegracao" value="${param.isAgenteIntegracao}" readonly> 
                                </label>
                                    <!-- AQUI TERMINA SELECIONA AGENTE DE INTEGRACAO-->                            
                                    <input type="hidden" class="form-control nomeAgenciada nomeAgenciada"  id="nomeAgenciada1" name="nomeAgenciada1" value="${ param.nomeAgenciada}">  
                                    <label for="nomeAgenciada"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeAgenciada"/></label>
                                    <label class="custom-control">
                                        <input type="text" class="form-control ${ not empty agenciadaMsg ? 'is-invalid': 'is-valid' } nomeAgenciada" id="nomeAgenciada"  name="nomeAgenciada" value="${ param.nomeAgenciada }" maxlength="250">
                                    </label>
                                <c:if test="${ not empty agenciadaMsg }">
                                    <div class="invalid-feedback">${ agenciadaMsg }</div>
                                </c:if>  
                            </div>
                        </div> 
                    </div>                      

                    <!-- AQUI COMECA EMPRESA-->
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <input type="hidden" class="form-control cnpjEcpf cnpjEcpf"  id="cnpjEcpf1" name="cnpjEcpf1" value="${ param.cnpjEcpf}">    
                            <label for="cnpjEcpf"><fmt:message key = "br.cefetrj.sisgee.resources.form.exibirCPFeCNPJ"/></label>
                            <div class="input-group">						  
                                <input type="text" class="form-control cnpjEcpf" id="cnpjEcpf" name="cnpjEcpf" value="${ param.cnpjEcpf }" readonly>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="nomeEmpresaPessoa"><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial"/></label>
                            <input type="text" class="form-control nomeEmpresaPessoa nomeEmpresaPessoa" id="nomeEmpresaPessoa" name="nomeEmpresaPessoa" value="${ param.nomeEmpresaPessoa }" readonly>
                        </div>
                    </div>
                </fieldset>
                <!-- AQUI TERMINA EMPRESA -->   

                <c:if test="${ not empty periodoMsg }">
                    <div class="alert alert-danger" role="alert">${ periodoMsg }</div>
                </c:if>
                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <label for="dataInicioTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataInicio"/></label>
                            <input type="text" class="form-control col-sm-4 ${ not empty dataInicioMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ param.dataInicioTermoEstagio }" ${ not empty termoEstagio ? 'disabled' : '' } onChange="sugereData();">
                            <p class="valid-feedback" id="dataIni" name="dataIni"></p>
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dataFimTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataTermino"/></label>
                            <input type="text" class="form-control col-sm-4 ${ not empty dataFimMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${ param.dataFimTermoEstagio }" ${ empty termoEstagio ? '' : empty updVigencia ? 'disabled' : '' } >
                            <c:if test="${ not empty dataFimMsg }">
                                <div class="invalid-feedback">${ dataFimMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>

                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="cargaHorariaTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.horasDia"/></label>
                            <input type="text" maxlength="1" class="form-control col-sm-2 ${ not empty cargaHorariaMsg ? 'is-invalid': 'is-valid' }" id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ param.cargaHorariaTermoEstagio }" ${ empty termoEstagio ? '' : empty updCargaHoraria ? 'disabled' : '' }>
                            <c:if test="${ not empty cargaHorariaMsg }">
                                <div class="invalid-feedback">${ cargaHorariaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>

                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="valorBolsa">Valor (R$)</label>
                            <input type="text" class="form-control col-sm-6 ${ not empty valorBolsaMsg ? 'is-invalid': 'is-valid' }" id="valorBolsa" name="valorBolsa" value="${ param.valorBolsa }" ${ empty termoEstagio ? '' : empty updValorBolsa ? 'disabled' : '' }>
                            <c:if test="${ not empty valorBolsaMsg }">
                                <div class="invalid-feedback">${ valorBolsaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>

                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' } ${ empty termoEstagio ? '' : empty updEndereco ? 'disabled' : '' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.localEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-12">

                            <label for="enderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></label>
                            <input type="text" maxlength="255" class="form-control ${ not empty enderecoMsg ? 'is-invalid': not empty enderecoMsg ? 'is-invalid' : 'is-valid' }" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${ param.enderecoTermoEstagio }">
                            <c:if test="${ not empty enderecoMsg }">
                                <div class="invalid-feedback">${ enderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="numeroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numero"/></label>
                            <input type="text" maxlength="10" class="form-control ${ not empty numeroEnderecoMsg ? 'is-invalid': not empty numeroEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="numeroEnderecoTermoEstagio" name="numeroEnderecoTermoEstagio" value="${ param.numeroEnderecoTermoEstagio }">
                            <c:if test="${ not empty numeroEnderecoMsg }">
                                <div class="invalid-feedback">${ numeroEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="complementoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></label>
                            <input maxlength="150" type="text" class="form-control ${ not empty complementoEnderecoMsg ? 'is-invalid': not empty complementoEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${ param.complementoEnderecoTermoEstagio }">
                            <c:if test="${ not empty complementoEnderecoMsg }">
                                <div class="invalid-feedback">${ complementoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="bairroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></label>
                            <input type="text" maxlength="150" class="form-control ${ not empty bairroEnderecoMsg ? 'is-invalid': not empty bairroEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${ param.bairroEnderecoTermoEstagio }">
                            <c:if test="${ not empty bairroEnderecoMsg }">
                                <div class="invalid-feedback">${ bairroEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">					
                        <div class="form-group col-md-6">
                            <label for="cidadeEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></label>
                            <input type="text" maxlength="150" class="form-control ${ not empty cidadeEnderecoMsg ? 'is-invalid': not empty cidadeEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${ param.cidadeEnderecoTermoEstagio }">
                            <c:if test="${ not empty cidadeEnderecoMsg }">
                                <div class="invalid-feedback">${ cidadeEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="estadoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></label>
                            <select name = "estadoEnderecoTermoEstagio" id="estadoEnderecoTermoEstagio" class="form-control ${ not empty estadoEnderecoMsg ? 'is-invalid': not empty estadoEnderecoMsg ? 'is-invalid' : 'is-valid' }">
                                <option value="" >---</option>
                                <c:forEach items="${ uf }" var="uf">
                                    <c:if test="${estadoEnderecoTermoEstagio eq uf}">
                                        <option value="${ uf }" selected>${ uf }</option>
                                    </c:if>
                                    <c:if test="${estadoEnderecoTermoEstagio != uf}">
                                          <option value="${ uf }">${ uf }</option>
                                    </c:if>      
                                    
                                </c:forEach>							
                            </select>
                            <c:if test="${ not empty estadoEnderecoMsg }">
                                <div class="invalid-feedback">${ estadoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cepEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></label>
                            <input type="text" class="form-control ${ not empty cepEnderecoMsg ? 'is-invalid': not empty cepEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${ param.cepEnderecoTermoEstagio }">
                            <c:if test="${ not empty cepEnderecoMsg }">
                                <div class="invalid-feedback">${ cepEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>


                <div class="form-row" >
                    <div class="form-group col-md-3" ${ isVisualizacao eq true ? 'disabled' :'' } >
                        <label for="eEstagioObrigatorio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></label>
                    </div>

                    <div class="custom-controls-stacked d-block my-3" ${ isVisualizacao eq true ? 'disabled' :'' }>
                        <label class="custom-control custom-radio"> 
                            <input id="estagioSim" name="eEstagioObrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "sim" ${ not empty termoEstagio ? 'disabled' :'' } ${ not empty eEstagioObrigatorioMsg ? '' : param.eEstagioObrigatorio == 'sim' ? 'checked' : '' }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                        </label> 
                        <label class="custom-control custom-radio"> 
                            <input id="estagioNao" name="eEstagioObrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "nao" ${ not empty termoEstagio ? 'disabled' :'' } ${ not empty eEstagioObrigatorioMsg ? '' : param.eEstagioObrigatorio == 'nao' ? 'checked' : '' }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                        </label>
                    </div>				
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></label>
                        <input type="text" class="form-control" id="nomeSupervisor"  name="nomeSupervisor" value="${ param.nomeSupervisor }" maxlength="80">
                        <c:if test="${ not empty nomeSupervisorMsg }">
                            <div class="invalid-feedback">${ nomeSupervisorMsg }</div>
                        </c:if>                        
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cargoSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></label>
                        <input type="text" class="form-control" id="cargoSupervisor"  name="cargoSupervisor" value="${ param.cargoSupervisor }" maxlength="80">
                        <c:if test="${ not empty cargoSupervisorMsg }">
                            <div class="invalid-feedback">${ cargoSupervisorMsg }</div>
                        </c:if>                      
                    </div>
                </div>                        

                <fieldset ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <div class="form-group col-md-8">
                        <label for="idProfessorOrientador"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/></label>
                        <select name="idProfessorOrientador" id="idProfessorOrientador" class="form-control ${ not empty idProfessorMsg ? 'is-invalid': not empty idProfessorMsg ? 'is-invalid' : 'is-valid' }" >
                            <option value="">---</option>
                            <c:forEach items="${ professores }" var="professor">
                                <c:if test="${professor.idProfessorOrientador eq idProfessor}">
                                    <option value="${ professor.idProfessorOrientador }" selected> ${ professor.nomeProfessorOrientador }</option>
                                </c:if>
                                <c:if test="${idProfessor != professor.idProfessorOrientador}">
                                    <option value="${ professor.idProfessorOrientador }"> ${ professor.nomeProfessorOrientador }</option>
                                </c:if>       
                            </c:forEach>				
                        </select>
                        <c:if test="${ not empty idProfessorMsg }">
                            <div class="invalid-feedback">${ idProfessorMsg }</div>
                        </c:if>				
                    </div>
                </fieldset>

                <c:if test="${ not empty termoEstagio }">
                    <input type="hidden" name="idTermoEstagio" value="${ termoEstagio.idTermoEstagio }" />
                    <input type="hidden" name="updVigencia" value="${ updVigencia }" />
                    <input type="hidden" name="updCargaHoraria" value="${ updCargaHoraria }" />
                    <input type="hidden" name="updProfessor" value="${ updProfessor }" />
                    <input type="hidden" name="updValorBolsa" value="${ updValorBolsa }" />
                    <input type="hidden" name="updEndereco" value="${ updEndereco }" />
                </c:if>

                <button type="submit" class="btn btn-primary" ${ isVisualizacao eq true ? 'disabled' :'' }><fmt:message key = "br.cefetrj.sisgee.resources.form.salvar"/></button>
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
            $('#cargaHorariaTermoEstagio').mask('9');
            $('#valorBolsa').mask('000.000,00', {reverse: true});
            $('#dataInicioTermoEstagio').mask('99/99/9999');
            $('#dataFimTermoEstagio').mask('99/99/9999');
            $("#cnpjEcpf1").mask("99.999.999/9999-99");
            $('#cepEnderecoTermoEstagio').mask('99.999-999');
            $('#dataIni').mask('99/99/9999');
                    

        });


        function sugereData() {
            var tipoDeAluno = document.getElementById('tipoAluno').value;

            var data = document.getElementById('dataInicioTermoEstagio').value;
            var dia = data.substring(0, 2);
            var mes = data.substring(3, 5);
            var ano = data.substring(6, 10);

            var dataNova = new Date(mes + "-" + dia + "-" + ano);

            if (tipoDeAluno != null || tipoDeAluno != "") {
                if (tipoDeAluno.toString().toUpperCase() == 'TECNICO') {
                    dataNova.setMonth(dataNova.getMonth() + 6);
                    tipoDeAluno = "Curso Técnico";
                    document.getElementById("dataIni").innerHTML = "Esse Estágio terminaria em " + dataNova.toLocaleDateString() + " para este aluno de " + tipoDeAluno;
                    document.getElementById("dataFimTermoEstagio").value = dataNova.toLocaleDateString();
                } else if (tipoDeAluno.toString().toUpperCase() == 'GRADUACAO') {
                    dataNova.setMonth(dataNova.getMonth() + 12);
                    tipoDeAluno = "Graduação";
                    document.getElementById("dataIni").innerHTML = "Esse Estágio terminaria em " + dataNova.toLocaleDateString() + " para este aluno de " + tipoDeAluno;
                    document.getElementById("dataFimTermoEstagio").value = dataNova.toLocaleDateString();
                }

            }

        }
        

    </script>
</body>
</html>
