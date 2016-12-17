/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psg;

import hiber.Passenger;
import hiber.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author STD
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);

            String username = request.getParameter("email");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String telephone = request.getParameter("tel");

            out.println(!username.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals("") && !telephone.equals(""));
            if (!username.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals("") && !telephone.equals("")) {
                //out.println(username+password+firstname+lastname+telephone);
                if (!repassword.equals(password)) {
                    RequestDispatcher rd = request.getRequestDispatcher("psgFirstPage.jsp?check=qgl/j-0356df");
                    rd.forward(request, response);
                } else {
                    Service service = new Service();
                    int id = service.getAllPassenger()+1;
                    Passenger passenger = new Passenger(id, username, password, firstname, lastname, null, telephone, username);
                    boolean saved = service.insertPassenger(passenger);
                    out.println(saved);
                    
                    if (saved) {
                        RequestDispatcher rd = request.getRequestDispatcher("psgFirstPage.jsp?save=yes");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("psgFirstPage.jsp?save=no");
                        rd.forward(request, response);
                    }
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("psgFirstPage.jsp?data=gqaeky53vg");
                rd.forward(request, response);
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

}
