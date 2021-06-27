/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

package services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.MovieDAO;
import model.entities.Movie;
import org.json.JSONObject;

@WebServlet(name = "AddMovieService", urlPatterns = {"/AddMovieService"})
@MultipartConfig()
public class AddMovieService extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String data = request.getParameter("data");
            String s = request.getParameter("billboard");
            boolean billboard = false;
            if (!Objects.isNull(s)) {
                if (s.equals("on")) {
                    billboard = true;
                }
            }
            Part p = request.getPart("image");
            String fileName = p.getSubmittedFileName();
            if (fileName.isEmpty()) {
               //error
            }
            String type = p.getContentType();
            InputStream image = p.getInputStream();
            int length = (int) p.getSize();
            if (MovieDAO.validate(fileName)) {
                MovieDAO dao = new MovieDAO();
                try {
                    dao.add(id, new Movie(id, title, data, billboard, type, image, length));
                } catch (Exception ex) {
                }
            } else {
            }
        } catch (IOException | ServletException ex) {
            request.setAttribute("mensaje",
                    String.format("Ocurrió una excepción: '%s'", ex.getMessage()));
        }
        
        response.sendRedirect("index.html");
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

    public void adminValidation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        JSONObject user = (JSONObject) request.getSession(true).getAttribute("user");
        if (Objects.isNull(user) || !user.getBoolean("admin")) {
            response.sendRedirect("asd.html");
        }
    }
    
}
