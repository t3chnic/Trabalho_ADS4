<%-- 
    Document   : Contato
    Created on : 29/04/2015, 11:51:06
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



    <%@include file="Container.jsp" %> 
    <br><br>
    <div class="conteudo">


        <form class="form" name="contato-form" id="contato-form" method="post" action="">

            <fieldset class="field"> 
                <legend class="legenda">Telefones </legend>

                <P>
                    (11)41234567<br>
                    (11)41234568
                </p>

            </fieldset>

            <fieldset class="field">

                <legend class="legenda">Email </legend>
                <P>   
                    padariadelicate@yahoo.com.br
                </p>
            </fieldset>   
            <fieldset class="field">
                <legend class="legenda">Mensagens </legend>
                <p>  Nos mande uma mensagem por aqui </p>

                <ul>
                    <li>
                        <label for="nome">Nome </label>
                        <input type="text" name="nome"  id="nome"  maxlength="50" size="30" required  placeholder="Digite aqui o seu nome">

                    </li>

                    <li>
                        <label for="email">Email </label>
                        <input type="text" name="email"  id="email"  maxlength="50" size="30" required  placeholder="Digite aqui o seu email">

                    </li>

                    <li>
                        <label for="assunto">Assunto </label>
                        <input type="text" name="assunto"  id="assunto"  maxlength="50" size="30">

                    </li>

                    <li>
                        <label for="mensagem">Mensagem </label>
                        <textarea class="area" name="mensagem"   id="mensagem"  rows="5"  cols="40"> </textarea>

                    </li>

                </ul>
                <p> </p>

                <input name="btnEnviar"  type="button" value="Enviar"> &nbsp; &nbsp; &nbsp;
                <input type="button" value="Voltar"  onclick="javascript:history.back();" >
                <p> </p>


            </fieldset>
        </form>
    </div>

</body>
</html>
