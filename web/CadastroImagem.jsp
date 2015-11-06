<%-- 
    Document   : CadastroImagem
    Created on : 06/05/2015, 15:10:29
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
        <form class ="conteudo" name="formCadProduto" id="formCadProduto" action="ServletProduto?operacao=salvarComImagem" 
              method="post" enctype="multipart/form-data"> 

            <fieldset class="fieldImagem"> 
                <legend class ="legenda">Seleção de imagens</legend>
                <br><br>
                <label for="nome"> Nome </label>
                <input type="text" name="nome"  value="${produto.nome}" readonly class="textbox-sem-edicao">                 
                <br> <br>
                
                <label for="imagem1"> Imagem 1 </label>
                <input type="file" name="imagem1" id="imagem1" required>
                <br><br>

                <label for="imagem2"> Imagem 2 </label>
                <input type="file" name="imagem2" id="imagem2" required>
                <br><br>

                <label for="imagem3"> Imagem 3 </label>
                <input type="file" name="imagem3" id="imagem3" required>
                <br><br><br>

                <input type="submit" value="Gravar" class="linkAcao">                              
                &nbsp; &nbsp;    
                <input type="button" value="Voltar" class="linkAcao"  onclick="javascript:history.back()">                                              
                <br>
                <br>
            </fieldset>
        </form>       
    </body>
</html>
