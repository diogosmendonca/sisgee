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
            
            <c:forEach items="${imprimirNovos}" var="relatorioNovos">
                <table class="table table-hover" style="width: 100%; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th><fmt:message key="br.cefetrj.sisgee.relatorio.busca_convenios_relatorio_consolidado_servlet.qnt_convenios_novos"/></th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>		
                        <tr>
                            <td>${ relatorioNovos.size() }</td>
                        </tr>
                    </tbody>
                </table>
            </c:forEach>
            <c:forEach items="${imprimirRenovados}" var="relatorioRenovados">
                <table class="table table-hover" style="width: 100%; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th><fmt:message key="br.cefetrj.sisgee.relatorio.busca_convenios_relatorio_consolidado_servlet.qnt_convenios_renovados"/></th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>		
                        <tr>
                            <td>${ relatorioRenovados.size() }</td>
                        </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>




    </body>
</html>