/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas PC
 */
@WebServlet(name = "CadastrarConta", urlPatterns = {"/CadastrarConta"})
public class CadastrarConta extends HttpServlet {

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
          String conta = request.getParameter("nconta");
          String cpf = request.getParameter("cpf");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost/contacorrente","root","");
            PreparedStatement  p =  c.prepareStatement("insert into contas (NumeroConta,CPF_titular) values (?,?)");
            p.setString(1, conta );
            p.setString(2, cpf);
            p.execute() ;
            response.getWriter().print("Salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarConta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarConta.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
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
