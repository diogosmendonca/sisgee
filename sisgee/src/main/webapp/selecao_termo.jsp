<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="import_head.jspf"%>
<title><fmt:message key = "br.cefetrj.sisgee.resources.form.sisgee"/></title>
</head>
<body>
	<%@include file="import_navbar.jspf"%>
	<div class="container">
		<p>
			<c:choose>
				<c:when test="${ not empty msg }">
					<div class="alert alert-success" role="alert">
						<h2>${ msg }</h2>
					</div>
				</c:when>
				<c:otherwise>
					<h3><fmt:message key = "br.cefetrj.sisgee.resources.form.bemVindoSisgee"/></h3>
				</c:otherwise>
			</c:choose>
		</p>
	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
</body>
</html>
