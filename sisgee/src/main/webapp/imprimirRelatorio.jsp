<%@page import="br.cefetrj.sisgee.view.utils.ItemRelatorio"%>
<%@page import="java.util.List"%>
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


        <div class="container">
            



            <c:if test="${ not empty msgRelatorio }">
                <div class="alert alert-warning" role="alert">
                    ${ msgRelatorio }
                </div>
            </c:if>
            
            <c:forEach items="${imprimir}" var="relatorio">
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
        </div>




    </body>
</html>