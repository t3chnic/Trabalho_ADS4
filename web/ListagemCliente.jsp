<%-- 
    Document   : ListagemCliente
    Created on : 27/04/2015, 07:37:19
    Author     : Programador
--%>

<%@page import="DAO.Metodos"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ClienteDAO"%>
<%@page import="VO.ClienteVO"%>
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

        <div class="conteudo">
            <div id="consultaCli">
                <h1>Consulta de Clientes</h1>
                <table class="tabelaCliente">
                    <tr>
                        <th> Código </th>
                        <th> Nome </th>
                        <th> Data de Nascimento </th>
                        <th> CPF </th>
                        <th> Telefone </th>
                        <th> Endereço </th>
                        <th> Email </th>
                        <th> Ações </th>
                    </tr>        



                    <%                    List<ClienteVO> lista = ClienteDAO.listagem();
                        for (ClienteVO c : lista) {
                            out.write("<TR>");
                            out.write("<TD>" + c.getId() + "</TD>");
                            out.write("<TD>" + c.getNome() + "</TD>");
                            out.write("<TD>" + Metodos.dateToString(c.getDataNascimento()) + "</TD>");
                            out.write("<TD>" + c.getCpf() + "</TD>");
                            out.write("<TD>" + c.getTelefone() + "</TD>");
                            out.write("<TD>" + c.getEndereco() + "</TD>");
                            out.write("<TD>" + c.getEmail() + "</TD>");

                            out.write("<TD><a href='AlteraCliente.jsp?id="
                                    + c.getId() + "'><img src='Img/icon_edita.png'/></a> &nbsp;&nbsp;&nbsp;"
                                    + "<a href='#'  onclick=apaga('" + c.getId() + "')><img src='Img/icon_lixeira.png'/></a></TD>");
                            out.write("</TR>");
                        }

                    %>
                </table>

                <script>
                    function apaga(id)
                    {
                        if (confirm('Confirma Exclusão?'))
                        {
                            location.href = "ServletCliente?operacao=E&id=" + id;
                        }
                    }

                </script>
                <br><br><br>

                <form name="novoForm" method="get"  action="IncluiCliente.jsp">
                    <input type="submit" value="Novo Cadastro">            
                </form>
            </div>
        </div>


    </body>
</html>
