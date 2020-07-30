<%-- 
    Document   : Administrador
    Created on : 10-mar-2020, 23:51:06
    Author     : Ezequiel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/reset.css">
        <link href="CSSBS/bootstrap.css" rel="stylesheet" media="screen">
        <link href="CSS/administrador.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="CSS/footer.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <script type="text/javascript" src="JS/jquery.min.js"></script>
        <script type="text/javascript" src="JS/tab.js"></script>
        <script>
            $(function () {
                $("#busq,#busq2,#busq3").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $(".table tbody tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>

        <title>Administrador</title>
    </head>
    <body>
        <jsp:useBean id="bAdmin" scope="request" class="logicaNegocio.beanAdministrador"/>
        <jsp:setProperty name="bAdmin" property="*"/>

        <c:if test="${!empty param.idUsuario}">
            <jsp:useBean id="nuevoUsu" scope="request" class="DTO.Usuario">
                <jsp:setProperty name="nuevoUsu" property="*"/>
            </jsp:useBean>
            <jsp:include page="RegistrarUsuario"/>
        </c:if>

        <c:if test="${!empty param.codUsu}">
            ${bAdmin.eliminarUsuario()}
        </c:if>

        <c:if test="${!empty param.codChollo}">
            ${bAdmin.elminarChollo()}
        </c:if>

        <c:if test="${!empty param.mensajeContacto}">
            ${bAdmin.eliminarMensaje()}
        </c:if>

        <c:if test="${!empty param.codNot}">

            <jsp:forward page="EditarNoticia.jsp"/>
        </c:if>

        <header class="row mb-5">
            <div class="col text-center">
                <a href="index.jsp"><img  src="IMAGENES/logo.png"></a>
            </div>
        </header>

        <ul class="tabs ml-5">
            <li><a href="#tab1">Alta Usuario</a></li>
            <li><a href="#tab2">Usuarios</a></li>
            <li><a href="#tab3">Chollos</a></li>
            <li><a href="#tab4">Mensajes</a></li>
        </ul>

        <div class="tab_container ml-5 mb-3">
            <div class="tab_content" id="tab1">
                <form class=" m-1 p-2 m-md-5 p-md-3" action="#" id="editor">

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="idUsuario">IdUsuario:</label>
                        <input class="form-control" type="text" name="idUsuario" id="idUsuario" placeholder="Nick*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="email">Email</label>
                        <input class="form-control" type="email" name="email" id="email" placeholder="Correo Electronico*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="apellidos">Apellidos:</label>
                        <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="pass">Contraseña</label>
                        <input class="form-control" type="password" name="password" id="pass" placeholder="Contraseña*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="rol">Rol:</label>
                        <select name="rol" id="rol">
                            <option value="Chollista">Chollista</option>
                            <option value="Adminstrador">Admin</option>
                        </select>
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <label for="edad">Edad:</label>
                        <input class="form-control" type="number" name="edad" id="edad" placeholder="Edad*">
                    </div>

                    <div class="form-group col col-md-8 col-lg-6 offset-md-2 offset-lg-1">
                        <input class="btn btn-info" type="submit" value="Alta Usuario">
                    </div>
                </form>
            </div>

            <div class="tab_content" id="tab2">
                <div class="tabla col-lg-12 table-responsive">
                    <form action="Administrador.jsp" class="mb-3">
                        <input id="busq" type="text" placeholder="Buscar..">
                    </form>
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="thead-light">
                            <tr>
                                <th>IdUsuario</th>
                                <th>Email</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Password</th>
                                <th>Rol</th>
                                <th>Edad</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usu" items="${bAdmin.obtenerUsuarios()}">
                                <tr>
                                    <td>${usu.idUsuario}</td>
                                    <td>${usu.email}</td>
                                    <td>${usu.nombre}</td>
                                    <td>${usu.apellidos}</td>
                                    <td>${usu.password}</td>
                                    <td>${usu.rol}</td>
                                    <td>${usu.edad}</td>
                                    <td>
                                        <form class="form-inline">
                                            <input type="submit" value="Eliminar" class="btn-danger rounded col-12 col-lg-auto mb-2 mb-lg-0">
                                            <input type="hidden" name="codUsu" value="${usu.idUsuario}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="tab_content" id="tab3">
                <div class="tabla col-lg-12 table-responsive">
                    <form action="Administrador.jsp" class="mb-3">
                        <input id="busq2" type="text" placeholder="Buscar..">
                    </form>
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="thead-light">
                            <tr>
                                <th>Título</th>
                                <th>Sección</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="chollos" items="${bAdmin.obtenerChollos()}">
                                <tr>
                                    <td>${chollos.titulo}</td>
                                    <td>${chollos.seccion}</td>
                                    <td>
                                        <form class="form-inline">
                                            <input type="submit" value="Eliminar" class="btn-danger rounded col-12 col-lg-auto mb-2 mb-lg-0">
                                            <input type="hidden" name="codChollo" value="${chollos.codChollo}">
                                        </form>
                                    </td>
                                    <td>
                                        <form class="form-inline" action="EditarChollo.jsp">
                                            <input type="submit" value="Editar" class="btn-primary rounded col-12 col-lg-auto mb-2 mb-lg-0">
                                            <input type="hidden" name="codChollo" value="${chollos.codChollo}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="tab_content" id="tab4">
                <div class="tabla col-lg-12 table-responsive">
                    <form action="Administrador.jsp" class="mb-3">
                        <input id="busq3" type="text" placeholder="Buscar...">
                    </form>
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="thead-light">
                            <tr>
                                <th>Email</th>
                                <th>Mensaje</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contacto" items="${bAdmin.obtenerMensajes()}">
                                <tr>
                                    <td>${contacto.email}</td>
                                    <td>${contacto.mensaje}</td>
                                    <td>
                                        <form class="form-inline">
                                            <input type="submit" value="Eliminar" class="btn-danger rounded col-12 col-lg-auto mb-2 mb-lg-0">
                                            <input type="hidden" name="mensajeContacto" value="${contacto.mensaje}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
    </body>
</html>
