package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.TicketDAO;
import model.entities.TicketList;
import org.json.JSONObject;

@WebServlet(name = "UserHistoryService", urlPatterns = {"/UserHistoryService"})
public class UserHistoryService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        String id = (String) session.getAttribute("id");
        
        try(PrintWriter out = response.getWriter())
        {
            try {
                out.println(TicketListJSON(id));
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

    public String TicketListJSON(String id)
            throws IOException, SQLException {
        return new TicketList(new TicketDAO().listAll()).toJSONbyId(id).toString(4);
    }
}
