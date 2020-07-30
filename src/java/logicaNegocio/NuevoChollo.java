/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ChollosJpaController;
import DTO.Chollos;
import DTO.Usuario;
import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
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
public class NuevoChollo extends HttpServlet {

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
        
        
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usu");
        
        
        
        MultipartRequest mr = new MultipartRequest(request, getServletContext().getRealPath("/IMAGENES"));
        
        String titulo = new String(mr.getParameter("titulo").getBytes("ISO-8859-1"),"UTF-8");
        String cuerpo = mr.getParameter("cuerpo");
        
        String precAct = mr.getParameter("precAct");
        double precioActual = Double.parseDouble(precAct);
        
        String precAnt = mr.getParameter("precAnt");
        double precioAnterior = Double.parseDouble(precAnt);
        
        Enumeration imgs = mr.getFileNames();
        String imagen =  mr.getFile((String) imgs.nextElement()).getName();
        String enlace = new String(mr.getParameter("enlace").getBytes("ISO-8859-1"),"UTF-8"); 
        String seccion = new String(mr.getParameter("seccion").getBytes("ISO-8859-1"),"UTF-8"); 
        Date fechaChollo = new Date();
        
        Chollos chollo = new Chollos(null, titulo, cuerpo, precioAnterior, precioActual, enlace, imagen, fechaChollo, seccion, 0);
        chollo.setIdUsuario(usu);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChollosLocosPU");
        ChollosJpaController ctrChollo = new ChollosJpaController(emf);
        ctrChollo.create(chollo);
        
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
