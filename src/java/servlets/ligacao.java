import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ligacao {

    
    private static Connection liga = null;

    
    public Connection criarconexcao() throws SQLException, ClassNotFoundException {
                                                                                
    String u = "jdbc:mysql://localhost:3306/banco?user=root&password=&autoReconnect=true";
        Class.forName("com.mysql.jdbc.Driver");
         liga = DriverManager.getConnection(u);
        
        return liga;
    }
    
}
