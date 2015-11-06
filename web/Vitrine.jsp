<%-- 
    Document   : Vitrine
    Created on : 21/05/2015, 21:45:07
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
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
         


            
                <ul class="ul-vitrine">   

                    <c:forEach items="${listaProduto}"  var="produto">
                        <li class="li-vitrine">

                            <a href="ServletVitrine?operacao=descricaoProduto&id=${produto.id}">
                                <figure class="figura-vitrine">
                                    <img src="ImgProdutos/${produto.imagem1}" class="imagem-vitrine">
                                    <figcaption>${produto.nome}<br>
                                        <!--${produto.nomeCategoria} <br/-->             
                                        <fmt:formatNumber type="currency" var="precoFormatado" value="${produto.preco}"/>
                                        ${precoFormatado}                      
                                    </figcaption>
                                </figure>
                            </a>
                        </li>            
                    </c:forEach>             
                </ul>
        

    </body>
</html>
