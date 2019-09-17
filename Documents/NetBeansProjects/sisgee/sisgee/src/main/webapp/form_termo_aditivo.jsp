<!DOCTYPE html>
<html lang="en">
    <head>


        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
        </title>
        <style>

            table{
                white-space: nowrap ;
            }
        </style>
    </head>
    <body onLoad="termoAditivo()">
        <%@include file="import_navbar.jspf"%>

        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <c:if test="${ not empty periodoMsg }">
                <div class="alert alert-warning" role="alert">
                    ${periodoMsg}
                </div>
            </c:if>  
            
            <p class="tituloForm">
            <h5>		
                <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
            </h5>		
            
            
            <form action=BuscaTermoAditivoServlet method="post">

                <fieldset class="form-group dadosAluno" >
                    <%@include file="import_busca_aluno.jspf"%>
                    
                    <div class="container">					

                        <button id="btnListarAditivo" type="submit" class="btn btn-primary" ><fmt:message key = "br.cefetrj.sisgee.resources.form.listarAditivos"/></button>
                        <a id="btnListarAditivo" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><fmt:message key = "br.cefetrj.sisgee.resources.form.rescisao"/></a>
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'" ><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                    </div>				
                <input type="hidden" name="termoAditivo" id="termoAditivo" value="${ param.termoAditivo }">
                </fieldset>
            </form>
        </div>
        <c:if test="${not empty listaTermoEstagio}">
            <div class="container">
                <div class="table-responsive">
                    <table class="table table-info table-bordered container table-hover table-striped">
                        <tr>
                            <th><fmt:message key="br.cefetrj.sisgee.21" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.22" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.23" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.10" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.13" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.12" /></th>
                            <th>Visualizar</th>

                        </tr>

                        <c:forEach items="${listaTermoEstagio}" var="b">
                            <tr>
                                <td><fmt:message key="br.cefetrj.sisgee.4" /></td>
                                <td>${b.getEstado()}</td>
                                <td>${b.getDataInicioTermoEstagio2()}</td>
                                <td>${b.getDataFimTermoEstagio2()}</td>
                                <td>${b.getConvenio().pegaCpf()}</td>
                                <td>${b.getConvenio().pegaNome()}</td>
                                <td><a class="btn btn-sm btn-primary btn-block" href="VisualizarTermoEAditivo?ide=${b.idTermoEstagio}&matricula=${param.matricula}" >Visualizar</td>
                            </tr>
                            <c:forEach items="${b.getTermosAditivos()}" var="c">
                                <tr>
                                    <td>${c.getTipoAditivo()}</td>
                                    <td>--</td>
                                    <td>${c.getDataCadastramentoTermoAditivo2()}</td>
                                    <td>${c.getDataFimTermoAditivo2()}</td>
                                    <td>${b.getConvenio().pegaCpf()}</td>
                                    <td>${b.getConvenio().pegaNome()}</td>
                                    <td><a class="btn btn-sm btn-primary btn-block" href="VisualizarTermoEAditivo?ida=${c.idTermoAditivo}&ide=${b.idTermoEstagio}&matricula=${param.matricula}" >Visualizar</td>
                                </tr>   
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="container">                
                <form action="TermoAditivoServlet" method="post">

                    <br>

                    <div class="mx-auto" style="width: 500px;">
                        <div class="row">
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" id="vigencia" name="alvigencia"  value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/>
                                    <input type="hidden" name="alvigencia" value=${alvigencia}>
                                </label>
                            </div>

                            <div class="mx-auto" style="width: 200px;">
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" id="enderecoTermoEstagio" name="alendereco" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/>
                                        <input type="hidden" name="alendereco" value=${alendereco}>
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="mx-auto" style="width: 500px;">
                        <div class="row">
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" id="cargaHorariaTermoEstagio" name="alcargaHoraria" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/>
                                    <input type="hidden" name="alcargaHoraria" value=${alcargaHoraria}>
                                </label>
                            </div>

                            <div class="mx-auto" style="width: 200px;">
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" id="professorOrientador" name="alprofessor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/>
                                        <input type="hidden" name="alprofessor" value=${alprofessor}>
                                    </label>
                                </div>
                            </div>				
                        </div>
                    </div>

                    <div class="mx-auto" style="width: 500px;">
                        <div class="row">
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" id="alsupervisor" name="alsupervisor" value="sim"><fmt:message key="br.cefetrj.sisgee.24" />
                                    <input type="hidden" name="alsupervisor" value=${alsupervisor}>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" id="valorBolsa" name="alvalor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/>
                                    <input type="hidden" name="alvalor" value=${alvalor}>
                                </label>
                            </div>
                        </div>

                    </div>			
                    <br>			
                    <input type="hidden" name="idAlunoAdt" value="${param.matricula}">


                    <button type="submit" id="btnNovoAditivo" class="btn btn-secondary" ${ empty param.nome ? 'disabled' : '' }><fmt:message key = "br.cefetrj.sisgee.resources.form.novo_aditivo"/></button>
                    <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>			
                </form>
                    
            </c:if>
                

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="myModalLabel"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                            <div class="modal-body">
                                <form action="FormTermoRescisaoServlet" method="post">
                                    <input type="hidden" id="idAluno" name="idAluno" value="${ param.idAluno }">
                                        <div class="container">
                                            <div class="col-xs-1" align="center">
                                                <label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control ${ not empty dataTermoRescisaoMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataRescisao"  name="dataTermoRescisao" value="${ param.dataRescisao }" >
                                                    <c:if test="${ not empty dataTermoRescisaoMsg }">
                                                        <div class="invalid-feedback">${ dataTermoRescisaoMsg }</div>
                                                    </c:if>
                                                </div>
                                            </div>					
                                        </div>
                                        <button type="submit" class="btn btn-primary"> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_salvar"/></button>
                                        <!--<button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'form_termo_aditivo.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>-->		
                                </form>                                   
                            </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                        </div>
                    </div>
                </div>
            </div>
                      
                        
                        
                        
                     
                        
                        
                        
                        
                        
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <script type="text/javascript">
            function hablitarButoes() {
                $("#btnNovoAditivo").prop("disabled", false);
                $("#btnNovoAditivo").removeClass("btn-secondary");
                $("#btnNovoAditivo").addClass("btn-primary");

                $("#btnListarAditivo").prop("disabled", false);
                $("#btnListarAditivo").removeClass("btn-secondary");
                $("#btnListarAditivo").addClass("btn-primary");
            }
            var buscarAlunoCallback = function myCallback(json) {
                if (json != null) {
                    if (json.idTermoEstagioAtivo != null && json.idTermoEstagioAtivo != "") {
                        //atribui o id do termo de estágio para o campo hidden

                        //tem termo de estágio, ativa os botões
                        hablitarButoes();
                    } else {
                        //não tem termo de estágio
                    }
                }
            }
        </script>
        <%@include file="import_scripts.jspf"%>
        <script type="text/javascript">

            $(document).ready(function () {
                $(".form-check-input").change(function () {
                    $('#idAlunoAdt').val($("#idAluno").val());
                });

                if ($("#idAluno").val() != "") {

                }
            });
            
            function termoAditivo(){
                document.getElementById("termoAditivo").value = "sim";
            }
        </script>

    </body>
</html>