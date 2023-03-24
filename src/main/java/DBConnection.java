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
}

    public void display() throws Exception {
        ps = con.prepareStatement("SELECT * FROM EMPLOYEE_PAYROLL");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
                    +" "+rs.getDouble(4)+"  "+rs.getDouble(5)
                    +"  "+rs.getDouble(6)+"  "+rs.getDouble(7)
                    +"  "+rs.getDouble(8)+"  "+rs.getDate(9)
                    +"  "+rs.getString(10)+"  "+rs.getString(11)
                    +"  "+rs.getString(12));
        }
    }

    public void close() throws Exception{
        con.close();
        System.out.println("Connection closed.....");
    }
}
