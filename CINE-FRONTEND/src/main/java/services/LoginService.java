
package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "LoginService", urlPatterns = {"/LoginService"})
public class LoginService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
             
    System.out.println("servlet is working   ");

    String id =request.getParameter("id");  
    String pass =request.getParameter("password");  
    
    
    HttpSession session = request.getSession(true);
    
    User user = null;
        try {
            //se conecta a la bd y devuelve al usuario
            user = new UserDAO().loginUser(id, pass);
        } catch (Exception ex) {
            
        }
        System.out.println(user.getId());
            //verifica que se haya traido al usuario
        if (Objects.isNull(user) ) { 
            throw new IllegalArgumentException();
        }
        
        try (PrintWriter out = response.getWriter()) {
            JSONObject r = new JSONObject();
            JSONArray a = new JSONArray();
            
                JSONObject e = new JSONObject();
                e.put("Nombre", user.getRol());
                e.put("Identificacion", user.getId());
                e.put("Clave", user.getPassword());
                e.put("InfoCliente", user.getUserClientInfo());
                a.put(e);
           
            r.put("datos-user", a);
            System.out.println(r.toString(4));
            out.println(r);
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

    
    
}
