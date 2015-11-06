<%-- 
    Document   : CadastroProduto
    Created on : 06/05/2015, 14:20:07
    Author     : Programador
--%>

<%@page import="DAO.Metodos"%>
<%@page import="VO.ProdutoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Estilos.css" rel="stylesheet" type="text/css"/>
        <script src="JS/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="JS/jquery.price_format.js" type="text/javascript"></script>
        <title>JSP Page</title>
        
        
        
    </head>
    <body>
        <%@include file="Container.jsp" %>
        <%
            String id, nome, descricao, preco, categoriaId;

            if (request.getAttribute("produto") != null) {
                ProdutoVO produto = (ProdutoVO) request.getAttribute("produto");
                id = Integer.toString(produto.getId());
                nome = produto.getNome();
                preco = Metodos.doubleBrasil(produto.getPreco());
                descricao = produto.getDescricao();
                categoriaId = Integer.toString(produto.getCategoriaId());
            } else {
                id = Metodos.stringNotNull(request.getParameter("id"));
                nome = Metodos.stringNotNull(request.getParameter("nome"));
                preco = Metodos.stringNotNull(request.getParameter("preco"));
                descricao = Metodos.stringNotNull(request.getParameter("descricao"));
                categoriaId = Metodos.stringNotNull(request.getParameter("categoriaId"));
            }
        %>   

        <form class = conteudo name="formCadProduto" id="formCadProduto" action="ServletProduto?operacao=Salvar" method="post"> 
            <fieldset class="fieldProduto">
                <legend class="legenda"> Dados do Produto </legend>
                <br><br>
                <label for="id"> Código </label>
                <input type="text" name="id" id="id" readonly class="textbox-sem-edicao" value="<%=id%>">                
                <br>

                <label for="nome"> Nome </label>
                <input type="text" name="nome" id="nome" value="<%=nome%>" size="60" required placeholder="Informe o nome do produto" pattern="[a-zA-Z\s-çãàáÃÀÁãéêÉíÍóÓúÚñÑÇ]+$"/> 
                <span class="erro"> ${erros["nome"]} </span>
                <br>

                <label for="preco"> Preço </label>
                <input type="text" name="preco"  id="preco" value="<%=preco%>" placeholder="Preço do Produto" required> 
                <span class="erro">${erros["preco"]}</span>
                <br>

                <label for="descricao"> Descricao do Produto </label>
                <textarea name="descricao" id="descricao" rows="10" cols="83" required><%=descricao%></textarea> 
                <span class="erro">${erros["descricao"]}</span>
                <br>

                <label for="catagoriaId"> Categoria </label>
                <select name="categoriaId" id="categoriaId" required >
                    <option></option>
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.id}" ${categoria.id == produto.categoriaId ? 'selected="selected"' : ''}>${categoria.nome}</option>
                    </c:forEach>                    
                </select>                
                <br>
                <script type="text/javascript">
                jQuery(function($){
                $('preco').priceFormat({
                    prefix: 'R$',
                    centsSeparator: ',',
                    thousandsSeparator: '.'
                });
                });
                 
    
                 </script>

                
                 <c:if test="${param.operacao == 'A'}">                
                    <label for="alterarImagens"> Alterar as imagens </label>
                    <input type="checkbox" name="alterarImagens" id="alterarImagens">Alterar imagem 
                    <br>
                </c:if> 


                <br>
                <input type="submit" value="Continuar" class="linkAcao">                              
                &nbsp; &nbsp;                
                <input type="button" value="Voltar" class="linkAcao"  onclick="javascript:location.href = 'ServletProduto?operacao=L'">                                              
                <br>
                <br>
            </fieldset>
        </form>

    </body>
</html>
