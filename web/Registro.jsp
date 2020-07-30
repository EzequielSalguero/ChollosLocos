<%-- 
    Document   : Registro
    Created on : 25-feb-2020, 14:18:28
    Author     : Ezequiel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/registro.css">
        <link rel="stylesheet" type="text/css" href="CSS/footer.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <title>Registro</title>
    </head>
    <body>

        <jsp:useBean id="nuevoUsu" scope="request" class="DTO.Usuario">
            <jsp:setProperty name="nuevoUsu" property="*"/>
        </jsp:useBean>

        <c:if test="${!empty param.Alta}">
            <jsp:include page="RegistrarUsuario"/>
        </c:if>

        <header>
            <a href="index.jsp"><img id="logo" src="IMAGENES/logo.png"></a>
        </header>

        <section class="registro">

            <h2>Registrarme</h2>

            <form action="#" method="post">
                <input type="text" name="idUsuario" placeholder="Nick*">
                <input type="email" name="email"  placeholder="Correo Electronico*">
                <input type="text" name="nombre"  placeholder="Nombre*">
                <input type="text" name="apellidos"  placeholder="Apellidos*">
                <input type="password" name="password" placeholder="Contraseña*">
                <input type="hidden" name="rol" value="Chollista">
                <input type="number" name="edad" placeholder="Edad*">

                <input type="submit" name="Alta" value="Continuar" >
            </form>

        </section>
        <footer>

            <div class="footer-left">

                <p class="footer-links">
                    <a href="index.jsp">Inicio</a> -
                    <a href="AvisoLegal.html">Aviso Legal</a> - 
                    <a href="https://www.prisa.com/es/info/politica-de-cookies-publi">Política de Cookies</a> - 
                    <a href="mailto:cholloslocos">Contacto</a> 
                </p>

                <p class="autores">Ezequiel Project &copy; 2020</p>

                <div class="footer-icons"> 
                    <a href="#"><i class="fa fa-facebook"></i></a> 
                    <a href="#"><i class="fa fa-twitter"></i></a> 
                    <a href="#"><i class="fa fa-linkedin"></i></a> 
                    <a href="#"><i class="fa fa-github"></i></a>
                </div>

            </div>

            <div class="footer-right">
                <p>Contáctanos:</p>
                <form action="index.jsp" method="post">
                    <input type="text" name="email" placeholder="Email" />
                    <textarea name="message" placeholder="Mensaje"></textarea>
                    <button>Enviar</button>
                </form>
            </div>

        </footer>
    </body>
</html>
