package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.CinemaDAO;
import model.dao.RoomDAO;
import model.entities.Room;
import org.json.JSONObject;

@WebServlet(name = "BuildRoomService", urlPatterns = {"/BuildRoomService"})
@MultipartConfig
public class BuildRoomService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("application/json;charset=UTF-8");
        encoding = Optional.of(request.getCharacterEncoding());
        try (PrintWriter out = response.getWriter()) {
            JSONObject res = new JSONObject();
            try {
                JSONObject json = new JSONObject(toUTF8String(request.getParameter("room")));

                Room room = new Room(
                        new CinemaDAO().retrieve(json.getInt("cinema")),
                        json.getInt("number"),
                        json.getInt("capacity"));

                new RoomDAO().add(room.buildKey(), room);
                res.put("result",
                        String.format("Agregando sala con código: '%s'%n",
                                room.buildKey()));
            } catch (SQLException | IOException ex) {
                res.put("result", String.format("La sala no se pudo agregar%n"));
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

    private Optional<String> encoding;
}
