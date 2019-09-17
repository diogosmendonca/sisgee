<%@page import="br.cefetrj.sisgee.model.entity.Convenio"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetrj.sisgee.control.ConvenioServices"%>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <%@include file="import_head.jspf"%>

        <title>
           <fmt:message key = "br.cefetrj.sisgee.resources.form.sisgee"/>	
        </title>
    </head>

    <body>


        <div class="container">




            

            <c:if test="${ not empty ConvenioServices.listarConveniosVencer() }">

                <table id="myTable" class="table table-info table-bordered container table-hover table-striped " >
                    <thead>
                        <tr>

                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.10" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.11" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.12" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.13" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.14" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.15" /></th>
                            <th scope="col"><fmt:message key="br.cefetrj.sisgee.16" /></th>

                        </tr>
                    </thead>


                    <c:forEach items="${ ConvenioServices.listarConveniosVencer()}" var="b" >
                        <tr>

                            <td><fmt:formatDate value="${not empty b.dataAssinatura ? b.dataAssinatura : null }" type = "date" dateStyle = "short"/> - <fmt:formatDate value="${b.getDataFinal()}" type = "date" dateStyle = "short"/></td>
                            <td>${not empty b.numeroConvenio ? b.numeroConvenio : null }</td>
                            <td>${not empty b.empresa ? b.empresa.razaoSocial: b.pessoa.nome } </td>
                            <td>${not empty b.empresa ? b.empresa.cnpjEmpresa : b.pessoa.cpf }</td>
                            <td>${not empty b.empresa ? b.empresa.emailEmpresa: b.pessoa.email } </td>
                            <td>${not empty b.empresa ? b.empresa.telefoneEmpresa : b.pessoa.telefone }</td>
                            <td>${not empty b.empresa ? b.empresa.contatoEmpresa : null}</td>


                        </tr>
                    </c:forEach>

                </table>
            </c:if>
        </div>




    </body>
</html>