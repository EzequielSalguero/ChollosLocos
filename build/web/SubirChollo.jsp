<%-- 
    Document   : SubirChollo
    Created on : 29-feb-2020, 18:18:26
    Author     : Ezequiel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2.0, minimum-scale=1.0,  user-scalable=yes">
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="CSS/reset.css">
        <link rel="stylesheet" type="text/css" href="CSS/chollista.css">
        <link rel="stylesheet" type="text/css" href="CSS/footer.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link href="CSSBS/bootstrap.min.css" rel="stylesheet" media="screen">
        <title>Subir Chollo</title>
    </head>
    <body>
        
        <jsp:useBean id="beanSeccion" scope="request" class="logicaNegocio.beanSeccion"/>
        
         <% if ( request.getContentType()!=null)  {%>
            <jsp:include page="NuevoChollo"/>
        <%}%>
        
        <header class="row">
            <div class="col text-center">
                <a href="index.jsp"><img id="logo"  src="IMAGENES/logo.png"></a>
            </div>
        </header>
        
        <form class=" m-1 p-2 m-md-5 p-md-3" action="SubirChollo.jsp" id="editor" method="POST" enctype="multipart/form-data">

            <p class="h2 my-3 my-md-5">Compartir un CholloLoco<p>

            <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                <label for="titulo">Titulo</label>
                <input class="form-control" type="text" name="titulo" id="titulo">
            </div>

            <div class="form-group col col-md-10 col-lg-10 offset-md-1">
                <label for="cuerpo">Desarrolla la oferta</label>
                <textarea class="form-control" name="cuerpo" id="cuerpo" rows="10" cols="80"></textarea>
            </div>
            
            <div class="form-group col col-md-3 col-lg-3 offset-md-2 offset-lg-1">
                <label for="precAct">Precio Actual</label>
                <input class="form-control" type="text" name="precAct" id="precAct">€</input>
            </div>
            
            <div class="form-group col col-md-3 col-lg-3 offset-md-2 offset-lg-1">
                <label for="precAnt">Precio Anterior</label>
                <input class="form-control" type="text" name="precAnt" id="precAnt">€</input>
            </div>

            
            <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                <label for="enlace">Enlace</label>
                <input class="form-control" type="text" name="enlace" id="enlace"></input>
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
                <input class="btn btn-info" type="submit" value="Subir chollo" name="btn">
            </div>
        </form>

    </body>
</html>
