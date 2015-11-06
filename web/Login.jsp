<%-- 
    Document   : Login
    Created on : 29/04/2015, 13:55:33
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Estilos.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>


    </head>
    <body>

        <form id="formLogin" name="login" method="post" action="ServletLogin?operacao=login" >
            <fieldset class="field">
                <legend class="legenda"> Login  </legend> 
                <p></p>

                <img id="imgLogin" src="Img/img_logo.png" alt=""/>
                <label for="email">Email </label>
                <input type="email"  name="email">

                <br>
                <label for="senha">Senha </label>
                <input type="password"  name="senha">
                <br>
                <%
                    if (request.getParameter("erro") != null) {
                        out.print("<BR><br><span class='erro'>Usuário/senha inválidos! </span>");
                    } else if (request.getParameter("facalogin") != null) {
                        out.print("<BR><br><span class='erro'>É necessário se autenticar primeiro no site! </span>");
                    }
                %>
                <br>
                <br>
                <input type="submit" value="Logar">
                <input type="button" value="Voltar"  onclick="javascript:history.back();" >

            </fieldset>                       

        </form>

        <br>



    </body>
</html>
