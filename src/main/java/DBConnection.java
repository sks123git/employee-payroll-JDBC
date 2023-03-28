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

    public void updateDate(String date) throws Exception {
        ps = con.prepareStatement("SELECT * FROM employee_payroll WHERE CAST(? AS DATE) AND DATE(NOW())");
        ps.setDate(1, java.sql.Date.valueOf(date));
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
                    +" "+rs.getDouble(4)+"  "+rs.getDouble(5)
                    +"  "+rs.getDouble(6)+"  "+rs.getDouble(7)
                    +"  "+rs.getDouble(8)+"  "+rs.getDate(9)
                    +"  "+rs.getString(10)+"  "+rs.getString(11));
        }
    }

    public void FindMultipleValues() throws Exception{
        System.out.println("------------------------------------------------------");
        ps = con.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("sum of salaries of male: "+ rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("sum of salaries of females: "+ rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Average of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Average of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Maximum of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Maximum of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Minimum of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Minimum of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Number of male employee: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Number of female employee: " + rs.getInt(1));
        }
        System.out.println("------------------------------------------------------");
    }
}
