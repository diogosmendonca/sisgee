<%@page import="br.cefetrj.sisgee.model.entity.Convenio"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetrj.sisgee.control.ConvenioServices"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_titulo"/>
        </title>

    </head>

    <body>
        <%@include file="import_navbar.jspf"%>

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

                    <td><fmt:formatDate value="${not empty b.dataRegistro ? b.dataRegistro : null }" type = "date" dateStyle = "short"/> - <fmt:formatDate value="${b.getDataFinal()}" type = "date" dateStyle = "short"/></td>
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
        
        <c:if test="${ empty ConvenioServices.listarConveniosVencer() }">
            <div class="alert alert-danger" role="alert">
                        <h2><fmt:message key = "br.cefetrj.sisgee.28"/></h2>
                    </div>
        </c:if>
        
        <button type="button" onclick="javascript:location.href = 'index.jsp'" class="btn btn-primary offset-lg-5 mb-5 mt-5"><fmt:message key="br.cefetrj.sisgee.17" /></button>

        <c:if test="${not empty ConvenioServices.listarConveniosVencer()}">
                <a href="imprimirConveniosVencer.jsp" class="btn btn-warning " target="_blank"><fmt:message key="br.cefetrj.sisgee.30" /></a>
            </c:if>
                
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>

    </body>
</html>
