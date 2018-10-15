import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a
 */
@WebServlet(urlPatterns = {"/geral"})
public class geral extends HttpServlet {

    private PreparedStatement ps = null;
    private ligacao liga = new ligacao();
    private  Connection conexao;
    private ResultSet r = null;
    
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    try {
        int op = Integer.parseInt(request.getParameter("op"));
        PrintWriter out = response.getWriter();
        
        if(op == 1){
                    
                String sql = "INSERT INTO `ContaCorrente` (`idContaCorrente`, `NumeroConta`, `CPF_Titular`) VALUES (NULL, '" + request.getParameter("conta")+ "', '" + request.getParameter("cpf") + "')";
                this.conexao = liga.criarconexcao();
                ps = conexao.prepareStatement(sql);
                ps.execute(sql);
                out.println("<p>Cadastro realizado com sucesso!</ś><a href='/ContaBancaria'>Início");
            
            
        }
        
        if(op == 2){
                  
                 int id = 0;         
                 String sql = "SELECT * FROM ContaCorrente WHERE `NumeroConta` = '" +request.getParameter("conta")+ "'";
                 this.conexao = liga.criarconexcao();
                 ps = conexao.prepareStatement(sql);
                 r = ps.executeQuery();
       
                 while (r.next()) {
                    id = r.getInt("idContaCorrente");
                     System.out.println(id);
                 }
                 
                 if(id > 0){
               	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

                  Date dataAtual = new Date();
                 String sql2 = "INSERT INTO `Operacao` (`idOperacao`, `DataOperacao`, `ValorOperacao`, `CPFResponsavelOperacao`, `idContaCorrente`) VALUES"+
                  "(NULL, '" + new java.sql.Date(dataAtual.getTime()) + "', '" + request.getParameter("valor")+ "','" + request.getParameter("cpf")+ "','" + id + "')";
                this.conexao = liga.criarconexcao();
                ps = conexao.prepareStatement(sql2);
                ps.execute(sql2);
                out.println("<p>Cadastro realizado com sucesso!</p><a href='/ContaBancaria'>Início");
                }else{
                 out.println("<p>Verifique a conta</p><a href='/ContaBancaria'>Início");    
                }
            
            
        }
        
        
        if(op == 3){
                  int id = 0;
                  String cpf = "";
                  String conta= "";
                  String db;
                  String soma = "";
                 String sql = "SELECT * FROM ContaCorrente WHERE `NumeroConta` = '" +request.getParameter("conta")+ "'";
                 this.conexao = liga.criarconexcao();
                 ps = conexao.prepareStatement(sql);
                 r = ps.executeQuery();
                 while (r.next()) {
                    id = r.getInt("idContaCorrente");
                    cpf =  r.getString("CPF_Titular");
                    conta = r.getString("NumeroConta");
            
                                       
                 }
                 String html = "<p>Número da conta:"+conta+"</p>";
                        html += "<p>cpf:"+cpf+"</p>";

                 if(id > 0){
                 String sql2 = "SELECT * FROM `Operacao` WHERE `idContaCorrente` ="+id+" ORDER BY `Operacao`.`DataOperacao` ASC";
                 ps = conexao.prepareStatement(sql2);
                 r = ps.executeQuery();
                 
                 while (r.next()) {
                     db = (r.getBigDecimal("ValorOperacao").compareTo(new BigDecimal(0)) < 0) ? "Débito":"Credito";
                     html += "<tr>\n" +
                                "    <td>"+r.getBigDecimal("ValorOperacao")+"</td>\n" +
                                "    <td>"+r.getString("CPFResponsavelOperacao")+"</td>\n" +
                                "    <td>"+db +"</td>\n" +
                                "    <td>"+r.getDate("DataOperacao")+"</td>\n" +
                                "  </tr><br>\n";
                     
                 }
                 
                 String sql3 = "SELECT SUM(ValorOperacao) as soma FROM `Operacao` WHERE `idContaCorrente` = "+id+"";
                 ps = conexao.prepareStatement(sql3);
                 r = ps.executeQuery();
                 
                 while (r.next()) {
                    soma = r.getString("soma");
                       
                }
                 
                 
                  out.println(html+"<br>Saldo total:"+soma+"<br><a href='/ContaBancaria'>Início");
                 
                 }else{
                   out.println("Conta invalida");   
                 }
        
        }
   
    
    
    } catch (SQLException ex) {
         Logger.getLogger(geral.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(geral.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   

}
