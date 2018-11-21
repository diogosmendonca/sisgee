<!DOCTYPE html>
<html lang="en">
    <head>


        <%@include file="import_head.jspf"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                        <button type="button" id="btnEncerrarTermo" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><fmt:message key = "br.cefetrj.sisgee.resources.form.rescisao"/></button>
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
                            <th><fmt:message key="br.cefetrj.sisgee.37" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.31" /></th>
                            <th><fmt:message key="br.cefetrj.sisgee.36" /></th>
                        </tr>

                        <c:forEach items="${listaTermoEstagio}" var="b">
                            <tr>
                                <td><fmt:message key="br.cefetrj.sisgee.4" /></td>
                                <td>${b.getEstado()}</td>
                                <td>${b.getDataInicioTermoEstagio2()}</td>
                                <td>${b.getDataFimTermoEstagio2()}</td>
                                <td>${b.getConvenio().pegaCpf()}</td>
                                <td>${b.getConvenio().pegaNome()}</td>
                                <td><a class="btn btn-sm btn-primary btn-block" href="VisualizarTermoEAditivo?ide=${b.idTermoEstagio}&matricula=${param.matricula}" ><fmt:message key="br.cefetrj.sisgee.37" /></a></td>
                                <td><button type="button" ${ empty b.getTermosAditivos() ? '' : 'disabled="disabled"' } class="btn btn-sm btn-primary" data-toggle="modal" data-target="#${b.idTermoEstagio}"><fmt:message key="br.cefetrj.sisgee.31" /></button></td>
                                <td><button ${ b.getTermosAditivos().size() > 0 ? 'disabled="disabled"' : '' } class="btn btn-sm btn-primary btn-block" onclick="window.location.href = 'EditarTermoEAditivo?ide=${b.idTermoEstagio}&matricula=${param.matricula}'" ><fmt:message key="br.cefetrj.sisgee.36" /></button></td>
                                <!-- Modal -->
                            <div class="modal fade" id="${b.idTermoEstagio}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel"><fmt:message key = "br.cefetrj.sisgee.39"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="ExcluirTermoEstagioServlet?ide=${b.idTermoEstagio}&matricula=${param.matricula}" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.33"/></a>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.34"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                            <c:forEach items="${b.getTermosAditivos()}" var="c" varStatus="status">
                                <tr>
                                    <td><fmt:message key="${c.getTipoAditivoMSG()}"/></td>
                                    <td>--</td>
                                    <td>${c.getDataCadastramentoTermoAditivo2()}</td>
                                    <td>${c.getDataFimTermoAditivo2()}</td>
                                    <td>${b.getConvenio().pegaCpf()}</td>
                                    <td>${b.getConvenio().pegaNome()}</td>
                                    <td><a class="btn btn-sm btn-primary btn-block" href="VisualizarTermoEAditivo?ida=${c.idTermoAditivo}&ide=${b.idTermoEstagio}&matricula=${param.matricula}" ><fmt:message key="br.cefetrj.sisgee.37" /></td>
                                    <c:choose>
                                        <c:when test="${status.last}">
                                            <td><button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#${c.idTermoAditivo}_${b.idTermoEstagio}"><fmt:message key="br.cefetrj.sisgee.31" /></button></td>
                                            </c:when>
                                            <c:otherwise>
                                            <td><button class="btn btn-sm btn-primary" type="button" ${'disabled="disabled"'} onClick="informacao()"><fmt:message key="br.cefetrj.sisgee.31" /></button></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${status.last}">
                                            <td><a class="btn btn-sm btn-primary" href="AlterarTermoAditivo?ida=${c.idTermoAditivo}&ide=${b.idTermoEstagio}&matricula=${param.matricula}" ><fmt:message key="br.cefetrj.sisgee.36" /></a></td>
                                            </c:when>
                                            <c:otherwise>
                                            <td><button class="btn btn-sm btn-primary" type="button" ${'disabled="disabled"'} onClick="informacao()"><fmt:message key="br.cefetrj.sisgee.36" /></button></td>
                                            </c:otherwise>
                                        </c:choose>
                                    <!-- Modal -->

                                <div class="modal fade" id="${c.idTermoAditivo}_${b.idTermoEstagio}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

                                    <div class="modal-dialog" role="document">

                                        <div class="modal-content">

                                            <div class="modal-header">

                                                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key = "br.cefetrj.sisgee.32"/></h5>

                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                                    <span aria-hidden="true">&times;</span>

                                                </button>

                                            </div>

                                            <div class="modal-footer">

                                                <a href="ExcluirTermoAditivoServlet?ida=${c.idTermoAditivo}&ide=${b.idTermoEstagio}&matricula=${param.matricula}" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.33"/></a>

                                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.34"/></button>

                                            </div>

                                        </div>

                                    </div>

                                </div>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div>
            </div>


            <div class="container">
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.EscolhaTipoAditivo"/></legend>
                    <form action="TermoAditivoServlet" method="post">
                        <div class="mx-auto" style="width: 700px;">
                            <div class="row">
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="vigencia" name="alvigencia"  value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.AditivoDeVigência"/>
                                            <input type="hidden" name="alvigencia" value=${alvigencia}>
                                        </label>
                                    </div>
                                </div>
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="enderecoTermoEstagio" name="alendereco" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.AditivoDeEndereço"/>
                                            <input type="hidden" name="alendereco" value=${alendereco}>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="mx-auto" style="width: 700px;">
                            <div class="row">
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="cargaHorariaTermoEstagio" name="alcargaHoraria" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.AditivoDeCargaHoraria"/>
                                            <input type="hidden" name="alcargaHoraria" value=${alcargaHoraria}>
                                        </label>
                                    </div>
                                </div>
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="professorOrientador" name="alprofessor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.AditivoDeProfOrientador"/>
                                            <input type="hidden" name="alprofessor" value=${alprofessor}>
                                        </label>
                                    </div>
                                </div>				
                            </div>
                        </div>

                        <div class="mx-auto" style="width: 700px;">
                            <div class="row">
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="alsupervisor" name="alsupervisor" value="sim"><fmt:message key="br.cefetrj.sisgee.resources.form.AditivoDeSupervisor" />
                                            <input type="hidden" name="alsupervisor" value=${alsupervisor}>
                                        </label>
                                    </div>
                                </div>
                                <div class="mx-auto" style="width: 300px;">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="valorBolsa" name="alvalor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.AditivoDeValorBolsa"/>
                                            <input type="hidden" name="alvalor" value=${alvalor}>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>			
                        <input type="hidden" name="idAlunoAdt" value="${param.matricula}">

                        <div class="container">
                            <button type="submit" id="btnNovoAditivo" class="btn btn-primary" ${ empty param.nome ? 'disabled' : '' }><fmt:message key = "br.cefetrj.sisgee.resources.form.novo_aditivo"/></button>
                            <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>			
                        </div>
                    </form>
                </fieldset>
            </div>
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





            $(".alterar").on('click', function () {

                var id = $(this).data('id'); //recuperar qual o id da linha

                //agora vamos usar o id da linha para recuperar cada campo..

                var nome = $('#nome' + id).text(); //vai retornar nome da linha do botão

                var descricao = $('#descricao' + id).text(); //vai retornar descricao da linha do botao

                //..assim por diante..

                //agora voce pode jogar esses valores no seu modal

                //depois de jogar tudo, voce pode exibir seu modal manualmente:

                $("#DialogAlterarMaterial").modal();

            });



            function termoAditivo() {

                document.getElementById("termoAditivo").value = "sim";

            }
            
            function informacao(){
                alert("<fmt:message key="br.cefetrj.sisgee.form_alterar_termo_aditivo.msg_erro_botao"></fmt:message>");
            }

        </script>



    </body>

</html>