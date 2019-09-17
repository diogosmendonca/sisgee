<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>
        <title><fmt:message key = "br.cefetrj.sisgee.resources.form.sisgee"/></title>
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="node_modules/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" >
        

    </head>
    <body  onunload="window.opener.atualizaAgenteIntegracao()" >
        <%@include file="import_navbar.jspf"%>
        <div class="container" style="margin: 0px;" >
            <p>
                <c:choose>
                    <c:when test="${ not empty msg }">
                    <div class="alert alert-success" role="alert">
                        <h2>${ msg }</h2>
                    </div>
                </c:when>
                    <c:when test="${ not empty erroBuscar }">
                    <div class="alert alert-danger" role="alert">
                        <h2>${ erroBuscar }</h2>
                    </div>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <div id="myCarousel" class="carousel slide " style="margin: 0px; padding: 0px;" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" style="margin: 0px; padding: 0px;">



            <div class="carousel-item">

                <img  src="imgs/carousel2.jpeg" class="img-fluid d-block" alt="Sisgee">
                <div class="carousel-caption" style="background-color: #000; border-radius: 10px; padding: 5px;">
                    <h3 ><fmt:message key="br.cefetrj.sisgee.index.carousel1" /></h3>
                    <p ><fmt:message key="br.cefetrj.sisgee.index.carousel1.a" /></p>
                </div>
            </div>

            <div class="carousel-item active">
                <img  src="imgs/carousel1.jpg" class="img-fluid d-block" alt="Convênio">
                <div class="carousel-caption" style="background-color: #000; border-radius: 10px; padding: 5px;">
                    <h3><fmt:message key="br.cefetrj.sisgee.index.carousel2" /></h3>
                    <p><fmt:message key="br.cefetrj.sisgee.index.carousel2.a" /></p>
                </div>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control-prev" href="#myCarousel" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
            <span class="sr-only">Anterior</span>
        </a>
        <a class="right carousel-control-next" href="#myCarousel" data-slide="next">
            <span class="carousel-control-next-icon"></span>
            <span class="sr-only">Próximo</span>
        </a>
    </div>
        
        <!-- Marketing messaging and featurettes
      ================================================== -->
      <!-- Wrap the rest of the page in another container to center all the content. -->

      <div class="container marketing mt-5 ">

        <!-- Three columns of text below the carousel -->
        

        <section id="services" class="mb-5">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading"><fmt:message key="br.cefetrj.sisgee.1" /></h2>
            <hr class="my-4">
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
                <a href="form_empresa.jsp">
              <i class="fa fa-4x fa-newspaper text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3"><fmt:message key="br.cefetrj.sisgee.2" /></h3>
              </a>
              <p class="text-muted mb-0"><fmt:message key="br.cefetrj.sisgee.3" /></p>
                
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
              <a href="form_termo_estagio.jsp">
            <div class="service-box mt-5 mx-auto" >
              <i class="fa fa-4x fa-pencil-alt text-primary mb-3 sr-icons" ></i>
              <h3 class="mb-3"><fmt:message key="br.cefetrj.sisgee.4" /></h3>
              </a>
              <p class="text-muted mb-0"><fmt:message key="br.cefetrj.sisgee.5" /></p>
            </div>
             
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
                <a href="form_termo_aditivo.jsp">
              <i class="fa fa-4x fa-exchange-alt text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3"><fmt:message key="br.cefetrj.sisgee.6" /></h3>
              </a>
              <p class="text-muted mb-0"><fmt:message key="br.cefetrj.sisgee.7" /></p>
                
            </div>
          </div>
          <div class="col-lg-3 col-md-6 text-center">
            <div class="service-box mt-5 mx-auto">
                <a href="relatorio_consolidado.jsp">
              <i class="fa fa-4x fa-book text-primary mb-3 sr-icons"></i>
              <h3 class="mb-3"><fmt:message key="br.cefetrj.sisgee.8" /></h3>
              </a>
              <p class="text-muted mb-0"><fmt:message key="br.cefetrj.sisgee.9" /></p>
                
            </div>
          </div>
        </div>
      </div>
    </section>

    <%@include file="import_footer.jspf"%>
    <%@include file="import_finalbodyscripts.jspf"%>



</body>

</html>
