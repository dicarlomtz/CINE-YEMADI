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
import model.dao.CustomerDAO;
import model.dao.UserDAO;
import model.entities.Customer;
import model.entities.PaymentCard;
import model.entities.Rol;
import model.entities.User;
import org.json.JSONObject;

@WebServlet(name = "SignupService", urlPatterns = {"/SignupService"})
@MultipartConfig
public class SignupService extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("application/json;charset=UTF-8");
        encoding = Optional.of(request.getCharacterEncoding());
        try (PrintWriter out = response.getWriter()) {
            JSONObject res = new JSONObject();
            try {
                JSONObject json = new JSONObject(toUTF8String(request.getParameter("user")));
                Customer cust = new Customer(
                        json.getString("identification"),
                        json.getString("surnames"),
                        json.getString("name"),
                        json.getString("telephone"),
                        new PaymentCard(json.getString("card"))
                );
                new CustomerDAO().add(cust.getId(), cust);
                User user = new User(
                        json.getString("identification"),
                        cust,
                        json.getString("password"),
                        new Rol(false)
                );
                if (Objects.isNull(user)) {
                    throw new IllegalArgumentException();
                }
                new UserDAO().add(user.getId(), user);
                res.put("result", "valid");
                res.put("user", user.toJSON());
            } catch (SQLException | IOException ex) {
                res.put("result", "invalid");
                res.put("message", String.format("Las credenciales no son validas%n"));
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
