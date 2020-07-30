<%-- 
    Document   : EditarNoticia
    Created on : 12-mar-2019, 10:29:33
    Author     : Séfora
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Chollo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2.0, minimum-scale=1.0,  user-scalable=yes">
        <link rel="stylesheet" type="text/css" href="CSS/reset.css">
        <link rel="stylesheet" type="text/css" href="CSS/chollista.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

        <!-- CSS de Bootstrap -->
        <link href="CSSBS/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="JS/jquery.min.js"><\/script>')</script>

    </head>
    <body>
        <jsp:useBean id="beanSeccion" scope="request" class="logicaNegocio.beanSeccion"/>
        <jsp:useBean id="bEditar" scope="request" class="logicaNegocio.beanEditar"/>
        <jsp:setProperty name="bEditar" property="*"/>
        
        <% if ( request.getContentType()!=null)  {%>
            <jsp:include page="EditDeChollo"/>
        <%}%>

        <header class="row">
            <div class="col text-center">
                <a href="index.jsp"><img  src="IMAGENES/logo.png"></a>
            </div>
        </header>
        <c:choose>
            <c:when test="${!empty param.codChollo}">

                <c:set var="chollo" scope="request" value="${bEditar.obtenerChollo(param.codChollo)}"/>

                <form class=" m-1 p-2 m-md-5 p-md-3" action="EditarChollo.jsp" id="editor" method="POST" enctype="multipart/form-data">

                    <p class="h2 my-3 my-md-5">Editar este Chollo<p>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="titulo">Titulo</label>
                        <input class="form-control" type="text" name="titulo" id="titulo" value="${chollo.titulo}">
                    </div>

                    <div class="form-group col col-md-10 col-lg-10 offset-md-1">
                        <label for="cuerpo">Desarrolla la oferta</label>
                        <textarea class="form-control" name="cuerpo" id="cuerpo" rows="10" cols="80">${chollo.cuerpo}</textarea>
                    </div>

                    <div class="form-group col col-md-3 col-lg-3 offset-md-2 offset-lg-1">
                        <label for="precAct">Precio Actual</label>
                        <input class="form-control" type="text" name="precAct" id="precAct" value="${chollo.precAct}">€</input>
                    </div>

                    <div class="form-group col col-md-3 col-lg-3 offset-md-2 offset-lg-1">
                        <label for="precAnt">Precio Anterior</label>
                        <input class="form-control" type="text" name="precAnt" id="precAnt" value="${chollo.precAnt}">€</input>
                    </div>


                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="enlace">Enlace</label>
                        <input class="form-control" type="text" name="enlace" id="enlace" value="${chollo.enlace}"></input>
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="foto">Subir Foto</label>
                        <input class="form-control-file" type="file" name="foto" id="foto">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="seccion">¿A que sección pertenece?</label><br>
                        <c:forEach var="sec" items="${beanSeccion.listaSecciones}">
                            <input type="radio" id="seccion" name="seccion" value="${sec.nomSeccion}"/>${sec.nomSeccion} <br>
                        </c:forEach>     
                    </div>  

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <input class="btn btn-info" type="submit" value="Editar chollo" name="btn">
                        <input type="hidden" name="codigoChollo" value="${chollo.codChollo}">
                        <input type="hidden" name="nLikes" value="${chollo.likes}"/>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <div class="tabla col-lg-12 table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="thead-light">
                            <tr>
                                <th>Título</th>
                                <th>Sección</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="chollo" items="${bEditar.obtenerChollosUsuario(sessionScope.usu.getIdUsuario())}">
                                <tr>
                                    <td>${chollo.titulo}</td>
                                    <td>${chollo.seccion}</td>
                                    <td>
                                       <form class="form-inline" justify-content-center="center" role="form">
                                            <input type="submit" value="Editar" class="btn-primary rounded col-12 col-lg-auto mb-2 mb-lg-0">
                                            <input type="hidden" name="codChollo" value="${chollo.codChollo}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
