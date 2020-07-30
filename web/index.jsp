<%-- 
    Document   : index
    Created on : 27-feb-2020, 15:38:38
    Author     : Ezequiel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChollosLocos</title>
        <link rel="stylesheet" type="text/css" href="CSS/reset.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css">
        <link rel="stylesheet" type="text/css" href="CSS/footer.css">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>

    <body>

        <jsp:useBean id="coment" scope="request" class="logicaNegocio.beanComentarios"/>

        <c:if test="${!empty param.idUsuario}">
            <jsp:include page="ComprobarUsuario"/>
        </c:if>

        <c:if test="${!empty param.cerrar}">
            <jsp:include page="CerrarSesion"/>
            <c:redirect url="index.jsp"/>
        </c:if>

        <c:if test="${!empty param.like}">
            <jsp:include page="DarLikes"/>
        </c:if>
        <c:if test="${!empty param.cod}">
            <jsp:include page="MostrarCholloSeleccionado"/>
        </c:if>

        <c:if test="${!empty param.comentario}">
            <jsp:include page="NuevoComentario"/>
        </c:if>

        <c:if test="${!empty param.enviarContacto}">
            <jsp:include page="NuevoContacto"/>
        </c:if>

        <jsp:include page="MostrarChollos"/>

        <header>
            <a href="index.jsp"><img src="IMAGENES/logo.png" id="logo"></a>

            <c:choose>
                <c:when test="${!empty sessionScope.usu}">
                    <article id="cerrar">
                        <div class="usuario">
                            <img src="IMAGENES/user-symbol.png">
                            <span> ${sessionScope.usu.getIdUsuario()}</span>
                        </div>
                        <div id="cerrarSesion">
                            <c:if test="${!empty sessionScope.chollista}">
                                <a href="SubirChollo.jsp">Subir Chollo</a>
                                <a href="EditarChollo.jsp">Editar Chollo</a>
                            </c:if>

                            <c:if test="${!empty sessionScope.admin}">
                                <a href="Administrador.jsp">Perfil</a>
                            </c:if>

                            <a href="index.jsp?cerrar=si">Cerrar Sesión</a>
                        </div>
                    </article>
                </c:when>
                <c:otherwise>
                    <section id="login">
                        <a href="#" id="inicioSesion">Iniciar Sesión</a>
                        <p id="inicio">Iniciar Sesión</p>

                        <div id="logueo">
                            <form id="log">
                                <input type="text" name="idUsuario" id="idUsuario" placeholder="Nombre Usuario">
                                <input type="password" name="pass" id="pass" placeholder="Contraseña">
                                <input type="submit" value="Entrar" id="entrar">
                                <h3 id="mensaje">${requestScope.mensaje}</h3>
                                <label>No estás registrado, <a id="reg" href="Registro.jsp">hazlo aquí</a></label>
                            </form>
                        </div>
                    </section>
                </c:otherwise>
            </c:choose>

        </header>

        <nav>
            <ul>
                <li><a href="index.jsp">Todos los Chollos</a></li>
                <li><a href="index.jsp?seccion=Tecnología">Tecnología</a></li>
                <li><a href="index.jsp?seccion=Gaming">Gaming</a></li>
                <li><a href="index.jsp?seccion=Deportes">Deportes</a></li>
                <li><a href="index.jsp?seccion=Hogar">Hogar</a></li>
                <li><a href="index.jsp?seccion=Salud y Belleza">Salud y Belleza</a></li>
                <li><a href="index.jsp?seccion=Coches y Motos">Coches y Motos</a></li>
                <li><a href="index.jsp?seccion=Otros">Otros</a></li>
            </ul>
        </nav>

        <section id="chollos">
            <c:choose>
                <c:when test="${!empty requestScope.chollo}">
                    <article id="cholloSelect">

                        <figure>
                            <img src="IMAGENES/${requestScope.chollo.foto}">
                        </figure>
                        <h1>${requestScope.chollo.titulo}</h1>
                        <div id="likes">
                            <c:if test="${!empty sessionScope.usu.getIdUsuario()}">
                                <p>¿Te ha gustado este chollo?</p>
                                <c:choose>
                                    <c:when test="${requestScope.likeSN == 'SI'}">
                                        <form class="formlike" action="index.jsp">
                                            <input type="hidden" name="cod" value="${requestScope.chollo.codChollo}"/>
                                            <input class="likeSi" type="submit" name="like" value="SI"/>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form class="formlike" action="index.jsp">
                                            <input type="hidden" name="cod" value="${requestScope.chollo.codChollo}"/>
                                            <input class="likeNo" type="submit" name="like" value="NO"/>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                        <p class="act">Ahora: ${chollo.precAct}€</p> <p class="ant">  Antes: ${chollo.precAnt}€</p>
                        <div class="cuerpo">
                            ${requestScope.chollo.cuerpo}
                        </div>
                        <h2 class="h2">Comentarios</h2>
                        <c:if test="${!empty sessionScope.usu}">
                            <div id="comentarios">
                                <form action="index.jsp">
                                    <textarea cols="70" rows="6" placeholder="Escriba aquí su comentario..." name="comentario"></textarea>
                                    <input type="hidden" name="codChollo" value="${requestScope.chollo.codChollo}">
                                    <input class="btnComentario" type="submit" value="Enviar Comentario">
                                </form>       
                            </div>
                        </c:if>
                        <c:forEach var="listaComent" items="${coment.obtenerComentarios(requestScope.chollo.codChollo)}">
                            <div class="cajonComentarios">
                                <span class="usuComent">${listaComent.idUsuario.idUsuario}: </span>
                                <span class="textComent">${listaComent.comentario}</span>
                            </div>
                        </c:forEach>
                    </article>
                </c:when>

                <c:otherwise>

                    <c:forEach var="chollos" items="${requestScope.listaChollos}" varStatus="estado">

                        <c:if test="${estado.count>0}">
                            <article id="card">
                                <a href="index.jsp?cod=${chollos.codChollo}"><img src="IMAGENES/${chollos.foto}">
                                    <a class="tchollo" href="index.jsp?cod=${chollos.codChollo}"><h2>${chollos.titulo}</h2></a>
                                    <span class="act">${chollos.precAct}€</span><span class="ant">${chollos.precAnt}€</span>
                                    <a class="enlace" href="${chollos.enlace}"><input type="submit" value="Ir al Chollo"/></a>
                            </article>
                        </c:if>

                    </c:forEach>

                </c:otherwise>

            </c:choose>
        </section>

        <section id="populares">
            <h1 class="botonPopulares">Los mas populares</h1>
            <c:forEach var="chollos" items="${requestScope.listaChollosPopulares}" varStatus="estado">
                <c:if test="${estado.count<=3}">
                    <article id="card">
                        <a  href="index.jsp?cod=${chollos.codChollo}"><img src="IMAGENES/${chollos.foto}">
                            <a class="tchollo" href="index.jsp?cod=${chollos.codChollo}"><h2>${chollos.titulo}</h2></a>
                            <span class="act">${chollos.precAct}€</span><span class="ant">${chollos.precAnt}€</span>
                            <a class="enlace" href="${chollos.enlace}"><input type="submit" value="Ir al Chollo"/></a>
                    </article>
                </c:if>
            </c:forEach>
        </section>
        
        <footer>

            <div class="footer-left">

                <p class="footer-links">
                    <a href="index.jsp">Inicio</a> -
                    <a href="AvisoLegal.html">Aviso Legal</a> - 
                    <a href="https://www.prisa.com/es/info/politica-de-cookies-publi">Política de Cookies</a> - 
                    <a href="mailto:cholloslocos@gmail.com">Contacto</a> 
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
                    <input type="submit" name="enviarContacto" value="Enviar">
                </form>
            </div>

        </footer>


        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="JS/jquery.min.js"><\/script>')</script>
        <script src="JS/menu.js"></script>

    </body>

</html>
