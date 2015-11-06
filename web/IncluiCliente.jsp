<%-- 
    Document   : IncluiCliente
    Created on : 27/04/2015, 07:37:04
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Estilos.css" rel="stylesheet" type="text/css"/>
        <script src="JS/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="JS/jquery.maskedinput.js" type="text/javascript"></script>
    

        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="Container.jsp" %>
        <div class="formCliente">
            <form class="conteudo" name="formCadCliente"  id="formCadCliente" action="ServletCliente?operacao=I" method="post" >
                <!--input type="hidden" name="operacao" value="I"-->


                <fieldset class="field"> 
                    <legend class="legenda">Cadastro</legend>

                    <ul>
                        <li>
                            <Label for="codigo"> Código </label>
                            <input type="hidden" name="codigo"  id="codigo" readonly class="textbox-sem-edicao"  ><br>
                        </li>                
                        <li>
                            <Label for="nome"> Nome </label>
                            <input type="text" name="nome"  id="nome" size="30"  placeholder="nome Completo"  value="${param["nome"]}" required pattern="[a-zA-Z\s-çãàáÃÀÁãéÉíÍóÓúÚñÑÇ]+$" /> <br>
                            <span class="erro">${erros["nome"]}</span>
                        </li>                   
                        <li>
                            <Label for="dataNasci"> Data de Nascimento </label>
                            <input type="text" name="dataNasci" id="dataNasci" value="${param["dataNasci"]}" required><br>
                            <span class="erro">${erros["dataNasci"]}</span>
                        </li>      
                        <li>
                            <Label for="cpf"> CPF </label>
                            <input type="text" name="cpf"  id="cpf" value="${param["cpf"]}" required><br>
                            <span class="erro">${erros["cpf"]}</span>
                        </li>
                        <li>
                            <Label for="telefone"> Telefone </label>
                            <input type="text" name="telefone"  id="telefone" value="${param["telefone"]}" required><br> 
                            <span class="erro">${erros["telefone"]}</span>
                        </li>                   
                        <li>
                            <Label for="endereco"> Endereço </label>
                            <input type="text" name="endereco" id="endereco" value="${param["endereco"]}" required><br>
                            <span class="erro">${erros["endereco"]}</span>
                        </li>                   
                        <li>
                            <Label for="email"> Email </label>
                            <input type="email" name="email"  id="email" value="${param["email"]}" required><br>
                            <span class="erro">${erros["email"]}</span>
                        </li>
                        <li>
                            <label for="senha">Senha </label>
                            <input type="password"  name="senha" id="senha" value="${param["senha"]}" required><br>
                            <span class="erro">${erros["senha"]}</span>
                        </li>
                    </ul>
                    
                   
                    
                    <p> </p> 

                    <script>
                        jQuery(function ($) {

                            $("#dataNasci").mask("99/99/9999");
                            $("#cpf").mask("999.999.999-99");
                            $("#telefone").mask("(99) 9999-9999");
                        });
                    </script>


                    <input type="submit" value="Salvar">
                    &nbsp;&nbsp;&nbsp;

                    <input type="button" value="Voltar"  onclick="javascript:history.back();" >

                    <p> </p>
                </fieldset>

            </form>
        </div>

    </body>
</html>
