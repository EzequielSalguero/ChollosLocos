/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ChollosJpaController;
import DAO.LikesJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Chollos;
import DTO.Likes;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MostrarCholloSeleccionado extends HttpServlet {

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

        String cod = request.getParameter("cod");
        int codChollo = Integer.parseInt(cod);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChollosLocosPU");
        ChollosJpaController ctrChollo = new ChollosJpaController(emf);
        Chollos chollo = ctrChollo.findChollos(codChollo);

        HttpSession miSesion = request.getSession();
        Usuario usu = (Usuario) miSesion.getAttribute("usu");
        

        LikesJpaController ctrLikes = new LikesJpaController(emf);

        if (usu != null) {

            List<Likes> listaLike = ctrLikes.findLikesEntities();
            String likeSN = "NO";

            for (int i = 0; i < listaLike.size(); i++) {
                int codC = listaLike.get(i).getLikesPK().getCodChollo();
                String idUsu= listaLike.get(i).getLikesPK().getIdUsuario();
                String idUsuario= usu.getIdUsuario();
                if (codC == codChollo && idUsu.equals(idUsuario)) {
                    likeSN = "SI";
                }
            }
            request.setAttribute("likeSN", likeSN);
        }

        request.setAttribute("chollo", chollo);

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
