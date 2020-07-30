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
import DTO.LikesPK;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DarLikes extends HttpServlet {

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

        String like = request.getParameter("like");
        String cod = request.getParameter("cod");

        int codC = Integer.parseInt(cod);

        HttpSession miSesion = request.getSession();
        Usuario usu = (Usuario) miSesion.getAttribute("usu");
        LikesJpaController ctrLike = new LikesJpaController(emf);

        ChollosJpaController ctrChollo = new ChollosJpaController(emf);

        Chollos cholloLiked = ctrChollo.findChollos(codC);

        if (like != null) {
            if (like.equals("SI")) {
                LikesPK lPK = new LikesPK(usu.getIdUsuario(), codC);
                cholloLiked.setLikes(cholloLiked.getLikes() - 1);
                try {
                    ctrChollo.edit(cholloLiked);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ctrLike.destroy(lPK);

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Likes likes = new Likes(usu.getIdUsuario(), codC);
                cholloLiked.setLikes(cholloLiked.getLikes() + 1);
                try {
                    ctrChollo.edit(cholloLiked);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ctrLike.create(likes);
                } catch (Exception ex) {
                    Logger.getLogger(DarLikes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

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
