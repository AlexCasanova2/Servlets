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
import modelo.Pokemon;
import modelo.Trainer;

public class AltaPokemon extends HttpServlet {

    @EJB
    SessionBean miEjb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Alta Pokemon</title>");
            out.println("</head>");
            out.println("<h1>Alta Pokémon</h1>");
            out.println("<body>");
            out.println("<form method=\"POST\">");
            out.println("<input type=\"text\" name=\"nombre\" placeholder=\"nombre\" required>");
            out.println("<br>");
            out.println("<input type=\"text\" name=\"tipo\" placeholder=\"tipo\" required>");
            out.println("<br>");
            out.println("<input type=\"text\" name=\"habilidad\" placeholder=\"habilidad\" required>");
            out.println("<br>");
            out.println("<input type=\"number\" name=\"lvlataque\" placeholder=\"nivel ataque\" required>");
            out.println("<br>");
            out.println("<input type=\"number\" name=\"lvldef\" placeholder=\"nivel defensa\"required> ");
            out.println("<br>");
            out.println("<input type=\"number\" name=\"velocidad\" placeholder=\"velocidad\"required>");
            out.println("<br>");
            out.println("<input type=\"number\" name=\"vida\" placeholder=\"vida\" required>");
            out.println("<br>");
            out.println("¿A qué entrenado quieres asignar el Pokémon?");
            out.println("<select name=\"entrenador\">");
            List<Trainer> trainers = miEjb.selectAllEntrenadores();
            for (Trainer t : trainers) {
                out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" name=\"alta\" value=\"Guardar\">");
            out.println("</form>");

            if ("Guardar".equals(request.getParameter("alta"))) {
                String name = request.getParameter("nombre");
                String type = request.getParameter("tipo");
                String hability = request.getParameter("habilidad");
                int atack = Integer.parseInt(request.getParameter("lvlataque"));
                int defense = Integer.parseInt(request.getParameter("lvldef"));
                int speed = Integer.parseInt(request.getParameter("velocidad"));
                int life = Integer.parseInt(request.getParameter("vida"));

                Pokemon p = new Pokemon(name, type, hability, atack, defense, speed, life, 0);

                String nameT = request.getParameter("entrenador");
                Trainer entrenadorEscogido = miEjb.getTrainerByName(nameT);

                p.setTrainer(entrenadorEscogido);
                if (miEjb.insertarPokemon(p)) {
                    out.println("Pokémon dado de alta");
                } else {
                    out.println("Este pokémon ya existe");
                }
                out.println("</body>");
                out.println("</html>");
            }

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
