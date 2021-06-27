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
import java.sql.SQLException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.InvoiceDAO;
import model.entities.InvoiceList;
import org.json.JSONObject;

@WebServlet(name = "UserHistoryService", urlPatterns = {"/UserHistoryService"})
public class UserHistoryService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        JSONObject user = (JSONObject) session.getAttribute("user");

        try (PrintWriter out = response.getWriter()) {
            try {
                out.println(InvoiceListJSON(user.getString("identification")));
            } catch (IOException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                out.println(new JSONObject());
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

    public String InvoiceListJSON(String id)
            throws IOException, SQLException {
        return new InvoiceList(new InvoiceDAO().listAll()).toJSONbyId(id).toString(4);
    }

    public void clientValidation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        JSONObject user = (JSONObject) request.getSession(true).getAttribute("user");
        if (Objects.isNull(user) || user.getBoolean("admin")) {
            response.sendRedirect("asd.html");
        }
    }
}
