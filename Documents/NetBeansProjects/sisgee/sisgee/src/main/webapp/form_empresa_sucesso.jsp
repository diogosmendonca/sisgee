

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

        <%
            String codigo = request.getParameter("numeroConvenioGerado");


        %>
        
            
            
            
                
                <fieldset class="form-group col-10 offset-1 mt-5 mb-5">
                    <div class="alert alert-sucess offset-2 mt-5" style="font-family: Tajawal, Helvetica; color: #28a745; font-size: 30px; font-weight: bolder" role="alert">
                       ${msg}
                    </div>
                         <div class="form-inline offset-3 alert alert-sucess"  >
                            <label for="numeroConvenioMsg" style="font-family: Tajawal, Helvetica; color: #000; font-size: 20px; font-weight: bolder" ><fmt:message key="br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_convenio_numero_gerado" /></label>
                            <label for="numeroConvenio" class="ml-3" style="font-family: Tajawal, Helvetica; color: #bd2130; font-size: 30px; font-weight: bolder" > ${numeroConvenioGerado}</label>
                         </div>
                        <div class="offset-6 mb-5" >
                            <button type="button" class="btn btn-primary " onclick="javascript:location.href = 'index.jsp'">Ok</button>
                        </div>
                </fieldset>
            
            
       


        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>

    </body>
</html>
