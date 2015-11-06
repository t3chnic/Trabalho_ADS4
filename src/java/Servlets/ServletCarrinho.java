/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.ProdutoDAO;
import VO.CarrinhoVO;
import VO.ProdutoVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Programador
 */
@WebServlet(name = "ServletCarrinho", urlPatterns = {"/ServletCarrinho"})
public class ServletCarrinho extends HttpServlet {

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

            HashMap<Integer, Integer> carrinho = obtemCarrinho(sessao);

            if (operacao.equalsIgnoreCase("adiciona-no-carrinho")) {

                int idProduto = Integer.parseInt(request.getParameter("id"));

                if (carrinho.containsKey(idProduto)) {
                    int qtde = (Integer) carrinho.get(idProduto);
                    qtde++;
                    carrinho.put(idProduto, qtde);
                } else {
                    carrinho.put(idProduto, 1);
                }

                response.sendRedirect("ServletVitrine?operacao=L");
            } else if (operacao.equalsIgnoreCase("exibir-carrinho")) {
                exibeCarrinho(request, response, carrinho);
            } else if (operacao.equalsIgnoreCase("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                if (carrinho.containsKey(id)) {
                    carrinho.remove(id);
                    exibeCarrinho(request, response, carrinho);
                }
            }
            else if (operacao.equalsIgnoreCase("finalizar"))
            {
                //ler os dados do hashMap e guardar na tabela de pedido
                //rediredionar para uma página que mostre os dados do pedido
                //ou pagamento
                //limpar os dados do carrinho na sessão                
            }
        } catch (Exception erro) {
            response.getWriter().write("Ocorreu um erro não esperado durante o processo. Erro: " + erro.getMessage());
        }
    }

    private void exibeCarrinho(HttpServletRequest request, HttpServletResponse response,
            HashMap<Integer, Integer> carrinho)
            throws Exception {

        ArrayList<CarrinhoVO> listaCarrinho = new ArrayList<CarrinhoVO>();

        double total = 0;
        for (Map.Entry<Integer, Integer> item : carrinho.entrySet()) {

            Integer idProduto = item.getKey();
            Integer quantidade = item.getValue();

            ProdutoVO produto = ProdutoDAO.consulta(idProduto);
            CarrinhoVO dadosCarrinho = new CarrinhoVO();
            dadosCarrinho.setProduto(produto);
            dadosCarrinho.setQuantidade(quantidade);
            listaCarrinho.add(dadosCarrinho);
            
            total += produto.getPreco()* quantidade;            
        }
        request.setAttribute("carrinho", listaCarrinho);
        request.setAttribute("total", total);
        RequestDispatcher dispacher = request.getRequestDispatcher("Carrinho.jsp");
        dispacher.forward(request, response);

    }

    private HashMap<Integer, Integer> obtemCarrinho(HttpSession sessao) {
        if (sessao.getAttribute("carrinho") == null) {
            HashMap<Integer, Integer> carrinho = new HashMap<Integer, Integer>();
            sessao.setAttribute("carrinho", carrinho);
        }

        HashMap<Integer, Integer> carrinho
                = (HashMap<Integer, Integer>) sessao.getAttribute("carrinho");
        return carrinho;

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
