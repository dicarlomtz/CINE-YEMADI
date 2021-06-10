package services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "MoviesListService", urlPatterns = {"/MoviesListService"})
public class MoviesListService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        Object[][] datos = null; // Falta retornar datos
        try (PrintWriter out = response.getWriter())
        {
            JSONObject r = new JSONObject();
            JSONArray a = new JSONArray();
            for(Object[] registroPelicula : datos)
            {
                JSONObject e = new JSONObject();
                e.put("secuencia", registroPelicula[0]);
                e.put("nombre", registroPelicula[1]);
                a.put(e);
            }
            r.put("datos_peliculas", a);
            out.println(r);
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
}
