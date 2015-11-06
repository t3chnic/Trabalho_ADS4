/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.CategoriaDAO;
import DAO.ProdutoDAO;
import VO.ProdutoVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "ServletVitrine", urlPatterns = {"/ServletVitrine"})
public class ServletVitrine extends HttpServlet {

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

            if (operacao == null || operacao.equalsIgnoreCase("L")) {
                exibeVitrine(request, response);
            }             
            else if (operacao.equalsIgnoreCase("descricaoProduto")) {
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("produto", ProdutoDAO.consulta(id));
                RequestDispatcher dispacher = request.getRequestDispatcher("DescricaoProduto.jsp");
                dispacher.forward(request, response);
            }

        } catch (Exception erro) {
            response.getWriter().write("Ocorreu um erro não esperado durante o processo. Erro: " + erro.getMessage());
        }
    }

    private void exibeVitrine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String filtroCateg = "0", filtroNome = "", filtroQtde = "30";
        if (request.getParameter("filtroCategoria") != null) {

            filtroCateg = request.getParameter("filtroCategoria");

            filtroQtde = request.getParameter("filtroQtde");
        }
        filtroNome = request.getParameter("filtroNome");
        List<ProdutoVO> listaProduto = ProdutoDAO.listagem(Integer.parseInt(filtroCateg),
                filtroNome, true, Integer.parseInt(filtroQtde));
        request.setAttribute("listaProduto", listaProduto);
        request.setAttribute("categorias", CategoriaDAO.listagem());

        RequestDispatcher dispacher = request.getRequestDispatcher("Vitrine.jsp");
        dispacher.forward(request, response);
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
