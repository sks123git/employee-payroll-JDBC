import java.sql.*;

public class DBConnection {
Connection con;
PreparedStatement ps;
ResultSet rs;
EmployeePayrollObj employeePayrollObj;

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
                    +"  "+rs.getString(10)+"  "+rs.getString(11));
        }
    }
    public void updateSalary(String name,Double pay) throws Exception {
        ps = con.prepareStatement("UPDATE EMPLOYEE_PAYROLL SET BASIC_PAY = ? WHERE NAME = ?");
        ps.setDouble(1,pay);
        ps.setString(2,name);
        ps.executeUpdate();
        System.out.println("Record updated........");
        employeePayrollObj = new EmployeePayrollObj(pay);
    }
    public double getSalaryFromDB(String name) throws Exception{
        ps = con.prepareStatement("SELECT BASIC_PAY FROM EMPLOYEE_PAYROLL WHERE NAME = ?");
        ps.setString(1,name);
        rs = ps.executeQuery();
        double sal=0;
        while (rs.next()){
            sal= rs.getDouble(1);
        }
        return sal;
    }
    public void close() throws Exception{
        con.close();
        System.out.println("Connection closed.....");
    }
}
