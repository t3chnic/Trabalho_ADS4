<%-- 
    Document   : DetalhesProduto
    Created on : 06/05/2015, 16:15:57
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
        <div class="conteudo">
            
                <div id="descProd">
                    
                    <form name="frmComprar" method="post"  action="ServletCarrinho?operacao=adiciona-no-carrinho">
                            <input type="hidden"  name="id" value="${produto.id}">
                            <input id="btnAddCarrinho" type="submit" value="Adicionar no carrinho" class="linkAcao">
                    </form><br><br>

                    <h1> ${produto.nome} </h1>
                    
                    
                    <div id="categoria">
                        Categoria: ${produto.nomeCategoria} <br/>             

                    </div>

                    <div id="divImagem1">   
                        <img src="ImgProdutos/${produto.imagem1}" id="img1" alt="Imagem do filme">
                    </div>
                    <br>
                    <div id="divDesc">Preço: 
                        <fmt:formatNumber type="currency" var="precoFormatado" value="${produto.preco}"/>
                        ${precoFormatado} <br>                       <br>
                        Descrição do produto:
                        ${produto.descricao} 
                        </div>
                        
                        
                        <br><br><br><br><br><br>
                        <div>
                            &nbsp;<p id="outrasImgs"> Outras imagens</p>    
                            <img src="ImgProdutos/${produto.imagem2}"  id="img2" alt="Imagem do filme">               
                            <img src="ImgProdutos/${produto.imagem3}"  id="img3" alt="Imagem do filme">
                        </div>

                        <br>
                        
                    </div>
            
        </div>
    </body>
</html>
