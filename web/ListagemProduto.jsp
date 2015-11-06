<%-- 
    Document   : ListagemProduto
    Created on : 06/05/2015, 15:09:52
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Estilos.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
         <script>
            function excluiProduto(id) {
                if (confirm('Confirma Exclusão?'))
                {
                    location.href = "ServletProduto?operacao=excluir&id=" + id;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="Container.jsp" %>
        
        <div class ="conteudo">
            <div id="listProd">
        
        

                <h1>Consulta de Produtos</h1> <br>
        <table class = tabelaProds>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Preço</th>            
                <th>Ações</th>            
            </tr>

            <c:forEach items="${lista}" var="produto">
                <tr>
                    <td> ${produto.id}  </td>
                    <td> ${produto.nome} </td>
                    <td> 
                        <fmt:formatNumber type="currency" value="${produto.preco} " var="preco"/>
                        ${preco}
                    </td>                
                    <td>
                        
                        <a href="ServletProduto?operacao=alteracao&id=${produto.id}"><img class="imgListProd" src="Img/icon_edita1.png"/></a>
                        &nbsp;&nbsp;
                        <a href="javascript:excluiProduto(${produto.id})"><img class="imgListProd" src="Img/icon_lixeira.png"/></a>        
                        
                    </td>
                </tr>
            </c:forEach>

        </table>
        <br>
        <br>
        <form name="novoForm" method="post"  action="ServletProduto?operacao=exibe-cadastro">
                    <input type="submit" value="Novo Cadastro">            
                </form>
            </div>
        </div>
    </body>
</html>
