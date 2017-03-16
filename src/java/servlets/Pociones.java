/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bean.SessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Trainer;

/**
 *
 * @author 25369405z
 */
public class Pociones extends HttpServlet {

    
     @EJB
    SessionBean miEjb;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Pociones</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Pociones at " + request.getContextPath() + "</h1>");
            out.println("<form method=\"POST\">");
            out.println("¿Quién eres?");
            out.println("<select name=\"entrenador\">");
            List<Trainer> trainers = miEjb.selectAllEntrenadores();
            for (Trainer t : trainers) {
                out.println("<option value=\"" + t.getName() + "\">" + t.getName() + " " + t.getPoints() + " Puntos"+ "</option>");
            }
            out.println("</select>");
            out.println("<br>");
            out.println("¿Cuantas pociones quieres comprar?");
            out.println("<input type=\"number\" name=\"asigna\">");
            out.println("<br>");
            out.println("<input type=\"submit\" name=\"alta\" value=\"Asignar\">");
            out.println("</form>");
            
            if("Asignar".equals(request.getParameter("alta"))){
                Trainer t = miEjb.getTrainerByName(request.getParameter("entrenador"));
                int pociones = Integer.parseInt(request.getParameter("asigna"));
                out.println(pociones);
                if(t.getPoints() <10 *pociones){
                    out.println("Te faltan" + (10 *pociones  - t.getPoints()) + "para comprar una poción de vida");
                }else{
                    t.setPoints(t.getPoints() - 10 * pociones);
                    t.setPotions(t.getPotions() + 1 * pociones);
                    miEjb.updateTrainer(t);
                    out.println("Compra realizada");
                }
            }
            
            
            out.println("</body>");
            out.println("</html>");
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
