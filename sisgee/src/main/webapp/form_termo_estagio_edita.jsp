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

        <title><fmt:message key = "br.cefetrj.sisgee.resources.form.editarTermoEstagio"/></title>
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

            <h5><fmt:message key = "br.cefetrj.sisgee.resources.form.editarTermoEstagio"/></h5>		
        </p>		
            <form action="EditarTermoEAditivo" method="post">
                <!-- DADOS NECESSÁRIOS VINDOS DO SERVLET -->
                <input type="hidden" id="precisaVerificarTermoEmAberto" name="precisaVerificarTermoEmAberto" value="1" />
                <input type="hidden" id="idTermoEstagio" name="idTermoEstagio" value="${ idTermoEstagio }" />
                <input type="hidden" id="idEstagio" name="idEstagio" value="${ idEstagio }" />
                <input type="hidden" id="matriculaAluno" name="matriculaAluno" value="${aluno.getMatricula()}" />
                <input type="hidden" id="idAluno" name="idAluno" value="${aluno.getIdAluno()}" />
                <input type="hidden" id="nomeAluno" value="${aluno.getPessoa().getNome()}" />
                <input type="hidden" id="nomeCursoAluno" value="${aluno.getCurso().getNomeCurso()}" />
                <input type="hidden" id="unidadeCursoAluno" value="${aluno.getCurso().getCampus()}" />
                
                
                 <fieldset class="form-group dadosAluno">
                    <%@include file="import_busca_aluno.jspf"%>
                </fieldset>
                
                <!-- AQUI VEM O CONVÊNIO-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosEmpresaConveniada"/></legend>
                    <div class="form-group col-md-12">
                        <!-- AQUI VEM O NOME E NUMERO DO CONVÊNIO-->
                        <div class="input-group">  
                            <div class="form-group col-md-5">
                                <label for="numeroConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numeroConvenio"/></label>
                                <div class="input-group">
                                    <input type="hidden" class="form-control numeroConvenio numeroConvenio"  id="numeroConvenio1" name="numeroConvenio1" value="${ cvNumero2 }">
                                    <input type="hidden" class="form-control idConvenio idConvenio"  id="idConvenio" name="idConvenio" value="${ convenio.getNumero() }"> 
                                    <input type="text" class="form-control ${ not empty numeroConvenioMsg ? 'is-invalid': 'is-valid' } numeroConvenio" id="numeroConvenio" name="numeroConvenio"  maxlength="6" value="${ cvNumero2 }" placeholder="<fmt:message key = "br.cefetrj.sisgee.resources.form.placeholder_numeroConvenio"/>">
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
                                    <input type="hidden" class="form-control nomeConvenio nomeConvenio"  id="nomeConvenio1" name="nomeConvenio1" value="${ cvNome }">                      
                                    <input type="text" class="form-control ${ not empty nomeConvenioMsg ? 'is-invalid': 'is-valid' } nomeConvenio" placeholder="<fmt:message key="br.cefetrj.sisgee.resources.form.placeholder_nomeConvenio"/>" id="nomeConvenio" name="nomeConvenio" maxlength="100"  value="${ cvNome }" readonly >                            
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
                                    <input id="tipoConvenio" class="form-control tipoConvenio" type="text" name="tipoConvenio" value="${tConvenio }" readonly> 
                                </label>						              
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
                                    <input type="hidden" class="form-control nomeAgenciada nomeAgenciada"  id="nomeAgenciada1" name="nomeAgenciada1" value="${ nomeAgenciada }">  
                                    <label for="nomeAgenciada"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeAgenciada"/></label>
                                    <label class="custom-control">
                                        <input type="text" class="form-control ${ not empty agenciadaMsg ? 'is-invalid': 'is-valid' } nomeAgenciada" id="nomeAgenciada"  name="nomeAgenciada" value="${ nomeAgenciada }" maxlength="250">
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
                            <input type="hidden" class="form-control cnpjEcpf cnpjEcpf"  id="cnpjEcpf1" name="cnpjEcpf1" value="${ cvCpfCnpj }">    
                            <label for="cnpjEcpf"><fmt:message key = "br.cefetrj.sisgee.resources.form.exibirCPFeCNPJ"/></label>
                            <div class="input-group">						  
                                <input type="text" class="form-control cnpjEcpf" id="cnpjEcpf" name="cnpjEcpf" value="${ cvCpfCnpj }" readonly>
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
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <label for="dataInicioTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataInicio"/></label>
                            <input type="text" class="form-control col-sm-4 " id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ vidataInicioTermoEstagio }" onchange="sugereData();">
                            <p class="valid-feedback" id="dataIni" name="dataIni"></p>
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dataFimTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataTermino"/></label>
                            <input type="text" class="form-control col-sm-4 " id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${showVigencia eq 'sim' ? '' : vidataFimTermoEstagio }">
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
                            <input type="text" required="required" maxlength="1" pattern="[1-6]+$" class="form-control col-sm-2 " id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ showCargaHoraria eq 'sim' ? '' :cacargaHorariaTermoEstagio }">
                            <c:if test="${ not empty cargaHorariaMsg }">
                                <div class="invalid-feedback">${ cargaHorariaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui Termina Carga Horária-->
                
                <!-- Aqui começa Valor Bolsa-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="valorBolsa"><fmt:message key = "br.cefetrj.sisgee.33"/></label>
                            <input type="text" class="form-control col-sm-6" id="valorBolsa" name="valorBolsa" value="${ showValorBolsa eq 'sim' ? '' : vavalorBolsa }">
                            <c:if test="${ not empty valorBolsaMsg }">
                                <div class="invalid-feedback">${ valorBolsaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui termina Valor Bolsa-->

                <!-- Aqui começa local estágio-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.localEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-12">

                            <label for="enderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></label>
                            <input type="text" required="required" maxlength="255" class="form-control" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :enenderecoTermoEstagio }">
                            <c:if test="${ not empty enderecoMsg }">
                                <div class="invalid-feedback">${ enderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="numeroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numero"/></label>
                            <input type="text" maxlength="10" class="form-control" id="numeroEnderecoTermoEstagio" name="numeroEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :ennumeroEnderecoTermoEstagio }">
                            <c:if test="${ not empty numeroEnderecoMsg }">
                                <div class="invalid-feedback">${ numeroEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="complementoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></label>
                            <input maxlength="150" type="text" class="form-control" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encomplementoEnderecoTermoEstagio }">
                            <c:if test="${ not empty complementoEnderecoMsg }">
                                <div class="invalid-feedback">${ complementoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="bairroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></label>
                            <input type="text" maxlength="150" class="form-control" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :enbairroEnderecoTermoEstagio }">
                            <c:if test="${ not empty bairroEnderecoMsg }">
                                <div class="invalid-feedback">${ bairroEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">					
                        <div class="form-group col-md-6">
                            <label for="cidadeEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></label>
                            <input type="text" maxlength="150" class="form-control" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encidadeEnderecoTermoEstagio }">
                            <c:if test="${ not empty cidadeEnderecoMsg }">
                                <div class="invalid-feedback">${ cidadeEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="estadoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></label>
                            <select name="estadoEnderecoTermoEstagio" id="estadoEnderecoTermoEstagio" class="form-control">
                                <option value="" selected>${showLocal eq 'sim' ? '' :enuf}</option>
                                <c:forEach items="${ uf }" var="uf">
                                    <c:choose>
                                        <c:when test="${ uf.equals(ufTermoEstagio) }">
                                            <option value="${ ufTermoEstagio }" selected>${ ufTermoEstagio }</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${ uf }">${ uf }</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>							
                            </select>
                            <c:if test="${ not empty estadoEnderecoMsg }">
                                <div class="invalid-feedback">${ estadoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cepEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></label>
                            <input type="text" class="form-control" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encepEnderecoTermoEstagio }">
                            <c:if test="${ not empty cepEnderecoMsg }">
                                <div class="invalid-feedback">${ cepEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui começa local estágio-->
                
                <!-- Aqui começa Supervisor-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.20"/></legend>    
                        <div class="form-row" > 
                            <div class="form-group col-md-3"  >
                                <label for="eEstagioObrigatorio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></label>
                            </div>

                            <div class="custom-controls-stacked d-block my-3" >
                                <c:choose>
                                    <c:when test="${ eobrigatorio }">
                                        <label class="custom-control custom-radio">
                                            <input class="custom-control-input"  name="eobrigatorio" type="radio" value="sim" checked />
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                                        </label>
                                        <label class="custom-control custom-radio">
                                            <input class="custom-control-input"  name="eobrigatorio" type="radio" value="nao" />
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                                        </label>
                                    </c:when> 
                                    <c:otherwise>
                                        <label class="custom-control custom-radio">
                                            <input class="custom-control-input"  name="eobrigatorio" type="radio" value="sim"/>
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                                        </label>
                                        <label class="custom-control custom-radio">
                                            <input class="custom-control-input"  name="eobrigatorio" type="radio" value="nao" checked/>
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                                        </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>				
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="nomeSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></label>
                                <input type="text" class="form-control" id="nomeSupervisor"  name="nomeSupervisor" value="${ showSupervisor eq 'sim' ? '' :nomeSupervisor }" maxlength="80">
                                <c:if test="${ not empty nomeSupervisorMsg }">
                                    <div class="invalid-feedback">${ nomeSupervisorMsg }</div>
                                </c:if>                        
                            </div>
                            <div class="form-group col-md-6">
                                <label for="cargoSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></label>
                                <input type="text" class="form-control" id="cargoSupervisor"  name="cargoSupervisor" value="${ showSupervisor eq 'sim' ? '' :cargoSupervisor }" maxlength="80">
                                <c:if test="${ not empty cargoSupervisorMsg }">
                                    <div class="invalid-feedback">${ cargoSupervisorMsg }</div>
                                </c:if>                      
                            </div>
                        </div>                        
                </fieldset>
                <!-- Aqui termina Supervisor-->
                
                <!-- Aqui começa Professor Orientador-->             
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/></legend>
                        <div class="form-group col-md-8">
                                <label for="idProfessorOrientador"></label>
                            <select name="idProfessorOrientador" id="idProfessorOrientador" class="form-control" >
                                <c:forEach items="${ listaProfessores }" var="professor">
                                    <c:choose>
                                        <c:when test="${professor.getIdProfessorOrientador() ==  pfnomeprofessor.getIdProfessorOrientador()}">
                                            <option value="${ pfnomeprofessor.getIdProfessorOrientador() }" selected>${ pfnomeprofessor.getNomeProfessorOrientador() }</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${ professor.getIdProfessorOrientador() }">${ professor.getNomeProfessorOrientador() }</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <c:if test="${ not empty idProfessorMsg }">
                                <div class="invalid-feedback">${ idProfessorMsg }</div>
                            </c:if>				
                        </div>
                </fieldset>
                    
                <input type="submit" class="btn btn-primary" value='<fmt:message key = "br.cefetrj.sisgee.25"/>' />
                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'BuscaTermoAditivoServlet'"><fmt:message key = "br.cefetrj.sisgee.17"/></button>
                    	
            </form>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="myModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label='<fmt:message key = "br.cefetrj.sisgee.27"/>'>
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.27"/></button>
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
            
            $('#dataInicioTermoEstagio').mask('99/99/9999');
            $('#dataFimTermoEstagio').mask('99/99/9999');
            $("#cnpjEcpf1").mask("99.999.999/9999-99");        
            $('#cepEnderecoTermoEstagio').mask('99.999-999');
            $('#dataIni').mask('99/99/9999');
            carregaDadosAluno();
        });
        
        function carregaDadosAluno() {
            var nomeAluno = $('#nomeAluno').val();
            var cursoAluno = $('#nomeCursoAluno').val();
            var campusCursoAluno = $('#unidadeCursoAluno').val();
            
            $('#nome').val(nomeAluno);
            $('#nomeCurso').val(cursoAluno);
            $('#nomeCampus').val(campusCursoAluno);
        }
        
        function alerta(){
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
            if(tipoDeAluno != null){            
                if (tipoDeAluno == "tecnico") {
                    dataNova.setMonth(dataNova.getMonth() + 6);
                    tipoDeAluno = "Curso Técnico";
                }else{
                    dataNova.setMonth(dataNova.getMonth() + 12);
                    tipoDeAluno = "Graduação";
                }            
                document.getElementById("dataIni").innerHTML = "Esse Estágio terminaria em " + dataNova.toLocaleDateString() + " para este aluno de " + tipoDeAluno;
            }
                              
        }   
        
    </script>
</body>



</html>
