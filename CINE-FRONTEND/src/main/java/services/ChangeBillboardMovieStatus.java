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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.MovieDAO;
import model.entities.Movie;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "ChangeBillboardMovieStatus", urlPatterns = {"/ChangeBillboardMovieStatus"})
@MultipartConfig
public class ChangeBillboardMovieStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("application/json;charset=UTF-8");
        encoding = Optional.of(request.getCharacterEncoding());
        try (PrintWriter out = response.getWriter()) {
            JSONObject res = new JSONObject();
            try {
                JSONObject json = new JSONObject(toUTF8String(request.getParameter("movie")));

                Movie movie = new MovieDAO().retrieve(json.getString("id"));
                movie.setBillboard(json.getBoolean("billboard"));
                new MovieDAO().update(movie.getId(), movie);

                res.put("result",
                        String.format("Cambiando estado de cartelera a película: '%s'%n",
                                movie.getId()));
            } catch (IOException | SQLException | JSONException ex) {
                res.put("result", String.format("No se logró realizar el cambio%n"));
            }
            out.println(res.toString(4));
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

    private String toUTF8String(String s) throws UnsupportedEncodingException {
        return encoding.isPresent() ? s : new String(s.getBytes(), StandardCharsets.UTF_8);
    }

    public void adminValidation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        JSONObject user = (JSONObject) request.getSession(true).getAttribute("user");
        if (Objects.isNull(user) || !user.getBoolean("admin")) {
            response.sendRedirect("asd.html");
        }
    }

    private Optional<String> encoding;
}
