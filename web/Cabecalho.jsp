<%-- 
    Document   : Cabecalho
    Created on : 29/04/2015, 15:49:31
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="ServletLogin?limpar=true">Sair</a>

<%
            if ((session.getAttribute("logado") == null) || 
                ((Boolean)session.getAttribute("logado") == false)) {
                response.sendRedirect("Login.jsp?facalogin=true");
                return;
            }
%>
    </body>
</html>
