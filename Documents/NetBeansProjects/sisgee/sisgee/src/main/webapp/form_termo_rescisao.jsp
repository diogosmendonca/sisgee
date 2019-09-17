<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="import_head.jspf"%>

<title>
	<fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>
</title>

</head>
<body>
	<%@include file="import_navbar.jspf"%>
	
	<div class="container">
		<c:if test="${ not empty msg }">
			<div class="alert alert-warning" role="alert">
				${ msg }
			</div>
		</c:if>
		<c:if test="${ not empty msg2 }">
			<div class="alert alert-warning" role="alert">
				${ msg2 }
			</div>
		</c:if>
		<p class="tituloForm">
		<h5>
			<fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>
		</h5>		
		
		<form action="FormTermoRescisaoServlet" method="post">
			
			<fieldset class="form-group dadosAluno">
				
				<%@include file="import_busca_aluno.jspf"%>
				
			</fieldset>			
			
			
			<fieldset class="form-group">
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></label>
						<input type="text" class="form-control ${ not empty dataTermoRescisaoMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataRescisao"  name="dataTermoRescisao" value="${ param.dataRescisao }" >
					<c:if test="${ not empty dataTermoRescisaoMsg }">
				    	<div class="invalid-feedback">${ dataTermoRescisaoMsg }</div>
		        	</c:if>
					</div>					
				</div>
			</fieldset>
		
			<button type="submit" class="btn btn-primary"> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_salvar"/></button>
			<button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'form_termo_aditivo.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>			
		</form>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="myModalLabel"></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
	<%@include file="import_scripts.jspf"%>
    
</body>
</html>
