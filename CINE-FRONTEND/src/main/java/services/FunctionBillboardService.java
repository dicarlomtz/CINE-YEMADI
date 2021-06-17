package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.FunctionDAO;
import model.entities.FunctionList;
import org.json.JSONObject;

@WebServlet(name = "FunctionBillboardService", urlPatterns = {"/FunctionBillboardService"})
public class FunctionBillboardService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        try (PrintWriter out = response.getWriter()) {
            try {
                out.println(FunctionListJSON(request.getParameter("id_movie")));
            } catch (IOException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                JSONObject a = new JSONObject();
                a.put("ms1", ex.toString());
                out.println(a.toString(4));
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

    public String FunctionListJSON(String idMovie)
            throws IOException, SQLException {
        return new FunctionList(new FunctionDAO().listFunctionBillboard(idMovie)).toJSON().toString(4);
    }
}
