import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
Connection con;
PreparedStatement ps;
ResultSet rs;

DBConnection() throws Exception{
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "password");
        System.out.println("connection established........");
    }catch (Exception e){}
    con.close();
    System.out.println("connection closed...............");
}

}
