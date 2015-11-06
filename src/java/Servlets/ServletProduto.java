/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.CategoriaDAO;
import DAO.Metodos;
import DAO.ProdutoDAO;
import VO.ProdutoVO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Programador
 */
@MultipartConfig
@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    private static final String PASTA_IMAGENS_PRODUTOS = "/ImgProdutos/";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try {
            request.setCharacterEncoding("UTF-8");
            String operacao = request.getParameter("operacao");
            HashMap<String, String> erros = new HashMap<String, String>();
            HttpSession sessao = request.getSession();

            boolean novoRegistro = request.getParameter("id") != null
                    && request.getParameter("id").length() == 0;

            if (operacao != null && operacao.equalsIgnoreCase("exibe-cadastro")) {                                
                request.setAttribute("categorias", CategoriaDAO.listagem());
                RequestDispatcher dispacher = request.getRequestDispatcher("CadastroProduto.jsp?operacao=I");
                dispacher.forward(request, response);
                return;
            }
            
            if (operacao != null && operacao.equalsIgnoreCase("alteracao")) {

                int id = Integer.parseInt(request.getParameter("id"));
                ProdutoVO produto = ProdutoDAO.consulta(id);
                request.setAttribute("produto", produto);
                request.setAttribute("categorias", CategoriaDAO.listagem());
                RequestDispatcher dispacher = request.getRequestDispatcher("CadastroProduto.jsp?operacao=A");
                dispacher.forward(request, response);

            } 
            else if (operacao == null || operacao.equalsIgnoreCase("L")) {
                exibeListaProduto(request, response);
            } 
            else if (operacao.equalsIgnoreCase("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                apagaImagens(id, request);
                ProdutoDAO.deletar(id);
                exibeListaProduto(request, response);

                
            } else if (operacao.equalsIgnoreCase("salvar")) {
                String id = request.getParameter("id");
                String nome = request.getParameter("nome").trim();
                String preco = request.getParameter("preco");
                String descricao = request.getParameter("descricao").trim();
                String categoriaId = request.getParameter("categoriaId").trim();

                if (nome.length() == 0) {
                    erros.put("nome", "Campo obrigatório");
                }
                if (descricao.length() == 0) {
                    erros.put("descricao", "Campo obrigatório");
                }

                if (!Metodos.doubleValido(preco, 0)) {
                    erros.put("preco", "Preço inválido.");
                }

                if (!Metodos.integerValido(categoriaId, 1)) {
                    erros.put("categoriaId", "Campo obrigatório");
                }

                if (erros.isEmpty() == false) {
                    request.setAttribute("erros", erros);
                    request.setAttribute("categorias", CategoriaDAO.listagem());
                    RequestDispatcher dispacher = request.getRequestDispatcher("CadastroProduto.jsp");
                    dispacher.forward(request, response);
                } else {
                    ProdutoVO produto = new ProdutoVO();

                    if (id.length() > 0) {
                        produto.setId(Integer.parseInt(id));
                    }

                    produto.setNome(nome);
                    produto.setPreco(Metodos.stringToDouble(preco));
                    produto.setDescricao(descricao);
                    produto.setCategoriaId(Integer.parseInt(categoriaId));

                    boolean solicitadoAlterarImagens = request.getParameter("alterarImagens") != null
                            && request.getParameter("alterarImagens").equalsIgnoreCase("on");

                    if (novoRegistro || solicitadoAlterarImagens) {
                        sessao.setAttribute("produtoCon", produto); 
                        request.setAttribute("produto", produto);
                        RequestDispatcher dispacher = request.getRequestDispatcher("CadastroImagem.jsp");
                        dispacher.forward(request, response);
                    } else {
                        
                        ProdutoVO produtoCadastrado = ProdutoDAO.consulta(Integer.parseInt(id));

                        produto.setImagem1(produtoCadastrado.getImagem1());
                        produto.setImagem2(produtoCadastrado.getImagem2());
                        produto.setImagem3(produtoCadastrado.getImagem3());
                        produto.setId(Integer.parseInt(id));
                        ProdutoDAO.alterar(produto);
                        exibeListaProduto(request, response);
                    }
                }
                
            } else if (operacao.equalsIgnoreCase("salvarComImagem")) {

                ProdutoVO produto = (ProdutoVO) sessao.getAttribute("produtoCon");

                if (produto.getId() == 0) {
                    ProdutoDAO.inserir(produto);                    
                }

                produto.setImagem1(criaNomeImagem(1, produto.getId(), request));
                produto.setImagem2(criaNomeImagem(2, produto.getId(), request));
                produto.setImagem3(criaNomeImagem(3, produto.getId(), request));
                ProdutoDAO.alterar(produto);  

                
                salvaImagem("imagem1", produto.getImagem1(), request);
                salvaImagem("imagem2", produto.getImagem2(), request);
                salvaImagem("imagem3", produto.getImagem3(), request);

                exibeListaProduto(request, response);
            }
        } catch (Exception erro) {
            response.getWriter().write("Ocorreu um erro não esperado durante o processo. Erro: " + erro.getMessage());
        }
    }

    private void apagaImagens(int id, HttpServletRequest request)  throws SQLException
    {
        ProdutoVO produtoCadastrado = ProdutoDAO.consulta(id);
        File file = new File(getPastaProdutos(request) + produtoCadastrado.getImagem1());
        if (file.exists()) {
            file.delete();
        }
        file = new File(getPastaProdutos(request) + produtoCadastrado.getImagem2());
        if (file.exists()) {
            file.delete();
        }

        file = new File(getPastaProdutos(request) + produtoCadastrado.getImagem3());
        if (file.exists()) {
            file.delete();
        }
    }

    private void exibeListaProduto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", ProdutoDAO.listagem());
        RequestDispatcher dispacher = request.getRequestDispatcher("ListagemProduto.jsp");
        dispacher.forward(request, response);
    }

    
  
    private String criaNomeImagem(int numeroImagem, int IdImagem,              
            HttpServletRequest request) throws Exception {    
        Part filePart = request.getPart("imagem" + numeroImagem);
        String originalName = getFileName(filePart);
        String ext = getExtension(originalName);    
        String arquivo = "img_" + IdImagem + "_" + numeroImagem + "." + ext;
        return arquivo;
    }
    
    
    private void salvaImagem(String imagem, String arquivo, 
                             HttpServletRequest request) throws Exception {        
        Part filePart = request.getPart(imagem);
        String pastaDestino = getPastaProdutos(request);

       
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File outputFilePath = new File(pastaDestino + arquivo);

            inputStream = filePart.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private String getPastaProdutos(HttpServletRequest request) {
        return request.getServletContext().getRealPath(PASTA_IMAGENS_PRODUTOS) + File.separator;
    }

    private String getExtension(String name) {
        String[] str = name.split("\\.");
        if (str.length > 1) {
            return str[str.length - 1];
        }
        return ""; 
    }

    
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
