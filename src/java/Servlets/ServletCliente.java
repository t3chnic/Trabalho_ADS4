/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.ClienteDAO;
import DAO.Metodos;
import VO.ClienteVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "ServletCliente", urlPatterns = {"/ServletCliente"})
public class ServletCliente extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HashMap<String, String> Erros = new HashMap<String, String>();
        String op = request.getParameter("operacao");

        /*if ((sessao.getAttribute("logado") == null)
         || ((Boolean) sessao.getAttribute("logado") == false)) {
         response.sendRedirect("Login.jsp?facalogin=true");
         return;
         }*/
        try {

            if (op.equals("E")) {
                ClienteDAO.delete(Integer.parseInt(request.getParameter("id")));
            } else {

                if (request.getParameter("nome").length() < 5) {
                    Erros.put("nome", "Pelo menos 4 caracteres");
                }

                String data = request.getParameter("data");

                try {
                    Metodos.stringToDate(request.getParameter("dataNasci"));

                } catch (Exception erro) {
                    Erros.put("dataNasci", "Data inválida.");
                }
                                
                if (request.getParameter("cpf").length() == 0) {
                    Erros.put("cpf", "CPF inválido");
                }

                if (request.getParameter("telefone").length() == 0) {
                    Erros.put("telefone", "Telefone inválido");
                }

                if (request.getParameter("endereco").length() < 4) {
                    Erros.put("endereco", "Pelo menos 3 caracteres");
                }

                if (request.getParameter("email").length() == 0) {
                    Erros.put("email", "Campo obrigatório");
                }

                if (request.getParameter("senha").length() < 7) {
                    Erros.put("senha", "Informe uma senha com pelo menos 6 caracteres");
                }

                if (!Erros.isEmpty()) {
                    request.setAttribute("erros", Erros);
                    RequestDispatcher dispacher = request.getRequestDispatcher("IncluiCliente.jsp");
                    dispacher.forward(request, response);
                    return;
                }

                ClienteVO c = new ClienteVO();
                //c.setId(Integer.parseInt(request.getParameter("codigo")));
                c.setNome(request.getParameter("nome"));
                c.setDataNascimento(Metodos.stringToDate(request.getParameter("dataNasci")));
                c.setCpf(request.getParameter("cpf"));
                c.setTelefone(request.getParameter("telefone"));
                c.setEndereco(request.getParameter("endereco"));
                c.setEmail(request.getParameter("email"));
                c.setSenha(request.getParameter("senha"));
                
                if (op.equals("I")) {
                    ClienteDAO.insere(c);

                } else if (op.equals("A")) {
                    c.setId(Integer.parseInt(request.getParameter("codigo")));                    ;
                    ClienteDAO.altera(c);
                }

            }
            response.sendRedirect("ListagemCliente.jsp");
        } catch (Exception erro) {
            response.getWriter().write("Ops! Houve um erro: " + erro.getMessage());
        }

    }
    /*try {
     request.setCharacterEncoding("UTF-8");
     String operacao = request.getParameter("operacao");
     HashMap<String, String> erros = new HashMap<String, String>();
     HttpSession sessao = request.getSession();

     boolean novoRegistro = request.getParameter("id") != null
     && request.getParameter("id").length() == 0;

     if (operacao != null && operacao.equalsIgnoreCase("exibe-cadastro")) {

     RequestDispatcher dispacher = request.getRequestDispatcher("IncluiCliente.jsp?operacao=I");
     dispacher.forward(request, response);
     return;
     }

     if (operacao != null && operacao.equalsIgnoreCase("alteracao")) {

     int id = Integer.parseInt(request.getParameter("id"));
     ClienteVO cliente = ClienteDAO.consulta(id);
     request.setAttribute("cliente", cliente);

     RequestDispatcher dispacher = request.getRequestDispatcher("IncluiCliente.jsp?operacao=A");
     dispacher.forward(request, response);

     } else if (operacao == null || operacao.equalsIgnoreCase("L")) {
     exibeListaCliente(request, response);
     } else if (operacao.equalsIgnoreCase("excluir")) {
     int id = Integer.parseInt(request.getParameter("id"));
     ClienteDAO.delete(id);
     exibeListaCliente(request, response);

     } else if (operacao.equalsIgnoreCase("salvar")) {
     String id = request.getParameter("id");
     String nome = request.getParameter("nome").trim();
     String data = request.getParameter("dataNasci").trim();
     String cpf = request.getParameter("cpf").trim();
     String telefone = request.getParameter("telefone").trim();
     String endereco = request.getParameter("endereco").trim();
     String email = request.getParameter("email").trim();
     String senha = request.getParameter("senha").trim();

     if (nome.length() == 0) {
     erros.put("nome", "Campo obrigatório");
     }
     if (data.length() == 0) {
     erros.put("dataNasci", "Campo obrigatório");
     }
     if (cpf.length() == 0) {
     erros.put("cpf", "Campo obrigatório");
     }
     if (telefone.length() == 0) {
     erros.put("telefone", "Campo obrigatório");
     }
     if (endereco.length() == 0) {
     erros.put("endereco", "Campo obrigatório");
     }
     if (email.length() == 0) {
     erros.put("email", "Campo obrigatório");
     }
     if (senha.length() == 0) {
     erros.put("senha", "Campo obrigatório");
     }

     if (erros.isEmpty() == false) {
     request.setAttribute("erros", erros);

     RequestDispatcher dispacher = request.getRequestDispatcher("IncluiCliente.jsp");
     dispacher.forward(request, response);
     } else {
     ClienteVO cliente = new ClienteVO();

     if (id.length() > 0) {
     cliente.setId(Integer.parseInt(id));
     }

     cliente.setNome(nome);
     cliente.setDataNascimento(Metodos.stringToDate(data));
     cliente.setCpf(cpf);
     cliente.setTelefone(telefone);
     cliente.setEndereco(endereco);
     cliente.setEmail(email);
     cliente.setSenha(senha);

     if (operacao.equals("I")) {
     ClienteDAO.insere(cliente);
     } else if (operacao.equals("A")) {
     ClienteDAO.altera(cliente);
     }
     }
     }

     } catch (Exception erro) {
     response.getWriter().write("Ocorreu um erro não esperado durante o processo. Erro: " + erro.getMessage());
     }
     }

     private void exibeListaCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {
     request.setAttribute("lista", ClienteDAO.listagem());
     RequestDispatcher dispacher = request.getRequestDispatcher("ListagemCliente.jsp");
     dispacher.forward(request, response);
     }*/

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
