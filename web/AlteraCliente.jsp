<%-- 
    Document   : AlteraCliente
    Created on : 27/04/2015, 07:36:34
    Author     : Programador
--%>

<%@page import="DAO.Metodos"%>
<%@page import="DAO.ClienteDAO"%>
<%@page import="VO.ClienteVO"%>
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

        <%
            int id = Integer.parseInt(request.getParameter("id"));
            ClienteVO cliente = ClienteDAO.consulta(id);

        %>


        <div class="formCliente">
            <form class="conteudo" name="formCadCliente"  id="formCadCliente" action="ServletCliente?operacao=A" method="post" 
                  <!--input type="hidden" name="operacao" id="operacao" value="A"-->

                <fieldset class="field"> 
                    <legend class="legenda">Alteração cadastro </legend>

                    <ul>
                       <li>

                            <Label for="codigo"> Código </label>
                            <input type="hidden" name="codigo"  id="codigo" readonly class="textbox-sem-edicao" 
                                   value="<% out.write(request.getParameter("id"));%>" required pattern="[a-zA-Z\s]+$" /> <br>
                        </li>
                        <li>

                            <Label for="nome"> Nome </label>
                            <input type="text" name="nome"  id="nome" size="30"
                                   value="<%=cliente.getNome()%>" required >
                            <span class="erro">${erros["nome"]}</span><br>
                        </li>
                        <li>

                            <Label for="dataNasci"> Data de Nascimento </label>
                            <input type="text" name="dataNasci"  id="data"
                                   value="<%= Metodos.dateToString(cliente.getDataNascimento())%>" required  >
                            <span class="erro">${erros["dataNasci"]}</span><br>
                        </li>
                        <li>
                            <Label for="cpf"> CPF </label>
                            <input type="text" name="cpf"  id="cpf" 
                                   value="<%=cliente.getCpf()%>" required  >
                            <span class="erro">${erros["cpf"]}</span><br>
                        </li>
                        <li>
                            <Label for="telefone"> Telefone </label>
                            <input type="text" name="telefone"  id="telefone" 
                                   value="<%=cliente.getTelefone()%>" required >
                            <span class="erro">${erros["telefone"]}</span><br>
                        </li>
                        <li>
                            <Label for="endereco"> Endereço </label>
                            <input type="text" name="endereco"  id="endereco"
                                   value="<%=cliente.getEndereco()%>" required >
                            <span class="erro">${erros["endereco"]}</span><br>
                        </li>
                        <li>
                            <Label for="email"> Email </label>
                            <input type="email" name="email"  id="email" 
                                   value="<%=cliente.getEmail()%>" required >
                            <span class="erro">${erros["email"]}</span><br>
                        </li>
                        <li>
                            <label for="senha">Senha </label>
                            <input type="password"  name="senha" id="senha"
                                   value="<%=cliente.getSenha()%>"required > 
                            <span class="erro">${erros["senha"]}</span><br>
                        </li>
                    </ul>
                        
                    <p></p>
                    
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
                </fieldset>
            </form>
        </div>
    </body>
</html>
