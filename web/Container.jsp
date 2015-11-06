
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>        
<meta charset="UTF-8">
<link href="Estilos.css" rel="stylesheet" type="text/css"/>


<%
    if (session.getAttribute("logado") == null || session.getAttribute("logado") == "") {
        session.setAttribute("logado", false);
    }

%>

<body> 
    <section class="conteudo">

        <section class="cabecalho">
            <img src="Img/img_logo.png" alt="" class="logotipo"/>
            <div class="login"> 
                <%                    if ((request.getSession().getAttribute("logado").equals(false))) {
                %>

                <img class="imgLink" src="Img/img_usuario.png" alt=""/> &nbsp; <a href="Login.jsp">Entrar</a>  /                     
                <a href="IncluiCliente.jsp">Cadastrar</a><br><br>
                <img class="imgLink" src="Img/img_cesta.png" alt=""/> &nbsp;<a href="ServletCarrinho?operacao=exibir-carrinho">Meus Pedidos</a><br><br>

                <%
                } else {
                %>

                <img class="imgLink" src="Img/img_usuario.png" alt=""/> &nbsp;<a href="ServletLogin?limpar=true">Sair</a><br><br>

                <%
                    }
                %>

                <%
                    if ((request.getSession().getAttribute("admin") != null) && request.getSession().getAttribute("admin").equals(true)) {
                %>
                <img class="imgLink" src="Img/icon_pesq.png" alt=""/> &nbsp;<a href="ListagemCliente.jsp">Clientes</a><br><br>
                <img class="imgLink" src="Img/img_cesta.png" alt=""/> &nbsp;<a href="#">Pedidos</a><br><br>
                <img class="imgLink" src="Img/img_add.png" alt=""/> &nbsp;<a href="ServletProduto?operacao=L">Produtos</a><br><br>
                <%
                } else if ((request.getSession().getAttribute("usuario") != null) && request.getSession().getAttribute("usuario").equals(true)) {
                %>
                <img class="imgLink" src="Img/img_cesta.png" alt=""/> &nbsp;<a href="#">Meus Pedidos</a><br><br>
                <%
                    }
                %>
            </div>
        </section>

        <nav>
            <div id='menuWrapper'>
                <div class='menuPrincipal'>
                    <ul>
                        <li><a href="Index.jsp">Home</a></li>
                        <li><a href="#">Nossos Produtos</a>
                            <ul>
                                <li><a href="ServletVitrine?filtroCategoria=1&operacao=L&filtroQtde=30&filtroNome=">Pães</a></li>
                                <li><a href="ServletVitrine?filtroCategoria=2&operacao=L&filtroQtde=30&filtroNome=">Bebidas</a></li>
                                <li><a href="ServletVitrine?filtroCategoria=3&operacao=L&filtroQtde=30&filtroNome=">Doces</a></li>

                            </ul>
                        </li>
                        <li><a href="#">A Delicatê</a></li>
                        <li><a href="Contato.jsp">Contato</a></li>
                    </ul> 
                    
                    


                    <form name="formfiltro" method="post" action="ServletVitrine?operacao=L" id="search">
                        <input id="filtroNome" type="text" name="filtroNome"  size="25" value="${param.filtroNome}" required placeholder="Buscar..."/> 
                        &nbsp;
                        <input type="submit" value="" id="search-btn"/>
                    </form>


                    <!--form action="ServletVitrine?operacao=L" id='search' method='get' name='searchForm' style='display:inline;'>
                        <input id='search-box' name='q' onblur='if (this.value == & quot; & quot; ) this.value = & quot; Buscar... & quot; ;' onfocus='if (this.value == & quot; Buscar... & quot; ) this.value = & quot; & quot; ;' size='28' type='text' required  placeholder="Buscar.."/>
                        <input type="submit" value="" id="search-btn"/>
                    </form-->
                </div>
            </div>
        </nav>

    </section>

</body>






