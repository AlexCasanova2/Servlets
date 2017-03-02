package servlets;

import bean.SessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Trainer;


public class AltaEntrenador extends HttpServlet {
    
    @EJB
    SessionBean miEjb;

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Alta Entrenador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Alta Entrenador</h1>");
            String nombre = request.getParameter("nombre");
            int pokeballs = Integer.parseInt(request.getParameter("pokeballs"));
            int pociones = Integer.parseInt(request.getParameter("pociones"));
            
            Trainer t = new Trainer(nombre, pokeballs, pociones, 0);
            
            if(miEjb.insertarEntrenador(t)){
                out.println("Entrenador dado de alta en la Base de Datos");
            }else{
                out.println("Este entrendor ya existe");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
