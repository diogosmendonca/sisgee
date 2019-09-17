<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.title"/>	
        </title>
    </head>

    <body>
        <%@include file="import_navbar.jspf"%>

        <div class="container">
            <h5><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.title" /></h5>



            <form action="ValidaRelatorioConsolidadoServlet" method="POST" >
                <fieldset class="form-group">
                    <c:if test="${ not empty dataMsg }">
                        <div class="alert alert-warning"> ${ dataMsg }</div>
                    </c:if>
                    <legend class="col-form-legend col-lg"><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.lgd_criterio_pesquisa" /></legend>
                    <c:if test="${ not empty msgDataObrig }">
                        <div class="alert alert-warning" role="alert">
                            ${ msgDataObrig }
                        </div>
                    </c:if>
                    <c:if test="${ not empty msgComparaDatas }">
                        <div class="alert alert-warning" role="alert">
                            ${ msgComparaDatas }
                        </div>
                    </c:if>
                    <div class="form-row">
                        <div class="form-group col-md-4" style="margin-left: 10px;">

                            <label for="dataDeInicio"><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_data_inicio" /></label>
                            <input type="text" class="form-control" id="dataInicio"  name="dataDeInicio" value="${ param.dataDeInicio }">
                            <br>
                            <c:if test="${ not empty msgDataInicio }">
                                <div class="alert alert-danger" role="alert">
                                    ${ msgDataInicio }
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4" style="margin-left: 10px;">
                            <label for="dataDeTermino"><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_data_termino" /></label>

                            <input type="text" class="form-control " id="dataFim"   name="dataDeTermino" value="${ param.dataDeTermino }">
                            <br>
                            <c:if test="${ not empty msgDataTermino }">
                                <div class="alert alert-danger" role="alert">
                                    ${ msgDataTermino }
                                </div>
                            </c:if>
                        </div>


                    </div>
                    <div class="form-row "style="margin-left: 5px; margin-bottom: 15px;">
                        <div class="form-check form-check-inline " >
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="estagioObrigatorio" value="${ param.estagioObrigatorio }" >
                                <p class="font-weight-light text-info">
                                    <fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_estagio_obrg" />
                                </p>
                            </label>
                        </div>
                        <div class="form-check form-check-inline ">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="estagioNaoObrigatorio" value="${ param.estagioNaoObrigatorio }"  >
                                <p class="font-weight-light text-info"> 
                                    <fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_estagio_nao_obrg" />
                                </p>
                            </label>
                        </div>
                        <c:if test="${ not empty msgCheckEstagio }">
                            <div class="alert alert-warning" role="alert">
                                ${ msgCheckEstagio }
                            </div>
                        </c:if>
                    </div>				
                </fieldset>
                <button type="submit" class="btn btn-primary"><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.btn_pesquisar" /></button>
                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>

            </form>
            <c:if test="${ not empty msgRelatorio }">
                <div class="alert alert-warning" role="alert">
                    ${ msgRelatorio }
                </div>
            </c:if>
            <c:forEach items="${ relatorio }" var="relatorio">
                <table class="table table-hover" style="width: 100%; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th>${ relatorio.nomeCurso }</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>		
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_termoestagio"/></td>
                            <td>${ relatorio.qntTermoEstagio }</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_termoaditivo"/></td>
                            <td>${ relatorio.qntTermoAditivo }</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_rescisao" /></td>
                            <td>${ relatorio.qntRescReg }</td>
                        </tr>
                    </tbody>
                </table>

            </c:forEach>
            <c:if test="${not empty relatorio}">
                <a href="imprimirRelatorio.jsp" class="btn btn-warning offset-5" target="_blank"><fmt:message key="br.cefetrj.sisgee.30" /></a>
            </c:if>
        </div>

        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <script type="text/javascript">
            $('#dataInicio, #dataFim').datepicker({
            <c:if test="${ lang eq 'pt_BR' }">
                language: 'pt-BR'
            </c:if>
            });

            $('.isAI, .notAI').hide();

            $('.isAgenteChk').change(function () {
                $('.AI').hide();
                $(this).val() == 'sim' ? $('.isAI').show("slow") : $('.notAI').show("slow");
            });

        </script>
    </body>
</html>