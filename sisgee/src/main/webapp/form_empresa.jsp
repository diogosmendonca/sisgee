<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>
        <title>
            <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_titulo"/>
        </title>
    </head>
    <body style="font-family: 'Roboto Slab', Helvetica">
        <%@include file="import_navbar.jspf"%>
        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <p class="tituloForm">
            <h5 class="offset-5"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo" /></h5>
            <form id="meuForm" action="ValidaCadastroEmpresaServlet" method="POST">
                <fieldset class="form-group">
                    <div class="form-row  " >
                        <div class="form-group col-md-2 mt-2 " >
                            <label for="Tipo"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_Tipo" /></label>
                        </div>

                        <div class="custom-controls-stacked d-block my-3 ">
                            <label class="custom-control custom-radio"> 
                                <input id="pessoaJuridica" name="tipoPessoa" type="radio"   ${ not empty isEmpresa ? 'checked ' : '' } class="custom-control-input ehAgenteCheck ${ not empty isAgenteIntegracaoMsg ? 'is-invalid' : '' }" ${ not empty tipoPessoaMsg ? '' : param.tipoPessoa == 'sim' ? 'checked' : '' } required value = "sim" > 
                                <span class="custom-control-indicator"></span> 
                                <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_pessoaJuridica"/></span>
                            </label> 
                            <label class="custom-control custom-radio"> 
                                <input id="pessoaFisica" name="tipoPessoa" type="radio" ${ not empty isPessoa ? 'checked' : '' }class="custom-control-input ehAgenteCheck ${ not empty isAgenteIntegracaoMsg ? 'is-invalid' : '' }"  ${ not empty tipoPessoaMsg ? '' : param.tipoPessoa == 'nao' ? 'checked' : '' } required value = "nao" > 
                                <span class="custom-control-indicator"></span> 
                                <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_pessoaFisica"/></span>
                            </label>

                        </div>
                    </div>

                    <div class=" isAI AI" ${ empty param.tipoPessoa ? "style='display:none'" : param.tipoPessoa == "nao" ? "style='display:none'" : "" } >     
                        <div class="form-row "  >
                            <div class="form-group col-md-2 mt-2 mr-4" >
                                <label for="agenteIntegracao"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_agente_integracao" /></label>
                            </div>
                            <div class="custom-controls-stacked d-block my-3 mr-5 ml-5 ">
                                <label class="custom-control custom-radio"> 
                                    <input id="SimAgente" name="agenteIntegracao" type="radio" class="custom-control-input agenteIntegracaoNotAI  ${ not empty simAgenteIntegracao ? 'checked ' : '' } ${ not empty agenteIntegracaoMsg ? 'is-invalid': '' } " ${ not empty agenteIntegracaoMsg ? '' : param.agenteIntegracao == 'true' ? 'checked' : '' } value="true"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></span>
                                </label> 
                                <label class="custom-control custom-radio"> 
                                    <input id="NaoAgente" name="agenteIntegracao" type="radio" class="custom-control-input agenteIntegracaoNotAI ${ not empty naoAgenteIntegracao ? 'checked ' : '' } ${ not empty agenteIntegracaoMsg ? 'is-invalid': '' }" ${ not empty agenteIntegracaoMsg ? '' : param.agenteIntegracao == 'false' ? 'checked' : '' } value="false"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></span>
                                </label>
                                <c:if test="${ not empty agenteIntegracaoMsg }">
                                    <div class="invalid-feedback">${ agenteIntegracaoMsg }</div>
                                </c:if>
                            </div>

                        </div>
                        <div class="form-group col-md-6">
                            <label for="cnpjEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cnpj"/></label>
                            <input type="text" class="form-control ${ not empty cnpjEmpresaMsg ? 'is-invalid': 'is-valid' }" id="cnpjEmpresa" name="cnpjEmpresa" value="${ param.cnpjEmpresa }">
                            <c:if test="${ not empty cnpjEmpresaMsg }">
                                <div class="invalid-feedback">${ cnpjEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nomeEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_razao_social"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="nomeEmpresa" name="nomeEmpresa"   maxlength="100" value="${ param.nomeEmpresa }">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">

                            <label for="dataRegistroConvenioEmpresa"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataRegistro"/></label>
                            <input type="text" class=" form-control col-sm-4 ${ not empty dataRegistroEmpresaMsg ? 'is-invalid': 'is-valid' }" id="dataRegistroConvenioEmpresa"  name="dataRegistroConvenioEmpresa" value="${param.dataRegistroConvenioEmpresa}" >
                            <c:if test="${ not empty dataRegistroEmpresaMsg }">
                                <div class="invalid-feedback">${ dataRegistroEmpresaMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="emailEmpresa"><fmt:message key = "br.cefetrj.sisgee.resources.form.emailEmpresa"/></label>
                            <input type="text" class="form-control ${ not empty emailEmpresaMsg ? 'is-invalid': 'is-valid' }" id="emailEmpresa" name="emailEmpresa" maxlength="100" value="${param.emailEmpresa}">
                            <c:if test="${ not empty emailEmpresaMsg }">
                                <div class="invalid-feedback">${ emailEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="telefoneEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_telefone"/></label>
                            <input type="text" class="form-control ${ not empty telefoneEmpresaMsg ? 'is-invalid': 'is-valid' }" id="telefoneEmpresa" name="telefoneEmpresa" maxlength="12"  value="${param.telefoneEmpresa}">
                            <c:if test="${ not empty telefoneEmpresaMsg }">
                                <div class="invalid-feedback">${ telefoneEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="contatoEmpresa"><fmt:message key = "br.cefetrj.sisgee.resources.form.contatoEmpresa"/></label>
                            <input type="text" class="form-control ${ not empty contatoEmpresaMsg ? 'is-invalid': 'is-valid' }" id="contatoEmpresa" name="contatoEmpresa" maxlength="100"  value="${param.contatoEmpresa}">
                            <c:if test="${ not empty contatoEmpresaMsg }">
                                <div class="invalid-feedback">${ contatoEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-8">
                            <label for="numeroEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_numero"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control ${ not empty numeroEmpresaMsg ? 'is-invalid': 'is-valid' }" id="numeroEmpresa" name="numeroEmpresa" value="${numeroEmpresa}">
                                <span class="input-group-btn"> 
                                    <button class="btn btn-primary" type="button" id="btnSetarNumeroSugerido" onClick="setValorSugeridoEmpresa()"><fmt:message key = "br.cefetrj.sisgee.form_empresa.btn_usar_numero_sugerido"/></button>
                                </span>
                                <c:if test="${ not empty numeroEmpresaMsg }">
                                    <div class="invalid-feedback">${numeroEmpresaMsg }</div>
                                </c:if>
                            </div>

                        </div>


                        <div class="form-group col-md-6">
                            <label for="anoEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_ano"/></label>
                            <input type="text" class="form-control ${ not empty anoEmpresaMsg ? 'is-invalid': 'is-valid' }" id="anoEmpresa" name="anoEmpresa" value="${anoEmpresa}">
                            <c:if test="${ not empty anoEmpresaMsg }">
                                <div class="invalid-feedback">${anoEmpresaMsg }</div>
                            </c:if>
                        </div>    

                    </div>         

                    <div class=" notAI AI" ${ empty param.tipoPessoa ? "style='display:none'" : param.tipoPessoa == "sim" ? "style='display:none'" : "" } >

                        <div class="form-group col-md-6">
                            <label for="cpfPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cpf"/></label>
                            <input type="text" class="form-control ${ not empty cpfPessoaMsg ? 'is-invalid': 'is-valid' }" id="cpfPessoa" name="cpfPessoa"   value="${ param.cpfPessoa }">
                            <c:if test="${ not empty cpfPessoaMsg }">
                                <div class="invalid-feedback">${ cpfPessoaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nomePessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nome"/></label>
                            <input type="text" class="form-control ${ not empty nomePessoaMsg ? 'is-invalid': 'is-valid' }" id="nomePessoa" name="nomePessoa" maxlength="100"  value="${ param.nomePessoa }">
                            <c:if test="${ not empty nomePessoaMsg }">
                                <div class="invalid-feedback">${ nomePessoaMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">

                            <label for="dataRegistroConvenioPessoa"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataRegistro"/></label>
                            <input type="text" class=" form-control col-sm-4 ${ not empty dataRegistroPessoaMsg ? 'is-invalid': 'is-valid' }" id="dataRegistroConvenioPessoa"  name="dataRegistroConvenioPessoa" value="${param.dataRegistroConvenioPessoa}" >
                            <c:if test="${ not empty dataRegistroPessoaMsg }">
                                <div class="invalid-feedback">${ dataRegistroPessoaMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="emailPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_email"/></label>
                            <input type="text" class="form-control ${ not empty emailPessoaMsg ? 'is-invalid': 'is-valid' }" id="emailPessoa" name="emailPessoa" maxlength="50"  value="${param.emailPessoa}">
                            <c:if test="${ not empty emailPessoaMsg }">
                                <div class="invalid-feedback">${ emailPessoaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="telefonePessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_telefone"/></label>
                            <input type="text" class="form-control ${ not empty telefonePessoaMsg ? 'is-invalid': 'is-valid' }" id="telefonePessoa" name="telefonePessoa" maxlength="11"  value="${param.telefonePessoa}">
                            <c:if test="${ not empty telefonePessoaMsg }">
                                <div class="invalid-feedback">${ telefonePessoaMsg }</div>
                            </c:if>
                        </div>


                        <div class="form-group col-md-8">
                            <label for="numeroPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_numero"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control ${ not empty numeroPessoaMsg ? 'is-invalid': 'is-valid' }" id="numeroPessoa" name="numeroPessoa" value="${numeroPessoa}">
                                <span class="input-group-btn"> 
                                    <button class="btn btn-primary" type="button" id="btnSetarNumeroSugeridoPessoa" onClick="setValorSugeridoPessoa()"><fmt:message key = "br.cefetrj.sisgee.form_empresa.btn_usar_numero_sugerido"/></button>
                                </span>
                                <c:if test="${ not empty numeroPessoaMsg }">
                                    <div class="invalid-feedback">${numeroPessoaMsg }</div>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="anoPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_ano"/></label>
                            <input type="text" class="form-control ${ not empty anoPessoaMsg ? 'is-invalid': 'is-valid' }" id="anoPessoa" name="anoPessoa" value="${anoPessoa}">
                            <c:if test="${ not empty anoPessoaMsg }">
                                <div class="invalid-feedback">${anoPessoaMsg }</div>
                            </c:if>
                        </div>

                    </div>

                </fieldset>
                <input type ="hidden" name="numeroSugerido" value="${numeroSugerido}">
                <button type="submit" class="btn btn-primary" ><i class="far fa-save"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_salvar"/></button>
                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>

            </form>
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <%@include file="import_scripts.jspf"%>


        <script>
            $(document).ready(function () {
                $('#cnpjEmpresa').mask('99.999.999/9999-99');
                $('#cpfPessoa').mask('999.999.999-99');
                $('#dataRegistroConvenioEmpresa').mask('99/99/9999');
                $('#dataRegistroConvenioPessoa').mask('99/99/9999');
                $('#numeroEmpresa').mask('999999');
                $('#numeroPessoa').mask('999999');
                $('#anoPessoa').mask('9999');
                $('#anoEmpresa').mask('9999');
            });


            function setValorSugeridoEmpresa() {
                document.getElementById("numeroEmpresa").value = "${numeroSugerido}";
            }
            
            function setValorSugeridoPessoa() {
                document.getElementById("numeroPessoa").value = "${numeroSugerido}";
            }
        </script>
    </body>