/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ChollosJpaController;
import DAO.ComentarioJpaController;
import DTO.Chollos;
import DTO.Comentario;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ezequiel
 */
public class NuevoComentario extends HttpServlet {

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
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChollosLocosPU");
        ChollosJpaController ctrChollo = new ChollosJpaController(emf);
        ComentarioJpaController ctrComentario = new ComentarioJpaController(emf);
        
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usu");
        
        String cod = (String) request.getParameter("codChollo");
        int codigo = Integer.parseInt(cod);
        String coment = (String) request.getParameter("comentario");
        Chollos chollo = ctrChollo.findChollos(codigo);
        
        Comentario comentario = new Comentario(null,codigo, coment);
        comentario.setCodChollo(codigo);
        comentario.setIdUsuario(usu);
        ctrComentario.create(comentario);
        
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
