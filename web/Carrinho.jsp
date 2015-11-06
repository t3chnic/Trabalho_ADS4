<%-- 
    Document   : Carrinho
    Created on : 19/05/2015, 16:21:20
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
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
            <div id="pagCarrinho">
                <h1> Carrinho </h1>
                
                <p class="escritoCarrinho">
                <c:if test="${empty carrinho }">
                    Seu carrinho está vazio  <br>
                </c:if>
                </p>
                        
                <br><br><br><br>
                <div class="espacoTab">
                <table class="tabelaCarrinho" border="1"> 
                    <tr>
                        <th>Código </th>
                        <th>Produto </th>
                        <th>Valor unitário</th>
                        <th>Quantidade </th>
                        <th>Valor Total </th>
                        <th>Ações</th>
                    </tr>

                    <c:forEach items="${carrinho}" var="item">
                        <tr>
                            <td>${item.produto.id}</td>
                            <td>${item.produto.nome}</td> 
                            <td><fmt:formatNumber type="currency" value="${item.produto.getPreco()}"/> </td> 
                            <td>${item.quantidade}</td> 
                            <td><fmt:formatNumber type="currency" value="${item.produto.getPreco()*item.quantidade}"/> </td>               
                            <td><a href="ServletCarrinho?operacao=excluir&id=${item.produto.id}"><img class="imgListProd" src="Img/icon_lixeira.png"/></a> </td>               
                        </tr>
                    </c:forEach>
                        
                </table>
                    </div><br><br>
                
                <p class="totalCompra">
                Total da compra: <fmt:formatNumber type="currency" value="${total}" />  
                </p>


                <br><br><br><br>

                <form  name="frmFinaliza" action="">                
                    <input id="btnFimCompra" type="submit" value="Finalizar compra" >
                </form>
                <br><br><br>
            </div>
        </div>
    </body>
</html>
